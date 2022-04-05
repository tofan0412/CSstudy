package kr.or.ddit.basic;

public class T01_ThreadTest {
	public static void main(String[] args) {
		// Single-Thread Program 의 작성
		for (int i = 1; i <= 200; i++) {
			System.out.print("*");
		}
		
		System.out.println();
		
		for (int i = 1; i <= 200; i++) {
			System.out.print("$");
		}
	}
}
