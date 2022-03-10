package hello.core.singleton;

public class SingletonService {
    // 1. 자기 자신을 클래스 Level에서 생성한다. (Static을 통한 클래스 변수 선언)
    private static final SingletonService instance = new SingletonService();

    // 조회할 때는 얘를 쓰면 된다. 이 경우
    public static SingletonService getInstance() {
        return instance;
    }

    // Constructor의 private 설정을 통해 외부에서 new 연산자로 생성되는 것을 막는다.
    private SingletonService() {

    }

    // 메서드이다.
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
