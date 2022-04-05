package kr.or.ddit.basic;

/*
 * 제너릭 메서드 <T, R> R method(T t)	// R은 반환값이며, 이 또한 제네릭하게 선언한 것이다. 
 * 
 * 파라미터 타입과 리턴 타입으로 타입 파라미터를 가지는 메서드
 * - 선언 방법 : 리턴 타입 앞에 <> 기호를 추가하고 타입 파라미터를 기술 후 사용함.
 *
 */
class Util{
	public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
		
		boolean keyCompare = p1.getKey().equals(p2.getKey());
		boolean valueCompare = p1.getValue().equals(p2.getValue());
		
		return keyCompare && valueCompare;
	}
}


/**
 * 멀티타입<K, V>을 가지는 제너릭 클래스
 * 
 *
 * @param <K>
 * @param <V>
 */
class Pair<K, V> {	// 제너릭 클래스이다. 따라서 클래스명 뒤에 타입이 따라온다. 
	private K key;
	private V value;
	
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	
	public Pair(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	// 키와 값을 출력하는 메서드. K와 V는 메서드 내에서 사용하기 위해 새롭게 정의한 것이다.
	public <K, V> void displayAll(K key, V value) {
		System.out.println(key.toString() + " : " + value.toString());
	}
}


public class T03_GerericMethodTest {
	public static void main(String[] args) {
		
		Pair<Integer, String> p1 = new Pair<Integer, String>(1, "홍길동");
		Pair<Integer, String> p2 = new Pair<Integer, String>(1, "홍길동");
		
		// 구체적 타입을 명시적으로 지정(생략 가능)
		boolean result1 = Util.<Integer, String>compare(p1, p2);
		
		if(result1) {
			System.out.println("논리(의미)적으로 동일한 객체임");
		}else {
			System.out.println("논리(의미)적으로 동일한 객체 아님");
		}
		
		Pair<String, String> p3 = new Pair<String, String>("001", "홍길동");
		Pair<String, String> p4 = new Pair<String, String>("002", "홍길동");
		
		boolean result2 = Util.compare(p3, p4);
		
		if(result2) {
			System.out.println("논리(의미)적으로 동일한 객체임.");
		}else {
			System.out.println("논리(의미)적으로 동일한 객체 아님.");
		}
		
		// 제너릭 메서드 테스트
		p1.<String, Integer>displayAll("키", 1234);
		
		
		
	}
}