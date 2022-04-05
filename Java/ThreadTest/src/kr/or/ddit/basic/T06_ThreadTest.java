package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/**
 * 멀티 쓰레드를 활용한 카운트 다운 처리
 */
public class T06_ThreadTest {
	// 입력 여부를 확인하기 위한 변수 선언
	// 모든 쓰레드에서 공통으로 사용할 변수
	public static boolean inputCheck = false;

	public static void main(String[] args) {
		DataInput th1 = new DataInput();
		CountDown th2 = new CountDown();
		
		th1.start();
		th2.start();
	}
}

/**
 * 데이터를 입력하는 쓰레드
 */
class DataInput extends Thread {
	@Override
	public void run() {	//main에 해당한다. 
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
	
	// 입력이 완료되면 inputCheck 변수를 true로 변경한다. 
	T06_ThreadTest.inputCheck = true;
	
	System.out.println("입력한 값은 " + str + "입니다.");
	}
}


/**
 * 카운트다운을 처리하는 쓰레드 
 *
 */
class CountDown extends Thread	{

	@Override
	public void run() {
		for (int i = 10; i >= 1; i--) {
			// 입력이 완료되었는지 여부를 검사하고 입력이 완료되면 
			// run()메서드를 종료시킨다. 즉, 현재 쓰레드를 종료 시킨다.
			
			if(T06_ThreadTest.inputCheck) {
				return;	// run()메서드가 종료되면 쓰레드도 끝난다.
			}
			
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//10초가 경과되었는데도 입력이 없으면 프로그램을 종료한다. 
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0); // 프로그램을 종료시키는 명령
	}
}
