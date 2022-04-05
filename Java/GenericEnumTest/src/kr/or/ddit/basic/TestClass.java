package kr.or.ddit.basic;

public class TestClass {
	// enum을 사용하지 않을 때
	public static final int ZERO = 0;
	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int THREE = 3;

	public static final int LION = 0;
	public static final int TIGER = 1;

	public static void main(String[] args) {
		if (LION == 0) {	// 컴파일러 입장에서, LION과 ZERO는 모두 0이며, 이는 결국 0 == 0 을 의미한다.
			// 타입 체크가 되지 않는다.
			System.out.println("ZERO입니다. ");
		}
	}
}
