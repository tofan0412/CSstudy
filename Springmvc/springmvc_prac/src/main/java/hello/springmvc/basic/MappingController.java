package hello.springmvc.basic;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MappingController {

    @RequestMapping(value = "/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mapping-get-v1");
        return "ok";
    }

    /**
     * 편리한 축양 애노테이션
     *
     * @return
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }

    /**
     * PathVariable 사용
     * 변수명이 같으면 생략이 가능하다.
     *
     * @return
     * @PathVariable("userId") String userId -> @PathVariable userId
     * ex) /mapping/userA
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    /**r
     * 다중 PathVariable 적용
     */
    @GetMapping("/mapping/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable String orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * mode라는 쿼리 파라미터를 활용해 다음과 같이 API를 작성할 수 있다.
     * 반드시 쿼리 파라미터에서 mode라는 변수가 존재해야 하며, 해당 변수의 값이 debug일 때만 이 API가 호출된다.r
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더 조건이 만족될 때만 API가 호출된다.
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type에 맞춰 API를 호출할 수 있다.
     * PostMan으로 테스트해야 한다.
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingCOnsumes");
        return "ok";
    }

    /**
     * 해당 API가 생산하는 produce의 타입에 따라 API를 호출할 수 있다.
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }

}
