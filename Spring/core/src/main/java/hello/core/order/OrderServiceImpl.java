package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {


    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // DIP에 위배된다. ServiceImpl이 구현 클래스에 의존하고 있으므로
    // 좋은 객체 지향 언어는 인터페이스에만 의존해야지, 구현 클래스에는 의존해선 안된다.

    // 따라서 코드를 다음과 같이 변경해 보자.
    // 철저하게 DIP를 지키고 있다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 어떤 discountPolicy가 들어올 지, 전혀 예상할 수 없다.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 단일 체계 원칙이 잘 구현된 예이다. 인터페이스를 잘게 쪼갠 것
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
