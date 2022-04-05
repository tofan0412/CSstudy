package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 문제)학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다.
 * 생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
 * 이 Student 객체들은 List에 저장하여 관리한다.
 * List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과 
 * 총점의 역순으로 정렬하는 부분을 프로그램 하시오.
 * (총점이 같으면 학번의 내림차순으로 정렬되도록 하시오.)
 * (학번 정렬기준은 Student클래스 자체에서 제공하도록 하고, 
 * 총점 정렬기준은 외부 클래스에서 제공하도록 한다.
 */

public class T08_StudentTest {
	public static void main(String[] args) {
		List<Student> stdlist = new ArrayList<>();
		Student s1 = new Student("001", "조웅현", 10, 20);
		Student s2 = new Student("002", "홍길동", 10, 20);
		Student s3 = new Student("003", "변학도", 30, 40);
		Student s4 = new Student("004", "유영현", 60, 75);
		Student s5 = new Student("005", "우송이", 80, 80);
		stdlist.add(s1);
		stdlist.add(s2);
		stdlist.add(s3);
		stdlist.add(s4);
		stdlist.add(s5);
		
		T08_StudentTest.setRanking(stdlist);
		
		System.out.println("정렬 전...");
		for(Student std : stdlist) {
			System.out.println(std);
		}
		System.out.println("=======================");
		
//		Collections.sort(stdlist);	//정렬
		System.out.println("정렬 후...");
		for(Student std : stdlist) {
			System.out.println(std);
		}
		System.out.println("========================");
		
		// 정렬해서 랭크 입력하기
		System.out.println("총점 기준을 정렬 ");
		for(Student s : stdlist) {
			System.out.println(s);
		}
		System.out.println("=========================");
	}
	
	public static void setRanking(List<Student> list){
		for(Student std1 : list) {	//등수를 구할 자료
			int rank = 1;
			for(Student std2 : list) {
				if (std1.getTotalSc() < std2.getTotalSc()) {
					rank++;
				}
			}
		std1.setOrder(rank);
		}
	}
}



class Student{
	private String id;
	private String name;
	private int langSc;
	private int MathSc;
	private int totalSc;
	private int rank;
	// 등수 자동으로 만들어주기
	
	public Student(String id, String name, int langSc, int mathSc) {
		super();
		this.id = id;
		this.name = name;
		this.langSc = langSc;
		MathSc = mathSc;
		this.totalSc = mathSc + langSc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLangSc() {
		return langSc;
	}

	public void setLangSc(int langSc) {
		this.langSc = langSc;
	}

	public int getMathSc() {
		return MathSc;
	}

	public void setMathSc(int mathSc) {
		MathSc = mathSc;
	}

	public int getTotalSc() {
		return totalSc;
	}

	public void setTotalSc(int totalSc) {
		this.totalSc = totalSc;
	}

	public int getOrder() {
		return rank;
	}

	public void setOrder(int order) {
		this.rank = rank;
	}
	
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", langSc=" + langSc + ", MathSc=" + MathSc + ", totalSc="
				+ totalSc + ", rank=" + rank + "]";
	}
	/**
	 * 학번을 기준으로 오름차순으로 정렬한다.
	 * 자신의 학번과 매개변수의 학번을 비교하여 정렬한다. 
	 */
	public int compareTo(Student s) {
		return getId().compareTo(s.getId())*-1;	// -1을 해주면 내림차순..
		// 이 경우 오름차순으로 정렬된다. 
	}
}


/**
 * 총점의 역순으로 정렬하는데 총점이 같으면 학번의 내림차순으로 정렬한다. 
 * @author PC-23
 *
 */
// Q. 동점인 경우 아이디를 기준으로 정렬하는건 어떻게 하는가?
class SortStudent implements Comparator<Student>{
	@Override
	public int compare(Student s1, Student s2) {
		if (s1.getTotalSc() == s2.getTotalSc()) {
			return s1.getId().compareTo(s2.getId())*-1;	// 요런식으로 하면 된다. 요 부분 못했음
		}else {
			return Integer.compare(s1.getTotalSc(), s2.getTotalSc())*-1;
		}
	}
}
