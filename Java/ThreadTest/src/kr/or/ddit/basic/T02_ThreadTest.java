package kr.or.ddit.basic;

public class T02_ThreadTest {
	public static void main(String[] args) {
		//Multi-Thread Program 작성
		
		// 방법 1 : Thread클래스를 상속한 class의 인스턴스를 생성한 후 
		//         이 인스턴스의 start() 메서드를 호출한다. 
		MyThread1 th1 = new MyThread1();
//		th1.start();	// 쓰레드가 동작한다.
		
		// 방법 2 : Runnable인터페이스를 구현한 class의 인스턴스를 생성한 후 
		//         이 인스턴스를 Thread객체의 인스턴스를 생성할 때 생성자의
		//         매개변수로 넘겨준다. 
		//         이 때 생성된 Thread객체의 인스턴스의 start()메서드를 호출한다. 
		MyThread2 r1 = new MyThread2();
		Thread th2 = new Thread(r1);
//		th2.start();
		
		
		// 방법 3 : 익명클래스를 이용하는 방법
		// Runnable인터페이스를 구현한 익명클래스를 Thread인스턴스를
		// 생성할 때 매개변수로 넘겨준다. 
		
		Thread th3 = new Thread(new Runnable() {
			@Override	// 쓰레드 객체에서 main 역할을 하는 게 바로 run 메서드이다. 
			public void run() {
				for (int i = 1; i <= 200; i++) {
					System.out.print("@");
					try {
						// Thread.sleep(시간) => 주어진 시간 동안 작업을 잠시 멈춤.
						// 시간은 밀리세컨드 단위를 사용한다. 
						// 즉 1000ms은 1초를 의미한다.
						Thread.sleep(100);
					}catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		th1.start();
		th2.start();
		th3.start();
		
//		th1.run();	// 이렇게 하면 별도의 call stack을 만들지 않고, main thread가 run()을 실행하게 된다. 
//		th2.run();
//		th3.run();
		
		
		System.out.println("main 메서드 작업 끝..");
	}
}

class MyThread1 extends Thread{
	
	@Override	// 쓰레드 객체에서 main 역할을 하는 게 바로 run 메서드이다. 
	public void run() {
		for (int i = 1; i <= 200; i++) {
			System.out.print("$");
			try {
				// Thread.sleep(시간) => 주어진 시간 동안 작업을 잠시 멈춤.
				// 시간은 밀리세컨드 단위를 사용한다. 
				// 즉 1000ms은 1초를 의미한다.
				Thread.sleep(100);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// super.run();
	}
}

class MyThread2 implements Runnable {

	@Override
	public void run() {
		for (int i = 1; i <= 200; i++) {
			System.out.print("*");
			try {
				// Thread.sleep(시간) => 주어진 시간 동안 작업을 잠시 멈춤.
				// 시간은 밀리세컨드 단위를 사용한다.
				// 즉 1000ms은 1초를 의미한다.
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
