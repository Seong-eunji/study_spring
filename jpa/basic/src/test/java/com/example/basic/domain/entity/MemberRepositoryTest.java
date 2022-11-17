package com.example.basic.domain.entity;

import com.example.basic.repository.MemberRepository;
import com.example.basic.type.MemberType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@Slf4j
@Transactional      // 쿼리가 나가야 하는데 안 나가면 거의 대부분이 구현체의 Transactional이 다르거나 어노테이션이 없는 경우가 많으므로
                    // 그냥 매번 걸어주는게 편함, 그렇지만 매번 메소드에 걸기 불편하니까 그냥 클래스에 통째로 거는게 젤 편함
@Rollback(false)    // 단위테스트에서는 자동 롤백이 되므로 false 해주기
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest(){
        /*Member member = new Member();
        member.create("한동석", "tedhan@gmail.com", "1234", 20, MemberType.ADMIN);
        log.info("saved member : " + memberRepository.save(member));*/

        Member member1 = new Member();
        Member member2 = new Member();
        member1.create("한동석", "tedhan1204@gmail.com", "1234", 20, MemberType.ADMIN);
        member2.create("홍길석", "hgs1111@gmail.com", "0000", 30, MemberType.MEMBER);
        log.info("saved member: " + memberRepository.save(member1));
        log.info("saved member: " + memberRepository.save(member2));
    }

    @Test
    public void findByIdTest(){
        assertThat(memberRepository.findById(1L)).isNotEmpty();
    }

    @Test
    public void findAllTest(){
        assertThat(memberRepository.findAll().stream().map(Member::getMemberName).collect(Collectors.toList()).get(0)).isEqualTo("한동석");
    }

    @Test
    public void updateTest(){
        memberRepository.findById(1L).get().setMemberPassword("0000");  // 프로젝트 할 때에는 Optional로 검증하기
    }

    @Test
    public void deleteTest(){
        memberRepository.deleteById(1L);
    }

    // Modifying에서 clearAutomatically를 true로 옵션을 줘서 불일치성을 해결하는데
    // 캐시를 비운 이후에 다시 불러와야 데이터가 일치되기 때문에 벌크연산(수정) 메소드를 실행한 후에
    // 다시 find() 메소드를 사용하여 select해와야함
    // clearAutomatically 옵션을 주지 않을 경우 벌크연산(수정) 사용 전 find()와 같기 때문에
    // 새로 쿼리를 날리지 않고 캐시에 담겨있는 값을 가져오기 때문에 불일치성이 생겨서 값 비교에 오류가 생김
    // 벌크연산(삭제)의 경우에는 DB에서는 값이 삭제되었는데 캐시에는 여전히 값이 담겨있는 경우가 있어서 조심해야 함

    @Test
    public void findByMemberNameContainingTest(){
        memberRepository.findByMemberNameContaining("석").stream().map(Member::toString).forEach(log::info);
    }

    @Test
    public void findByMemberNameStartingWithTest(){
        memberRepository.findByMemberNameStartingWith("석").stream().map(Member::toString).forEach(log::info);
    }
}
