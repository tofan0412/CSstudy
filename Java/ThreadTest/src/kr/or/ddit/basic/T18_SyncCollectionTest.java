package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Vector, hashtable 등의 예전부터 존재하던 Collection들은 내부에 동기화 처리가 되어 있다.
 * 그런데, 최근에 새로 구성된 Collection 클래스들은 동기화 처리가 되어 있지 않다. 
 * 그래서 동기화가 필요한 프로그램에서 이런 Collection들을 사용하려면 동기화 처리를 
 * 한 후에 사용해야 한다. 
 * 
 */
public class T18_SyncCollectionTest {
	// 동기화를 하지 않은 경우...
//	private static List<Integer> list1 = new ArrayList<>();
	
	// 이번에는 동기화 처리를 한 list에 add 작업을 진행해 보자..
	private static List<Integer> list2 = Collections.synchronizedList(new ArrayList<>());
	
	public static void main(String[] args) {
		
		// 익명 클래스로 쓰레드를 구현
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
//					list1.add(i);
					list2.add(i);
				}// 여러 쓰레드에서 동시에 하나의 List1에 add 작업을 하다보니, 접근해선 안되는 index값에 접근하게 된다.
			}
		};
		Thread[] ths = new Thread[] {
			new Thread(r), new Thread(r),
			new Thread(r), new Thread(r), new Thread(r)
		};
		
		for (Thread th : ths) {
			th.start();
		} 
		for (Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
//		System.out.println("list1의 개수 : " + list1.size());
		System.out.println("list2의 개수 : " + list2.size());
	}
}
