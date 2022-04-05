package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

/*
 * 와일드 카드 예제
 * <? extends T> = 와일드 카드의 상한 제한. T와 그 자손들만 가능
 * <? super T> = 와일드 카드의 하한 제한. T와 그 조상들만 가능
 * <?> = 모든 타입 가능 <? extends Object> 와 동일
 * 
 * * 와일드 카드와 제한된 타입 파라미터 비교:
 * 1. 동일한 파라미터 타입으로 강제하고 싶은 경우. 매개변수 dest와 src의 유형이 반드시 동일해야 한다. 
 * (타입 파라미터가 한 개만 사용될 경우에는 둘 중 아무거나 사용해도 동일함.)
 * public static <T extends Number> void copy(List<T> dest, List<T> src)
 * public static <T extends Number, // S extends Number> void copy(List<T> dest, List<S> src) -> 두 개가 달라도 된다. 
 * => 메서드의 파라미터 타입을 동일한 타입으로 강제함. 다시 말해 매개변수로 오는 dest와 src의 유형이 달라도 상관 없다. 
 * public static void copy(List<? extends Number> dest, List<? extends Number> src)
 * => 동일 타입으로 강제하지 않음
 * 
 * 2. Bounded Type parameters는 하한 제한만 가능 (와일드카드는 상한, 하한 가능)
 * ex) public void print(List<? super Integer> list) // OK
 *     public <T super Integer> void print(List<T> list) // 에러. 제한된 파라미터 문법에는 super 자리에 오직 extends 만 올 수 있다. 
 */

public class T05_GenericMethodTest {
	public static void main(String[] args) {
		FruitBox<Fruit> fruitBox = new FruitBox<>();	// 과일 상자
		FruitBox<Apple> appleBox = new FruitBox<>();	// 사과 상자
		
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());
		
		appleBox.add(new Apple());
//		appleBox.add(new Grape());	//사과밖에 담을 수 없으므로 new Grape()를 입력하면 에러 발생..
		
		Juicer.makeJu(fruitBox);	//과일상자인 경우에는 아무 문제 없음
//		Juicer.makeJu(appleBox);	//바로 에러난다. Juicer에서 받는 매개변수의 유형을 제너릭을 이용한 Bounded Type Parameter로 쓰면 정상 출력된다. 
		
	}
}

class Juicer{
//	static void makeJu(FruitBox<Fruit> box) {
//	static <T extends Fruit> void makeJu(FruitBox<T> box) {	// 제한된 타입 파라미터 문법으로 해결할 수 있다. 나중에 반드시 T가 무엇인 지를 알려줘야 한다. 
	static void makeJu(FruitBox<? extends Fruit> box) {	// 와일드 카드를 이용한 방법.	
//	static <T> void makeJu(FruitBox<T> box) {	// 어떤 타입이든 다 가능하다. 	
		
		String fruitListStr = ""; 	// 과일목록 
		int cnt = 0;
		for(Fruit f : box.getFruitList()) {	// T가 어떤 타입인 지 알 수 없기 때문에 에러가 발생한다. 
			if(cnt == 0) {
				fruitListStr += f;
			}else {
				fruitListStr += "," + f;
			}
			
			cnt++;
		}
		System.out.println(fruitListStr + "=> 쥬스 완성!");
	}
}


class Fruit{
	private String name;	// 과일 이름
	
	public Fruit(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "과일(" + name + ")";
	}
}
/**
 * 사과 
 */
class Apple extends Fruit {
	public Apple() {
		super("사과");
	}
}

/**
 * 포도
 */
class Grape extends Fruit {
	public Grape() { 
		super("포도");
	}
}


/**
 * 과일 상자
 * @param <T>
 */
class FruitBox <T> {
	private List<T> fruitList;
	
	public FruitBox() {
		fruitList = new ArrayList<>();
	}

	public List<T> getFruitList() {
		return fruitList;
	}

	public void setFruitList(List<T> fruitList) {
		this.fruitList = fruitList;
	}
	
	public void add(T fruit) {
		fruitList.add(fruit);
	}
}