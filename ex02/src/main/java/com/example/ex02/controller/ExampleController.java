package com.example.ex02.controller;

import com.example.ex02.domain.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/ex/*")
public class ExampleController {

    @RequestMapping(value = "ex01", method = {RequestMethod.GET, RequestMethod.POST})
    public void ex01(){
        log.info("ex01...........");
    }

    @GetMapping("ex02")
    public void ex02(HttpServletRequest request){
        log.info("name: " + request.getParameter("name"));
    }

    @GetMapping("ex03")
    public void ex03(String name){
        log.info("name: " + name);
    }

    @GetMapping("ex04")
    public void ex04(MemberVO memberVO){
        log.info("member: " + memberVO);
    }

    @GetMapping("ex05")
    public void ex05(@RequestParam("id") String name, int age){
        log.info("name: " + name);
        log.info("age " + age);
    }

    @GetMapping("ex06")
    public void ex06(MemberVO memberVO){
        log.info("ex06.................");
    }

    @GetMapping("ex07")
    public void ex07(@ModelAttribute("gender") String gender, MemberVO memberVO){
        log.info("ex07.................");
        log.info("memberVO:" + memberVO);
        log.info("gender:" + gender);
    }

    @GetMapping("ex08")
    public void ex08(@RequestParam("data") List<String> datas){ // 화면에서 data라는 이름으로 넘어온 건 모두 datas에 담아줘라
        log.info(datas.toString());                             // 원래 name은 중복되면 안되지만
                                                                // name이 중복되어 보내는 경우는 배열이나 리스트로 받을 수 있음
                                                                // 화면 : localhost:10002/ex/ex08?data=한동석&data=홍길동&data=이순신
                                                                // 출력 : [한동석, 홍길동, 이순신]
    }

    @GetMapping("ex09")
    public void ex09(MemberVO memberVO){ // 일반 Wrapper타입이 아닌 개발자가 지정한 타입(VO)을 제네릭에서 사용할 경우에는
         log.info()               // 파라미터 안에서 바로 리스트로 받을 수 없기 때문에 VO안에서 List를 선언해주고
                        // 그 필드에 접근하여 넣어주도록 지정해주어야함
                        // members[0].name=한동석 ('members[0].name'까지가 키값)
                        // members[0].age=20

    }

//    [실습 1]
//    외부에서 상품명, 상품가격, 상품재고, 브랜드 전달받아서 화면에 전송
//    화면에서 4개 정보 입력 후 form태그로 전송한다.

//    [실습 2]
//    TaskVO 선언
//    int num, int kor, int eng, int math 선언
//    총점과 평균 점수 화면에 출력

//    [실습 3]
//    아이디와 비밀번호를 입력받은 후 아이디가 admin일 경우 admin.html로 이동
//    아이디가 user일 경우 user.html로 이동
//
//    - login.html : 아이디와 비밀번호 입력 페이지 출력
//    - admin.html : 관리자 페이지 출력
//    - user.html : 일반 회원 페이지 출력

//    [실습 4]
//    이름을 입력하고 출근 또는 퇴근 버튼을 클릭한다.
//    출근 시간은 09:00이며, 퇴근 시간은 17:00이다.
//    출근 버튼 클릭 시 9시가 넘으면 지각으로 처리하고,
//    퇴근 버튼 클릭 시 17시 전이라면 퇴근이 아닌 업무시간으로 처리한다.
//    - getToWork.html
//    - leaveWork.html
//    - late.html
//    - work.html

    @GetMapping("task01")
    public String task01(){
        return "/ex/task01";
    }

    @PostMapping("task01")
    public String task01(ProductVO productVO){
        return "/ex/task01_result";
    }

    @GetMapping("task02")
    public String task02(){
        return "/ex/task02";
    }

    @PostMapping("task02")
    public String task02(TaskVO taskVO){
        return "/ex/task02_result";
    }

    @GetMapping("login")
    public String login(){
        return "/ex/login";
    }

    @PostMapping("login")
    public String login(UserVO userVO){
        return userVO.getId().equals("admin") ?  "/ex/admin" : "/ex/user";
    }

    @GetMapping("work")
    public String work(){
        return "/ex/work";
    }

    @PostMapping("work")
    public String work(WorkerVO workerVO){
        return "";
    }


}



















