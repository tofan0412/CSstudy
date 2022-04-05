package kr.or.ddit.basic;
/**
 * T16에서 synchronized와 거의 유사한 클래스.. 다만 lock을 이용해서 동기화를 진행한다. 
 * 차이점 : lock은 직접 걸고, unlock 또한 필요하다. 다른 메서드나 인스턴스를 호출하더라도, 아직 lock을 풀지 않았다면
 * 해당 영역 내에서는 동기화가 유지된다. 따라서 synchronized는 최대 범위가 하나의 메서드이지만, 
 * lock은 unlock만 하지 않으면 계속 동기화가 유지된다. 따라서 더 넓은 범위, 더 오랜 시간 동안 동기화를 유지할 수 있다. 
 * 단점 : unlock을 하지 않으면 다른 쓰레드는 들어올 수도 없다. 
 * 임계영역의 시작 부분에 lock()을 시작한다. 
 *  
 * 
 */
import java.util.concurrent.locks.ReentrantLock;

public class T17_LockAccountTest {
	public static void main(String[] args) {
		LockAccount lAcc = new LockAccount();
		lAcc.setBalance(10000);
		
		BankThread2 bth1 = new BankThread2(lAcc);
		BankThread2 bth2 = new BankThread2(lAcc);
		
		bth1.start();
		bth2.start();
	}
}

// 입출금을 담당하는 클래스
class LockAccount{
	private int balance;	// 잔액이 저장될 변수

	// lock 객체 생성 => 되도록이면 private final로 만든다. 
	private final ReentrantLock lock = new ReentrantLock();
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	// 입금하는 메서드
	public void deposit(int money) {
		// Lock 객체의 lock() 메서드가 동기화 시작이고, unlock() 메서드가 
		// 동기화의 끝을 나타낸다.
		// lock() 메서드로 동기화를 설정한 곳에서는 반드시 unlock() 메서드로 
		// 해제해 주어야 한다. 
		lock.lock();  // 동기화 시작
		
		balance += money;	// 동기화 처리 부분
		lock.unlock();	//동기화 해제
	}
	
	// 출금 하는 메서드 ( 출금성공 : true, 출금실패 : false 반환 )
	public boolean withdraw(int money) {
		lock.lock();
		boolean chk = false;
		// try ~ catch 블럭을 사용할 경우에는
		// unlock()메서드 호출은 finally 블럭에서 하도록 한다. 
		try {
			if(balance >= money) {
				for (int i = 1; i < 1000000000; i++) {}	// 시간 때우기
				balance -= money;
				System.out.println("메서드 안에서 balance = " + getBalance());
				chk = true;
			} 
		} catch (Exception e) {
			chk = false;
		}finally {
			lock.unlock();	// 동기화의 해제
		}
		return chk;
	}
}

class BankThread2 extends Thread {
	private LockAccount lAcc;
	
	public BankThread2(LockAccount lAcc) {
		this.lAcc = lAcc;
	}
	
	@Override
	public void run() {
		boolean result = lAcc.withdraw(6000);
		System.out.println("쓰레드 안에서 : result = " 
		                   + result + ", balance = " 
				           + lAcc.getBalance()); 
	}
	
}