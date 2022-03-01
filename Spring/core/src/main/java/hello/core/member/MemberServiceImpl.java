package hello.core.member;

public class MemberServiceImpl implements MemberService {
    // 오로지 추상화에만 의존하며, DIP를 의존한다.
    // 생성자 주입
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
