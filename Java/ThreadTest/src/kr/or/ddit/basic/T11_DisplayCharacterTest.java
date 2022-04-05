package kr.or.ddit.basic;

/*
 * 3개(명)의 쓰레드가 각각 알파벳대문자를 출력하는데
 * 출력을 끝낸 순서대로 결과를 나타내는 프로그램을 작성하기
 * 
 */
public class T11_DisplayCharacterTest {
	static String strRank = "";
	
	public static void main(String[] args) {
		DisplayCharacter[] disChars = new DisplayCharacter[] {
				new DisplayCharacter("홍길동"),	// 하나의 쓰레드이다.
				new DisplayCharacter("일지매"),	// 하나의 쓰레드이다.
				new DisplayCharacter("변학도"),	// 하나의 쓰레드이다.
		};
		
		// 3개의 쓰레드를 실행한다. 
		for (int i = 0; i < disChars.length; i++) {
			disChars[i].start();
		}
		
		// 메인 쓰레드가 3개가 종료될 때까지 기다린다. 
		for(DisplayCharacter dc : disChars) {
			try {
				dc.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("경기 끝");
		System.out.println("------------------");
		System.out.println();
		System.out.println("경기 결과");
		System.out.println("순위 : " + strRank);
	}
}


// 영어 대문자를 출력하는 쓰레드 클래스
class DisplayCharacter extends Thread{
	private String name;
	
	public DisplayCharacter(String name) {	// 생성자
		this.name = name;
	}
	
	// 대문자를 하나 출력하고, 임의의 시간 동안 대기 후 다시 loop
	@Override
	public void run() {
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.println(name + "의 출력문자 : " + ch);
			try {
				// sleep() 메서드의 값을 200 ~ 500 사이의 난수로 한다. 
				Thread.sleep((int)Math.random()*301 + 200);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name + "의 출력 끝");
		T11_DisplayCharacterTest.strRank  += name + " ";	//먼저 끝난 사람이 String에 추가된다.
	}
}