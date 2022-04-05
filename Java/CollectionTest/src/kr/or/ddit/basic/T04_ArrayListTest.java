package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class T04_ArrayListTest {
	public static void main(String[] args) {
		//문제1) 5명의 별명을 입력하여 ArrayList에 저장하고
		//      별명의 길이가 제일 긴 별명을 출력하시오.
		//      (단, 각 별명의 길이는 모두 다르게 입력한다.)
		
		//문제2) 문제1에서 별명의 길이가 같은 것을 여러 개 입력했을 때에도
		//      처리하시오. (5명중 가장 긴 길이를 가진 별명이 3개일 때, 3개를 출력해라.)
		
		List<String> list = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 5; i++) {
			System.out.println("별명을 입력하세요.");
			String input = sc.next();
			list.add(input);
		}
		
		String temp = list.get(0);
		List<String> max = new ArrayList<>();
		
		for (int i = 0; i < list.size()-1; i++) {
			if (temp.length() <= list.get(i+1).length()) {
				temp = list.get(i+1);
			}else {
				temp = temp;
			}
		}
		for (int i = 0 ; i < list.size() ; i++) {
			if (temp.length() == list.get(i).length()) {
				max.add(list.get(i));
			}
		}
		System.out.println("가장 길이가 긴 별명은 "+ temp+"입니다.");
		System.out.println("가장 길이가 긴 별명은"+ max+"입니다.");
		
		
	}
}
