package kr.or.ddit.basic;

public class T15_SyncThreadTest {
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		
		WorkerThread th1 = new WorkerThread("1번쓰레드", sObj);
		WorkerThread th2 = new WorkerThread("2번쓰레드", sObj);
		
		th1.start();
		th2.start();
		
		// 임계영역이 발생하면 동기화 작업을 진행해야 한다. 
	}
}


// 공통으로 사용할 객체
class ShareObject {
	private int sum = 0 ;
	
	public void add() {	// synchronized 를 붙여주면 동기화가 가능하다. 
		synchronized (this) {	// 동기화 블럭을 이용하는 방법
			for (int i = 0; i < 1000000000; i++) {}	// 동기화처리 전까지의 시간 지연용
			int n = sum;
			n += 10;	// 10증가
			sum = n;
			System.out.println(Thread.currentThread().getName() + "합계:" + sum); 
		}
	}
	
	
	public int getSum()	{
		return sum;
	}
}

// 작업을 수행하는 쓰레드
class WorkerThread extends Thread{
	ShareObject sObj;
	
	public WorkerThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			sObj.add();
		}
	}
}

