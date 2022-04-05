package kr.or.ddit.basic;

import java.lang.invoke.SwitchPoint;
import javax.swing.JOptionPane;

/*
 * 컴퓨터와 가위바위보를 진행하는 프로그램을 작성하시오.
 * 컴퓨터의 가위바위보는 난수를 이용하여 구하고
 * 사용자의 가위 바위 보는 showInputDialog() 메서드를 이용하여 입력 받는다. 
 * 
 * 입력 시간은 5초로 제한하고 카운트다운을 진행한다. 
 * 5초 안에 입력이 없으면 게임을 진 것으로 처리한다. 
 * 
 * 5초 안에 입력이 완료되면 승패를 출력한다. 
 * 
 * 결과예시)
 * === 결과 ===
 * 컴퓨터 : 가위
 * 당  신 : 바위
 * 결  과 : 당신이 이겼습니다. 
 */
public class T07_ThreadGame {
	public static boolean input = false;
	public static boolean regame = false;
	public static String user = " ";
	public static String com = " ";

	/**
	 * 컴퓨터의 결과를 저장하는 메서드
	 */
	public static void getRndNum() {
		int random = (int) (Math.random() * 3);

		if (random == 0) {
			com = "가위";
		} else if (random == 1) {
			com = "바위";
		} else {
			com = "보";
		}
	}

	/**
	 * 사용자가 낸 값과 컴퓨터가 낸 값을 비교하는 메서드
	 */
	public static String calcResult(String user) {
		String result = " ";
		if (user.equals("가위")) {
			if (com.equals("가위")) {
				result = "비겼습니다.";
				T07_ThreadGame.regame = true;
			} else if (com.equals("바위")) {
				result = "졌습니다.";
				T07_ThreadGame.input = true;
			} else {
				result = "이겼습니다.";
				T07_ThreadGame.input = true;
			}
		} else if (user.equals("바위")) {
			if (com.equals("가위")) {
				result = "이겼습니다.";
				T07_ThreadGame.input = true;
			} else if (com.equals("바위")) {
				result = "비겼습니다.";
			} else {
				result = "졌습니다.";
				T07_ThreadGame.input = true;
			}
		} else {
			if (com.equals("가위")) {
				result = "졌습니다.";
				T07_ThreadGame.input = true;
			} else if (com.equals("바위")) {
				result = "이겼습니다.";
				T07_ThreadGame.input = true;
			} else {
				result = "비겼습니다.";
			}
		}
		return result;
	}

	public static void main(String[] args) {
		getRndNum(); // 컴퓨터가 계산한 결과를 클래스 변수인 com에 저장
		Thread th1 = new Input(); // 사용자의 입력 받음
		Thread th2 = new CountDown1(); // 카운트다운
		th1.start();
		th2.start();
	}
}

/**
 * 사용자로부터 입력 받아서 결과를 출력하는 클래스
 */
class Input extends Thread {
	@Override
	public void run() {
		while (true) {
			T07_ThreadGame.user = JOptionPane.showInputDialog("가위, 바위, 보 중 하나를 입력하세요");
			// 사용자가 입력한 값이 가위, 바위, 보 중 아무것도 아니면 다시 입력한다.
			if (!T07_ThreadGame.user.equals("가위") && !T07_ThreadGame.user.equals("바위")
					&& !T07_ThreadGame.user.equals("보")) {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				continue;
			} else if (T07_ThreadGame.user == "끝") {
				System.exit(0);
			} else {
//				T07_ThreadGame.input = true;
				System.out.println(T07_ThreadGame.user + "를 선택하셨습니다.");

				String result = T07_ThreadGame.calcResult(T07_ThreadGame.user);

				System.out.println("===결과===");
				System.out.println("사용자 : " + T07_ThreadGame.user);
				System.out.println("컴퓨터 : " + T07_ThreadGame.com);
				System.out.println(result);
				break;
			}
		}
	}
}

/**
 * 카운트 다운 하는 클래스
 *
 */
class CountDown1 extends Thread {
	@Override
	public void run() {

		for (int i = 5; i >= 1; i--) {
			if (T07_ThreadGame.regame) {
				i += 5 - i;
				//
				Input th = new Input();
				T07_ThreadGame.getRndNum(); // 컴퓨터가 계산한 결과를 클래스 변수인 com에 저장
				th.start();
				T07_ThreadGame.regame =  false;
			}
			
			if (T07_ThreadGame.input) { // 사용자가 입력을 했다면 해당 쓰레드를 종료한다.
				return;
			}

			System.out.println(i);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 사용자가 5초동안 입력을 하지 않았다면 패배처리한다.
		System.out.println("제한 시간이 지났습니다.");
		System.out.println("패배하였습니다.");
		System.exit(0);
	}

}