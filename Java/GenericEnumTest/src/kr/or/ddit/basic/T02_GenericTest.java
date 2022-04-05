package kr.or.ddit.basic;

/* 
 * 제너릭 클래스를 만드는 방법
 * 형식)
 *   class 클래스명<제너릭타입글자>{	// 클래스 안에서는 타입글자가 하나의 타입역할을 한다.
 *   	제너릭타입글자 변수명;	// 변수선언에 제너릭을 사용할 경우. 변수의 유형이 T타입이 된다. 
 *   	  ...
 *      제너릭 타입 글자 메서드명(){	//반환값이 있는 메서드에서 사용. 리턴값의 유형은 T타입이 된다. 
 *      	...
 *      	return 값;
 *   	}
 *   	...
 *   } 
 *   
 *   -- 제너릭 타입 글자 --	// 일반적으로 사용하는 약속을 나열한 것이다.   
 *   T : Type
 *   K : Key
 *   V : Value
 *   E : Element(자료구조에 들어가는 것들을 나타낼 때 사용)
 *   
 */
class NonGenericClass{
	private Object Val;
	
	public Object getVal() {
		return Val;
	}

	public void setVal(Object Val) {
		this.Val = Val;
	}
}


class MyGeneric<T>{
	private T Val;
	
	public T getVal() {
		return Val;
	}
	public void setVal(T val) {
		this.Val = Val;
	}
}

public class T02_GenericTest {
	public static void main(String[] args) {
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setVal("가나다라");
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setVal(100);
		
		String rtnNg1 = (String)ng1.getVal();
		System.out.println("문자열 반환값 rtnNg1 : " + rtnNg1);
		
		Integer irtnNg2 = (Integer) ng2.getVal();
		System.out.println("정수 반환값 irtnNg2 : " + irtnNg2);
		System.out.println();
		
		MyGeneric<String> mg1 = new MyGeneric<String>();
		MyGeneric<Integer> mg2 = new MyGeneric<Integer>();
		
		mg1.setVal("우리나라");
		mg2.setVal(500);
		
		rtnNg1 = mg1.getVal();
		irtnNg2 = mg2.getVal();
		
		System.out.println("제너릭 문자열 반환값 : " + rtnNg1);
		System.out.println("제너릭 정수형 반환값 : " + irtnNg2);
	}
}
