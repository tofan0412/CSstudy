package kr.or.ddit.basic;

class Util2{	
	// 의미는 Number라는 클래스를 상속받는, 타입은 상관없는(Number도 물론 포함)
	// Number 클래스와 Number 클래스를 상속받는 하위 클래스(Wrapper 클래스)가 T로 올 수 있다.
	public static <T extends Number> int compare(T t1, T t2) {
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		return Double.compare(v1, v2);
	}
}


/**
 * 제한된 타입 파라미터(Bounded Type Parameter) 예제
 *
 */
public class T04_GenericMethodTest {
	public static void main(String[] args) {
		int result1 = Util2.compare(10, 20);	// 오토박싱 . 자동으로 Integer 객체로 변환된다. 
		System.out.println(result1);
		
		int result2 = Util2.compare(3.14, 3);	// 오토박싱
		System.out.println(result2);
		
//		Util2.compare("C", "java");	// 에러발생. Number를 extends하는 객체가 아니므로(String) 에러가 발생한다. 
		
	}
}
