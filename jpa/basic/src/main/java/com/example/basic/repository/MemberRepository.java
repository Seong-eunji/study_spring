package com.example.basic.repository;

import com.example.basic.domain.entity.Member;
import com.example.basic.type.MemberType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// Query method : Spring-Data-JPA에서 정해놓은 메소드의 이름으로 선언하면 자동으로 구현체를 주입하여 쿼리를 실행해준다.
// find[조회할 필드명 또는 아무거나]By[Where절에 =으로 들어갈 조건 필드명] : 조회할 필드명을 생략하면 전체 조회
// count[조회할 필드명 또는 아무거나]By[Where절에 =으로 들어갈 조건 필드명] : 개수 조회
// exist[조회할 필드명 또는 아무거나]By[Where절에 =으로 들어갈 조건 필드명] : boolean 타입으로 유무 검사
// delete[조회할 필드명 또는 아무거나]By[Where절에 =으로 들어갈 조건 필드명] : 삭제
public interface MemberRepository extends JpaRepository<Member, Long> {
// JpaRepository에게 엔티티와 PK의 데이터타입을 알려주어야 영속성 컨텍스트에 등록이 가능함(entityManager로 따로 persist해주지 않아도 됨)
    public List<Member> findByMemberNameContaining(String memberName);   // 메소드를 사용하면 이제 자동으로 알아서 쿼리가 나감
                                                                         // find(select) By(where) containing(LIKE) 등등
    public List<Member> findByMemberNameStartingWith(String memberName);
    public List<Member> findByMemberNameEndingWith(String memberName);
    public List<Member> findByMemberNameLike(String memberName);

//    Query
//    쿼리 메소드만 사용하면 메소드명이 너무 길어질 수 있기 때문에 @Query를 사용하여 원하는 쿼리를 JPQL로 작성하여 해결할 수 있다.
//    오타 혹은 문법에 오류가 있을 경우 컴파일 시 정확히 어떤 것이 오류인지가 콘솔에 정확히 나온다.

//    Parameter Binding
//    이름 기반 : JSQL에서 ":[파라미터명]"을 사용하면 매개변수 앞에 @Param([파라미터명])을 작성하여 매핑해준다.
    @Query("select m from Member m where m.memberName like :keyword")
    public void find(@Param("keyword") String keyword); // 위의 3개의 메소드를 이렇게 하나의 JPQL로 줄일 수 있음. 실무에서는 이렇게 더 많이 씀

    @Query("select m from Member m where m.memberType = :memberType and m.memberAge > :memberAge")
    public List<Member> findMemberbyMemberAge(@Param("memberType") Enum<MemberType> memberType, int memberAge);

    @Query("select m from Member m where m.memberName in :memberNames")     // in은 OR로 되기 때문에 전달한 이름에 해당하는 member 모두 리턴
    public List<Member> findMemberByNames(List<String> memberNames);

    // @Query의 리턴값의 디폴트가 ResultSet이기 때문에 수정을 원하는 경우(벌크연산) @Modifying()이라는 어노테이션을 붙여주어야 함
    // 벌크연산은 persist Context가 모르게 일어나기 때문에 데이터의 불일치가 생김(DB에서는 수정되지만 persist context에는 예전에 select한 값이 담겨있음)
    // clearAutomatically를 true로 설정해주어야 캐시를 다 비우고 새로 select해오면서 불일치성을 해결할 수 있음
    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.memberAge = m.memberAge - 1 where m.memberAge > :memberAge")
    public int updateByName(int memberAge);
}
