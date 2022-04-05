package kr.or.ddit.basic;

public class T20_WaitNotifyTest {
	public static void main(String[] args) {
		DataBox dataBox = new DataBox();
		
		ProducerThread pth = new ProducerThread(dataBox);
		ConsumerThread cth = new ConsumerThread(dataBox);
		
		pth.start();
		cth.start();
	}
}


// 데이터를 공통으로 사용하는 클래스
class DataBox {
	private String data;
	
	// data가 null이 아닐 때 data값을 반환하는 메서드
	public synchronized String getData() {
		if (data == null) {	// 데이터가 세팅되어 있지 않다면
			try {
				wait();	// 없는 경우에는 가져올 데이터가 없으므로, 대기한다...
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String returnData = data;	// 데이터가 존재한다면, returndata로 가져와서 출력한다. 
		System.out.println("읽어온 데이터 : " + returnData);
		data = null;	// 데이터를 소모했다는 뜻으로, (가져왔다는 뜻으로) null로 바꾼다. (비워 놓는다.)
		System.out.println(Thread.currentThread().getName() + "notify() 호출");
		
		notify();	//Wait-set에 대기하고 있는 쓰레드(setData)를 깨운다.
		
		return returnData;
	}
	
	// 서로 notify()를 통해 주거니 받거니... 한다.
	
	
	// data가 null일 경우에만 자료를 세팅하는 메서드
	public synchronized void setData(String data) {
		if(this.data != null) {
			try {
				wait();	// 이미 데이터가 존재하므로, 입력할 필요가 없다. 따라서 대기한다..
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.data = data;	// 그 외에는 데이터를 입력한 String값으로 처리한다. 
		System.out.println("세팅한 데이터 : " + this.data);
		System.out.println(Thread.currentThread().getName() + "notify() 호출");
		notify();	// 데이터를 입력했으니, 대기중인 쓰레드(getData)를 깨운다. 
	}
}


//	데이터를 세팅만 하는 쓰레드
class ProducerThread extends Thread{
	private DataBox dataBox;
	
	public ProducerThread(DataBox dataBox) {
		super("ProducerThread");	// 쓰레드의 이름을 설정한다. 
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			String data = "Data-" + i;	// Data-1, Data-2, Data-3, Data-4... 와 같이 데이터를 세팅해서 입력해준다.
			System.out.println("dataBox.setData(" + data + ") 호출");
			dataBox.setData(data);
		}
	}
}

// 데이터를 읽어만 오는 쓰레드
class ConsumerThread extends Thread {
	private DataBox dataBox;

	public ConsumerThread(DataBox dataBox) {
		super("ConsumerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			String data = dataBox.getData();	// 데이터를 가져오기만 한다. 
			System.out.println("dataBox.getData() : " + data);
		}
	}
}
