package kr.or.ddit.basic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
 * Java Reflection에 대하여
 * 
 * 1. 리플렉션은 클래스 또는 멤버변수, 메서드, 생성자에 대한 정보를 가져오거나 수정할 수 있다.
 * 2. Reflection API는 java.lang.reflect 패키지와 java.lang.CLass를
 * 통해 제공된다. 
 * 3. java.lang.Class의 주요 메서드 
 * - getName(), getSuperclass(), getInterface(), getModifiers() (클래스의 접근 제어자 확인) 등
 * 4. java.lang.reflect 패키지의 주요 클래스
 * - Field, Method, Constructor, Modifier, 등.
 */

public class AnnotationTest {
	public static void main(String[] args)
			throws IllegalAccessException, 
			       IllegalArgumentException, 
			       InvocationTargetException {
		System.out.println(PrintAnnotation.id); // 상수값 출력

		// reflection 기능을 이용한 메서드 실행하기
		// 선언된 메서드 목록 가져오기
		Method[] declaredMethods = Service.class.getDeclaredMethods();

		for (Method m : declaredMethods) {
			System.out.println(m.getName());
			PrintAnnotation pa = m.getDeclaredAnnotation(PrintAnnotation.class);
			for (int i = 0; i < pa.count(); i++) { // count값 만큼..
				System.out.println(pa.value()); // value값 출력
			}
			System.out.println(); // 줄바꿈 처리..

			// 방법1) new 이용한 객체 생성
			// m.invoke(new Service());

			// 방법2) reflection을 이용한 객체의 생성
			Class<Service> clazz = Service.class;

			try {
				Service service = (Service) clazz.newInstance();
				m.invoke(service);
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		}
	}
}
