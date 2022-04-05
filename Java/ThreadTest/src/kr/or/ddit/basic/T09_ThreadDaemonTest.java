package kr.or.ddit.basic;

public class T09_ThreadDaemonTest {

	public static void main(String[] args) {
		Thread autoSave = new AutoSaveThread();

		// 데몬스레드로 설정하기(start() 메서드를 호출하기 전에 설정한다. )
		autoSave.setDaemon(true);	//데몬 스레드로 설정되었기 때문에,main Thread가 죽으면 동시에 죽는다. 
		autoSave.start(); // 시작

		try {
			for (int i = 1; i <= 20; i++) {
				System.out.println("작업" + i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("메인 쓰레드 종료");
	}
}


/**
 * 자동 저장하는 기능을 제공하는 스레드 클래스
 */
class AutoSaveThread extends Thread {
	public void save() {
		System.out.println("작업 내용을 저장합니다.");
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			save(); // 저장 기능을 호출한다.
		}
	}
}
