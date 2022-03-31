package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // 자동으로 Bean을 등록하기 위한 어노테이션
        basePackages = "hello.core",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // 예외 대상 적용. AppConfig를 등록하지 않기 위해..
)
public class AutoAppConfig {
    // 기존의 AppConfig와 다르게, @Bean으로 등록한 클래스가 하나도 없다.
}
