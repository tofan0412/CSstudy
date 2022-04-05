package kr.or.ddit.basic;

public class T02_LambdaTest {
	public static void main(String[] args) {
		// 람다식을 사용하지 않았을 경우..
		LambdaTestInterface1 lam1 = new LambdaTestInterface1() {

			@Override
			public void test() {
				System.out.println("안녕하세요");
				System.out.println("익명 구현 객체 방식입니다.");
			}
		};
		lam1.test(); // 메서드 호출
		
		LambdaTestInterface1 lam2 = () -> {
			System.out.println("안녕하세요\n람다식으로 처리하는 방식입니다.");
		};
		lam2.test();
		System.out.println("------------------------------");
		/*
		 * 람다식의 작성 방법 기본형식) (자료형이름 매개변수명, ...) -> {실행문; ...}
		 * 
		 * 1) 매개변수의 '자료형이름'은 생략할 수 있다. 예) (int a) -> { System.out.println(a); } (a) -> {
		 * System.out.println(a); }
		 * 
		 * 
		 * 2) 매개변수가 1개일 경우에는 괄호'()'를 생략할 수 있다. (단, '자료형이름'을 지정할 경우에는 괄호를 생략할 수 없다.) 
		 * 예) a -> {System.out.println(a);}
		 * 
		 * 3) '실행문'이 1개일 경우에는 '{ }'를 생략할 수 있다.
		 * (이 때, 문장의 끝을 나타내는 세미콜론(;)도 생략한다. )
		 * 예) a -> System.out.println(a)
		 * 
		 * 4) 매개변수가 하나도 없으면 괄호'( )'를 생략할 수 없다.
		 * 예) () -> System.out.println(a)
		 * 
		 * 5) 반환값이 있을 경우에는 return명령을 사용한다. 
		 * 에) (a,b) -> {return a+b; }
		 *    (a,b) -> return a+b
		 *  
		 * 6) 실행문에 return문만 있을 경우 return명령과 '{ }'를 생략할 수 있다. 
		 * 예) (a, b) -> a+b 
		 *  
		 */

		LambdaTestInterface2 lam3 = (int z) -> {
			int result = z + 100;
			System.out.println("result = " + result);
		};
		lam3.test1(30);

		LambdaTestInterface2 lam4 = z -> {	//int를 안 쓰고도 람다식을 표현할 수 있다. 
			int result = z + 300;
			System.out.println("result = " + result);
		};
		lam4.test1(60);	// 결과값으로 360이 나온다. 

		LambdaTestInterface2 lam5 = z -> System.out.println("result = " + (z + 500));	// int를 생략하였고, 소괄호 ()와 중괄호 {}를 생략하였다. 이는 
																						// statement가 오직 하나이기 때문에 가능하다. 
		lam5.test1(90);
		
		System.out.println("---------------------------------------");
		
		LambdaTestInterface3 lam6 = (int x, int y) -> {
			int r = x + y;
			return r;
		};
		int k = lam6.test1(20, 50);
		System.out.println("k=" + k);

		LambdaTestInterface3 lam7 = (x, y) -> {
			return x + y;	// 위와 달리 중간에 r = x+y를 생략하였다. 
		};
		k = lam7.test1(80, 50);
		System.out.println("k =" + k);

		LambdaTestInterface3 lam8 = (x, y) -> x + y;	// 중괄호 {} 생략(실행문이 하나라서 가능)
		k = lam8.test1(100, 200);
		System.out.println("k = " + k);

		LambdaTestInterface3 lam9 = (x, y) -> {
			return x > y ? x : y;	// x가 크면 x를, y가 크면 y를 출력한다.
		};
		k = lam9.test1(100, 200);
		System.out.println("k = " + k);
	}
}