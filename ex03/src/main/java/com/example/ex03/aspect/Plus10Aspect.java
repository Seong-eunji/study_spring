package com.example.ex03.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Configuration  // 설정파일로 들어감
@Aspect         // Aspect인 것을 알려주어야 함
@Slf4j
public class Plus10Aspect {     // 클래스(구현체) 이름 뒤에 Aspect라고 써줘야 구분 가능

//    JointPoint(시점, 관점)
    @Around("@annotation(com.example.ex03.aspect.Plus10)")
    // 리턴값이 필요할 때에는 AfterReturning을 써도 되지만 보통은 Around라고 많이 씀
    // 괄호 안에 원래는 execution을 써야하지만 그냥 @annotation을 붙여주면 생략 가능, 그 뒤에 경로를 알려줌
    public Integer aroundPlus10(ProceedingJoinPoint proceedingJoinPoint) {
        // 프록시 : 가짜 객체 느낌, 스프링이 주입해주는 객체
        //         원본을 함부로 훼손시킬 수 없으므로 복사해서 그 복사본을 바꿔 사용하는 것
        //         주소값을 복사해옴
        // proceedingJoinPoint가 파라미터로 들어올 때에 프록시를 받아옴
        // proceedingJoinPoint : join(합류) point(시점)
        // aroudnPlus10의 JoinPoint는 Around이고, PointCut은 doAdd()라고 할 수 있음
        // doAdd()를 실행하면서 JoinPoint가 일어나고 PointCut 대상인 doAdd()의 모든 것을 파라미터로 가져옴

        Integer result = 0;

        try {
            result = (Integer)proceedingJoinPoint.proceed();  // Service의 doAdd()를 실행시키는 것으로 이 통쨰로가 리턴값이 됨
        } catch (Throwable throwable){  // throwable은 Exception의 부모
            throwable.printStackTrace();
        }

        return result + 10;
    }
}
