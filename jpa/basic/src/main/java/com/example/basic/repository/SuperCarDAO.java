package com.example.basic.repository;

import com.example.basic.domain.entity.SuperCar;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class SuperCarDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public SuperCar save(SuperCar supercar){
        entityManager.persist(supercar);
        return entityManager.find(SuperCar.class, supercar.getSuperCarId());
    }

    public void delete(SuperCar supercar){
        entityManager.remove(supercar);
    }

//    조회 시 NPE를 방어하기 위해 Optional로 리턴한다.
    public Optional<SuperCar> findById(Long supercarId){
        return Optional.ofNullable(entityManager.find(SuperCar.class, supercarId));
    }

    public List<SuperCar> findAll(){
//        한 개의 데이터를 조회할 때에는 JPA가 find()를 제공하지만 전체를 조회할 때에는 JPA가 제공하는 JPQL을 사용해야 한다.
//        SQL문 뒤에는 결과 중 한 개 행에 대한 resultType을 작성해준다. 여러 개 행을 가져올 때에는 getResultList()를 사용한다.

//        ▶ JPQL 주의사항
//        1. 엔티티명과 필드명은 대소문자를 구분한다.
//        2. JPQL 키워드는 대소문자를 구분하지 않는다.
//        3. JPQL에서 사용하는 테이블명은 클래스명이 아닌 엔티티명이다.
//        4. 엔티티의 Alias는 필수로 작성해야 한다.
        return entityManager.createQuery("select s from SuperCar s", SuperCar.class).getResultList();
        // JPQL (설정해줄 수 있지만 기본적으로 테이블명이 아니고 엔티티명(클래스명)이 들어감)
        // 또 ""안에서 작성을 해야 하므로 오타가 발생하면 컴파일이 아닌 런타임 에러로 잡힘
        // QueryDSL을 쓰면 selectFrom().where().sort() 이런 식으로 메소드로 쿼리를 작성하기 때문에
        // 오타가 날 수도 없을 뿐더러 컴파일 에러로 잡을 수 있음
        // 하지만 QueryDSL도 내부적으로는 JPQL을 사용하기 때문에 JPQL을 알아야 QueryDSL을 이해할 수 있음
    }

    public Long getCountOfSuperCar(){
        return entityManager.createQuery("select count(s) from SuperCar s", Long.class).getSingleResult();
    }

//    파라미터 바인딩
    public List<SuperCar> findSuperCarByReleaseDate(String superCarReleaseDate){
        return entityManager.createQuery("select s from SuperCar s where function('to_char', s.superCarReleaseDate, 'yyyyMMdd') = :superCarReleaseDate")
                .setParameter("superCarReleaseDate", superCarReleaseDate).getResultList();
    }

    public List<SuperCar> findSuperCarByColorContaining(String superCarColor){
        return entityManager.createQuery("select s from SuperCar s where s.superCarColor like :keyword")
                .setParameter("keyword", "%" + superCarColor + "%").getResultList();
    }

//    실습
//    시작 날짜와 종료 날짜를 전달받은 뒤 해당 기간 내에 출시된 슈퍼카 목록 전체 조회
    public List<SuperCar> findSuperCarBetweenReleaseDate(LocalDateTime startDate, LocalDateTime endDate){
        return entityManager.createQuery("select s from SuperCar s where s.superCarReleaseDate between :startDate and :endDate")
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }


}
