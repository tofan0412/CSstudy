package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: A 사용자가 10000원을 주문했다.
        statefulService1.order("userA", 10000);
        // ThreadB : B 사용자가 20000원을 주문했다.
        statefulService2.order("userB", 20000);

        // ThreadA: 사용자A가 주문 금액을 조회한다.
        int price = statefulService1.getPrice(); // 이 경우 B 사용자가 주문한 금액인 20000원이 발생한다.
        System.out.println(price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}