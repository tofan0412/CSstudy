package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음. 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {
    // Singleton이기 때문에 static 키워드가 빠져도 상관없다. (오직 하나의 MemberRepository 객체만을 갖기 때문에)
    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    // Singleton Pattern 적용
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {

    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        // 원본을 건드리고 싶지 않기 때문에, 새로운 리스트 객체를 만들어서 반환한다.
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
