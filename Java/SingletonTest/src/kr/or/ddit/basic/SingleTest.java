package kr.or.ddit.basic;

public class SingleTest {
	public static void main(String[] args) {
//		MySingleton test1 = new Mysingleton();	// new 명령 사용 불가..
		
		// getInstance() 메서드를 이용하여 객체 생성
		MySingleton test2 = MySingleton.getInstance();
		test2.displayText();
		
		MySingleton test3 = MySingleton.getInstance();
		
		System.out.println("tyest2 => " + test2);
		System.out.println("tyest3 => " + test3);
		//test2와 test3은 서로 같은 객체를 나타낸다. 
	}
}
