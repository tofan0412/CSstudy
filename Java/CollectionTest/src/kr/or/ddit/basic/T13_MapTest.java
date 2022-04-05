package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class T13_MapTest {
	public static void main(String[] args) {
		/*
		 * Map : key값과 value값을 한 쌍으로 관리하는 객체.
		 *     : ket값은 중복을 허용하지 않고 순서가 없다.(set의 특징)
		 *     : value값은 중복을 허용한다. (List의 특징)
		 * 
		 */
		
		Map<String, String > map = new HashMap<>();
		
		// 자료추가 : put(Key값, value);
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1234-4567");
		
		System.out.println("Map :" + map);
		
		// 자료의 등록과 수정하는 메서드 모두 put이다.
		// 자료의 수정 : 데이터를 저장할 때 key 값이 같으면 나중에 입력한 값이 
		//            저장된다. put(수정할 key값, 새로운 value값);
		map.put("addr", "서울");
		System.out.println("map :"+ map);
		
		// 자료 삭제 -> remove(삭제할 key값);
		map.remove("name");
		System.out.println("map :" + map);
		
		// 자료 읽기 -> get(key값);
		System.out.println("addr :"+ map.get("addr"));
		System.out.println("===============");
		
		//key값들을 읽어와 자료를 출력하는 방법.
		
		// 방법1. keySet()메서드 이용하기
		// 		 keySet() 메서드  : Map의 key값들만 읽어와 Set형으로 반환한다.
		Set<String> keySet = map.keySet();
		
		System.out.println("Iterator를 이용한 방법");
		Iterator<String> it = keySet.iterator();
		while(it.hasNext())	{
			String key = it.next();
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("==================");
		
		
		//방법2. Set형의 데이터를 'for-each문'으로 처리하면 Iterator를 
		//      사용하지 않아도 된다.
		System.out.println("for-each문을 이용한 방법");
		for(String key : keySet) {
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("====================");

		
		//방법3. value값만 읽어와서 출력하기 : values() 메서드 이용하기
		System.out.println("values() 메서드를 이용하는 방법");
		for (String value : map.values()) {
			System.out.println(value);
		}
		System.out.println("====================");
		
		
		//방법4. Map에는 Entry라는 내부 클래스가 만들어져 있다.
		//      이 Entry클래스는 key와 value라는 멤버변수로 구성되어 있다.
		//      Map에서 이 Entry 클래스들을 Set형식으로 저장하여 관리한다.
		
		//Entry 객체 전체를 가져오기(가져온 Entry들은 Set형식으로 되어 있다.)
		// : entrySet() 메서드를 이용하여 가져온다.
		Set<Map.Entry<String, String>> mapSet = map.entrySet();	//Map에 존재하는 모든 entry를 반환한다. 이렇게 하면 key와 value를 모두 가져갈 수 있다.  
		
		// 가져온 Entry객체들을 순서대로 처리하기 위하여 Iterator객체로 변환한다.
		Iterator<Map.Entry<String, String>> entryIt = mapSet.iterator();
		
		while(entryIt.hasNext()) {
			Map.Entry<String, String> entry = entryIt.next();
			System.out.println("key값 : "+ entry.getKey());
			System.out.println("value 값: "+ entry.getValue());
		}
		
		//위에서 진행한 while문을 for-each문을 사용하여 진행할 수도 있다.
		
	}
}
