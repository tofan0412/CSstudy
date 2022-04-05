package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/**
 * 단일 쓰레드에서의 사용자 입력 처리
 * 
 */
public class T05_ThreadTest {
	public static void main(String[] args) {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요");
		System.out.println("입력한 값은 " + str + "입니다.");

		for (int i = 10; i >= 1; i--) {
			try {
				System.out.println(i);
				Thread.sleep(1000); // 1초동안 잠시 멈춘다.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
