package kr.or.ddit.basic;
/*
10마리의 말들이 경주하는 경마 프로그램 작성하기

말은 Horse라는 이름의 클래스로 구성하고,
이 클래스는 말이름(String), 등수(int)를 멤버변수로 갖는다.
그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
기능이 있다.( Comparable 인터페이스 구현)

경기 구간은 1~50구간으로 되어 있다.

경기 중 중간중간에 각 말들의 위치를 나타내시오.
예)
1번말 --->------------------------------------
2번말 ----->----------------------------------
...

경기가 끝나면 등수 순으로 출력한다.
*/

import java.util.ArrayList;
import java.util.List;

public class HourseRacingTest {
	static int count = 1;

	public static void main(String[] args) {
		Horse th1 = new Horse("1번마");
		Horse th2 = new Horse("2번마");
		Horse th3 = new Horse("3번마");
		Horse th4 = new Horse("4번마");
		Horse th5 = new Horse("5번마");
		Horse th6 = new Horse("6번마");
		Horse th7 = new Horse("7번마");
		Horse th8 = new Horse("8번마");
		Horse th9 = new Horse("9번마");
		Horse th10 = new Horse("10번마");

		Horse[] game = { th1, th2, th3,th4, th5, th6, th7, th8, th9, th10  };

		for (int i = 0; i < game.length; i++) {
			game[i].start();
		}

		for (Thread h : game) {
			try {
				h.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


class resultBoard extends Thread{
	
}


/**
 * 말 클래스
 *
 */
class Horse extends Thread {
	private String name;
	List<String> road = new ArrayList<String>();

	public Horse(String name) {
		this.name = name;
	}

	/**
	 * 길 만드는 메서드
	 */
	public void makeRoad() {
		for (int i = 0; i < 50; i++) {
			road.add(i, "-");
		}
	}
	
	// 현재 상황을 출력하는 메서드.
	public String viewRoad(List<String> road) {
		String str = "";
		for (int i = 0; i < 50; i++) {
			str += road.get(i);
		}
		return str;
	}
	
	/**
	 * run() 메서드
	 */
	@Override
	public void run() {
		makeRoad(); // 길 만들어놓기.
		for (int i = 0; i < road.size(); i++) {
			road.set(i, ">");
			System.out.println(name + " : " + viewRoad(road));
			try { 
				Thread.sleep((int) Math.random()*100);
				road.set(i, "-");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name + " 도착 ");
		System.out.println(name + "의 순위는 " + HourseRacingTest.count + "위입니다.");
		HourseRacingTest.count++;
	}
}