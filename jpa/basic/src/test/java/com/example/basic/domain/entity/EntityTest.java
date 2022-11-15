package com.example.basic.domain.entity;

import com.example.basic.type.MemberType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class EntityTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    @Rollback(false)
    public void memberTest(){
        Member memberA = new Member();  // Entity
        Member memberB = new Member();  // Entity

        memberA.setMemberName("한동석");
        memberA.setMemberEmail("tedhan1204@gmail.com");
        memberA.setMemberPassword("1234");
        memberA.setMemberAge(20);
        memberA.setMemberType(MemberType.MEMBER);

        memberB.setMemberName("성은지");
        memberB.setMemberEmail("sej07@naver.com");
        memberB.setMemberPassword("0415");
        memberB.setMemberAge(20);
        memberB.setMemberType(MemberType.ADMIN);

//        persist Context(영속성 컨텍스트)에 등록, 1차 캐시에 저장
        entityManager.persist(memberA);
        entityManager.persist(memberB);

//        영속성 컨텍스트에 등록된 정보를 SQL로 방출, 캐시는 그대로 유지
//        원래 flush()는 커밋할 때 호출된다.
        entityManager.flush();

//        영속성 컨텍스트에 있는 1차 캐시를 없애준다.
        entityManager.clear();

//        캐시에 해당 ID가 있다면 SQL 조회 없이 캐시에서 가져온다(성능 최적화)
        Member findMember1 = entityManager.find(Member.class, 1L);

//        entityManager.flush();
//        entityManager.clear();    // findMember1과 findMember2 사이에 clear()가 있으면 persist context가 비워지면서(비영속)
                                    // 캐시에 담긴 게 아니라서 DB에서 새로 조회해오면서 주소가 달라지므로 값 비교에서 false가 나옴

        Member findMember2 = entityManager.find(Member.class, 1L);

//        더티 체킹
        findMember2.setMemberPassword("0000");

//        entityManager.flush();
//        entityManager.clear();
        
//        삭제를 할 경우 영속상태인 객체만 가능하다 (clear해서 이미 비영속상태인 경우 삭제할 객체를 찾을 수 없기 때문에 삭제 불가능)
        entityManager.remove(findMember2);

        entityManager.flush();  // flush가 되어도 1차 캐시에는 남아 있기 때문에 findMember1과 findMember2가 같은지 비교 가능
        entityManager.clear();  // clear()가 되어도 이미 findMember1과 findMember2가 동일한 주소로 DB에서 가져온 이후,
                                // 변수에 이미 주소값이 저장된 후이기 때문에 clear()해도 비교에 문제가 없음

        if(Optional.ofNullable(findMember1).isPresent()){
            assertThat(findMember1.getMemberEmail()).isEqualTo("tedhan1204@gmail.com");
//            SELECT 쿼리 flush 후 1차 캐시에 저장된 객체를 다시 조회하면 값 비교는 항상 true이다.
            assertThat(findMember1).isEqualTo(findMember2);
        }
    }
}
