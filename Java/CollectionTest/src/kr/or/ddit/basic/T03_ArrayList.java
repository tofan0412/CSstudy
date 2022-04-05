package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class T03_ArrayList {
	public static void main(String[] args) {
		//문제5) 5명의 사람 이름을 입력하여 ArrayList에 저장하고
		// 이중에 '김'씨 성의 이름을 출력하시오.
		//(단, scanner를 이용하여 입력받는다.)
		
		Scanner sc = new Scanner(System.in);
		System.out.println("출력1");
		String name1 = sc.next();
		System.out.println("출력2");
		String name2 = sc.next();
		System.out.println("출력3");
		String name3 = sc.next();
		System.out.println("출력4");
		String name4 = sc.next();
		System.out.println("출력5");
		String name5 = sc.next();
		
		List<String> list = new ArrayList<String>();
		list.add(name1);
		list.add(name2);
		list.add(name3);
		list.add(name4);
		list.add(name5);
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).contains("김")) {	// 문제점 : 앞에 김이 안오고 나중에 와도 포함으로 인식하여 출력된다. 
				System.out.println("김씨 성을 가진 이름 : " + list.get(i));
			}
		}
	}
}



/* if (name.indexOf("김") = 0{
 *    syso(name); 
 */

/* if(name.startWith("김")){
 *     syso(name);
 */

// charAt, substring을 사용해도 된다.
