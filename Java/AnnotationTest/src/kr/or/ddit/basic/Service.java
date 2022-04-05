package kr.or.ddit.basic;

/**
 * 애너테이션의 사용 예제
 * 주석처럼 달지만, 유용한 정보를 제공한다. 
 */
public class Service {
	@PrintAnnotation	// 파라미터 없음. 디폴트 값으로 '-', count의 경우 20으로 설정.
	public void method1() {
		System.out.println("메서드1에서 출력되었습니다.");
	}
	
	@PrintAnnotation(value="%")	//원래는 이렇게 해야 하나, value의 경우에만 오직 한 개가 있을 때 생략이 가능하다. 
	public void method2() {
		System.out.println("메서드2에서 출력되었습니다.");
	}
	
	@PrintAnnotation(value="#", count=25)
	public void method3() {
		System.out.println("메서드3에서 출력되었습니다.");
	}
	
}


