package kr.or.ddit.basic;

/**
 * 쓰레드의 상태 변화를 체크하기 위한 클래스
 */
/*
 * <쓰레드의 상태>
 * NEW : 쓰레드가 생성되고 아직 start()가 호출되지 않은 상태. 생성만 된 상태
 * RUNNABLE : 실행 중 또는 실행 가능한 상태
 * BLOCKED : 동기화 블럭에 의해서 일시정지된 상태(lock이 풀릴 때까지 기다리는 상태)
 * WAITING, TIME_WAITING : 쓰레드의 작업이 종료되지는 않았지만 실행가능하지 않은 (UNRUNNABLE)
 * 일시정지인 상태.
 * TERMINATED : 쓰레드의 작업이 종료된 상태. 
 * 
 */

/**
 * 쓰레드의 상태를 출력하는 예제
 */
public class T10_ThreadStateTest {
	public static void main(String[] args) {
		StatePrintThread spt = new StatePrintThread(new TargetThread());
		spt.start();
	}
}

// 쓰레드의 상태를 출력하는 클래스( 이 클래스도 쓰레드 클래스로 작성)
class StatePrintThread extends Thread {
	private Thread targetThread;	// 상태를 출력할 쓰레드가 저장될 변수.
	
	public StatePrintThread(Thread targetThread) {
		this.targetThread = targetThread;
	}
	
	@Override
	public void run() {
		while(true)	{
			// Thread의 상태 구하기(getState() 메서드를 이용한다.)
			Thread.State state = targetThread.getState();
			System.out.println("타겟 쓰레드의 상태값 : " + state);
			
			// NEW 상태인지 검사
			if (state == Thread.State.NEW)	{
				targetThread.start(); 	// 쓰레드를 시작한다. Call Stack에서 새로운 스택이 생성된다. 
			}
			
			// 타겟 쓰레드가 종료 상태인지 검사
			if (state == Thread.State.TERMINATED) {
				break;	// while 무한 루프를 벗어난다. 
			}

			try {
				Thread.sleep(500);	// 0.5초마다 쓰레드의 상태를 확인한다. 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

// Target용 쓰레드
class TargetThread extends Thread {
	@Override
	public void run() {
		for (long i = 1; i < 1000000000L ; i++) {}	// 시간 지연용. 여기서는 RUNNABLE 상태이다. 
		try {
			Thread.sleep(1500);	// 여기서 TIMED_WAITING 상태이다.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (long i = 1; i < 1000000000L ; i++) {}	// 시간 지연용. 여기서 RUNNABLE이 체크된다.
	}
}

