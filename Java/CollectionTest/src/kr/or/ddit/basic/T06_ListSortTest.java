package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T06_ListSortTest {
	/*
	 * 정렬과 관련된 interface는 Comparable과 Comparator 이렇게 두가지가 있다. - 보통 객체 자체에 정렬기능을 넣기
	 * 위해서는 Comparable을 구현하고 정렬 기준을 별도로 구현하고 싶을 때는 Comparator를 구현하여 사용하면 된다.
	 * (자신이 원하는 방식으로 정렬을 하고 싶을 때는 Comparator..)
	 * - Comparable에서는 comparaTo() 메서드를 구현해야 하고, Comparator에서는 compare() 메서드를 구현해야
	 * 한다.
	 * 
	 */
	
	public static void main(String[] args) {		
		List<String> list = new ArrayList<>();	//String 클래스에는 CompareTo가 구현되어 있다. -> comparable을 통해 정렬가능..
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		
		System.out.println("정렬전 : "+list);
		
		// 정렬은 Collections.sort()메서드를 이용하여 정렬한다.
		// 정렬은 기본적으로 '오름차순 정렬'을 수행한다. 
		
		// 정렬방식을 변경하려면 정렬방식을 결정하는 객체를 만들어서 
		// Collection.sort()메서드에 인수로 넘겨주면 된다.		// Collection은 인터페이스. 인터페이스에 구현화된 메서드를 정의할 수 없다.
		
		Collections.sort(list);		// 오름차순으로 정렬하기. Collections안에 유용한 메서드가 정의되어 있으며, 그 중 하나가 sort이다.
		System.out.println("정렬 후 :"+ list);
		// 
		Collections.shuffle(list);	// 데이터를 섞어 준다.
		System.out.println("자료 섞기 후 : " + list);
		
		// 정렬방식을 결정하는 객체를 이용하여 정렬하기
		Collections.sort(list, new Desc());	// 정렬하고자 하는 대상, 특정 정렬방식을 갖는 외부  메서드(정렬자). 내림차순으로
		System.out.println("정렬 후 :" + list);
	}
}

// 정렬방식을 결정하는 class는 Comparator라는 인터페이스를 구현해야 한다.
// 이 Comparator인터페이스의 compare()라는 메서드를 재정의하여 구현한다.

class Desc implements Comparator<String>{
	/*
	 compare() 메서드의 반환값을 결정하는 방법
	 => 이 메서드가 양수를 반환하면 두 값의 순서가 바뀐다. (오름차순이 기본임.)
	 
	 - 오름차순 정렬일 경우
	 => 앞의 값이 크면 양수, 같으면 0, 앞의 값이 작으면 음수를 반환하도록 한다.
	 
	 - String 객체에는 정렬을 위해서 comparaTo()메서드가 구현되어 있는데
	 이 메서드의 반환값은 오름차순에 맞게 반환되도록 구현되어 있다.
	 (Wrapper 클래스와 Date, File클래스에도 구현되어 있다.)
	 */
	
	@Override
	public int compare(String str1, String str2) {
	
		return str1.compareTo(str2) * 1;	//정렬 자체는 오름차순으로 정렬된다. 우리가 원하는건 오름차순..
		//곱하기 1 : 부호를 바꿔주면 정렬을 반대로 할 수 있다. (오름차순 -> 내림차순, 그 반대도 됨..)
		// compareTo 이미 정리가 완성된 정렬 기준을 사용해서 정렬을 할 수도 있다..
		
	}
}