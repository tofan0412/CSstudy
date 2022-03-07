package hello.core;

import hello.core.member.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        // MemberService memberService = new MemberServiceImpl();
        // ctrl alt v

        // Spring으로 전환하자.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 첫번째 인자는 찾고자 하는 Bean의 이름을, 두번째 인자는 해당 Bean의 타입을 뜻한다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + memberA.getName());
        System.out.println("findMember = " + findMember.getName());

    }
}
