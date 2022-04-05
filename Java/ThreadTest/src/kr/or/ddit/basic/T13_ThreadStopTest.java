package kr.or.ddit.basic;

/*
 * Thread의 stop() 메서드를 호출하면 쓰레드가 바로 멈춘다. 
 * 이 때 사용하던 자원을 정리하지 못하고 프로그램이 종료되기 때문에 나중에 실행되는 
 * 프로그램에 영향을 줄 수 있다. 
 * 따라서 현재는 stop() 메서드를 추천하지 않는다. (Deprecated)
 */

public class T13_ThreadStopTest {
	public static void main(String[] args) {
		ThreadStopEx1 th = new ThreadStopEx1();
		th.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
//		th.stop();//추천되지 않는다. 
		th.setStop(true);
//		---------------------------------------
		// Interrupt() 메서드를 이용해서 스레드 멈추기
		ThreadStopEx2 th2 = new ThreadStopEx2();
		th2.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		th2.interrupt();  // 인터럽트 처리
	}
}


class ThreadStopEx1 extends Thread {
	private boolean stop;
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	@Override
	public void run() {
		while(!stop) {
			System.out.println("쓰레드 처리 중...");
		}
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료.");
	}
	
	
}

// interrupt()를 이용하여 쓰레드를 멈추게 하는 방법
class ThreadStopEx2 extends Thread {
	@Override
	public void run() {
//		// 방법 1 : sleep() 메서드나 join() 메서드 등을 사용했을 때
//		//		   interrupt() 메서드를 호출하면 
//		//         InterruptedException 이 발생한다. 
//		try {
//			while(true) {
//				System.out.println("쓰레드 처리중... ");
//				Thread.sleep(1);
//			}
//		} catch (InterruptedException e) { }	//누가 인터럽트를 걸면 catch로 빠져 나가게 된다.
		
		
		// 방법2 -> interrupt() 메서드가 호출되었는지 검사하기
		while(true) {
			System.out.println("쓰레드 처리 중...");
			
			// 검사방법 1 -> 쓰레드의 인스턴스객체용 메서드를 이용하는 방법
//			if(this.isInterrupted()) {	// 내가 걸렸는지 안걸렸는지를 체크한다. 
//				//interrupt() 메서드가 호출되면 true
//				System.out.println("인스턴스용 isInterrupted()");
//				break;
//			}
			
			//검사방법 2 -> 쓰레드의 정적 메서드를 이용하는 방법
			if (Thread.interrupted()) { 
				//interrupt() 메서드가 호출되면 true
				System.out.println("정적 메서드 interrupted()");	// 클래스 메서드라는 점이 중요하다.
				// 자기 자신이 아닌 다른 쓰레드가 호출한 경우 값이 변경되어 있기 때문에, 2번째 호출부터 주의가 필요하다. 
				break;
			}
		}
	}
}





















