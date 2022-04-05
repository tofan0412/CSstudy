package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * 제너릭 클래스를 이용한 객체 생성 예제 
 */
public class T06_WildCardTest {
	public static void main(String[] args) {
		// FruitBox2<? extends Fruit> fruitBox1 = new FruitBox2<Fruit>();
		FruitBox2<?> fruitBox1 = new FruitBox2();
		FruitBox2<?> fruitBox2 = new FruitBox2<>();	// 위와 동일
		
		// FruitBox2<?>는 FruitBox2<? extends Fruit>를 의미함
//		FruitBox2<?> fruitBox3 = new FruitBox2<Object>(); // <?>를 통해 Fruit와 그 하위 클래스 타입만 올 수 있다고 명시하였기 때문에 Object는 담을 수가 없다. 따라서 오류 발생
		
		// 두 타입(Object, Fruit)이 일치하지 않음
//		FruitBox2<Object> fruitBox4 = new FruitBox2<Fruit>(); // 만들 때 Fruit로 만들어도, 서로의 타입이 일치하지 않기 때문에 에러가 발생한다. 
		
		FruitBox2<?> fruitBox5 = new FruitBox2<Fruit>();
		
		FruitBox2<? extends Fruit> fruitBox6 = new FruitBox2<Apple>();	//Fruit에서 Apple을 포함하고 있으므로 가능하다.
		
		// new 연산자는 타입이 명확해야 객체생성을 할 수 있다.(와일드 카드 사용 불가)
//		FruitBox2<? extends Object> fruitBox7 = new FruitBox2<? extends Object>();
	}
}


/**
 * 과일상자2
 * 제한된 타입 파라미터를 이용한 제너릭 클래스 예제
 * @author PC-23
 *
 * @param <T>
 */
class FruitBox2 <T extends Fruit> {
	List<T> itemList = new ArrayList<T>();

	public List<T> getItemList() {
		return itemList;
	}

	public void setItemList(List<T> itemList) {
		this.itemList = itemList;
	}
	
	public void addItem(T item) {
		this.itemList.add(item);
	}
}