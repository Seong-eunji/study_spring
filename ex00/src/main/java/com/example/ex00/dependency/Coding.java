package com.example.ex00.dependency;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
//@Data
@Getter
//@NoArgsConstructor        /* 기본 생성자 */
//@AllArgsConstructor       /* 전체 초기화 생성자 */
@RequiredArgsConstructor    /* 원하는 것만 초기화하는 생성자 */
public class Coding {
//    필드 주입
//    굉장히 편하게 주입할 수 있으나 순환 참조 시 오류가 발생하지 않기 때문에 StackOverFlow 발생. -> 서로 호출하면서 Stack영역이 넘칠 때까지 무한 루프
//    final을 붙일 수 없기 때문에 다른 곳에서 변형 가능 -> 스프링이 갖고 있는 값과 내가 갖고 있는 값이 다를 수 있게 됨(오류)
//    @Autowired
    private final Computer computer;    // final 혹은 @NonNull을 붙이면 무조건 초기화를 해야함
                                        // 기본 생성자를 가지고 있으면 final이나 @NonNull이 붙은 것도 값이 없어도 할당이 가능한 것처럼 보이기 때문에 오류가 남
                                        // 전체 초기화 생성자는 지금은 computer밖에 없지만 나중에 다른 변수가 존재하면 같이 초기화되어버림
                                        // 그래서 computer만을 초기화해주는 초기화생성자를 사용하여 주입받아야 하는데
                                        // 그럼 @RequiredArgsConstructor 어노테이션을 사용하는 것과 다를게 없음
                                        // final은 id, pw같은 사용자에게 화면에서 받아야 하는 값 말고
                                        // 스프링에게서 의존성 주입을 받아야하는 값에만 붙일 것

//    생성자 주입
//    순환 참조 시 컴파일러 인지 가능, 오류 발생
//    메모리에 할당되면서 초기값으로 주입되므로 final 키워드 사용 가능, 다른 곳에서 변형 불가능
//    의존성 주입이 되지 안으면 객체가 생성되지 않으므로 NPE 방어
//    생성자에 @Autowired 생략 가능
//    @Autowired  // 생략 가능, Autowired가 없어도 주입 받음, 생략이 가능하다는 얘기는 @RequiredArgsConstructor 어노테이션 사용해도 무방하다는 뜻
//    public Coding(Computer computer) {
//        this.computer = computer;
//    }
}
