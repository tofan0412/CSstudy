package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class LottoTest {
	public static void main(String[] args) {
		getLotto obj = new getLotto();
		List<int[]> list = new ArrayList<>();
		int user = 0;
		while(true) {
			try {
				Scanner sc = new Scanner(System.in);
				System.out.println("구매할 회수를 입력해주세요. ( 최소 1회~최대 5회)");
				user = sc.nextInt();
				  
				if (user !=1 && user !=2 && user !=3 && user !=4 && user !=5 ) {
					System.out.println("다시 입력해주세요.");
					continue;
				}else {
					break;
			}
			}catch (InputMismatchException e) {
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
				continue;
			}
		}
		
		for (int i = 0; i < user; i++ ) {
			int[] result = obj.provLotto();
			list.add(result);
			System.out.println((i+1)+"번 째 자동 추첨 결과 : " + Arrays.toString(list.get(i)));
		}
	}
}

// 자동으로 로또 번호 뽑는 프로그램 만들기..
// 1. 6자리 + 1자리(보너스 번호) 뽑아서 저장하기. 이 때 중복되면 안되므로, Set을 이용한다. 
// 2. 배열에 하나씩 순서대로 뽑아서 넣어주기.
// 3. 섞어주기.
// 4. 사용자로부터 최대 5회, 몇 게임을 할 건지 물어보기.


class getLotto{

	/**
	 * 로또 1~45의 숫자중 7자리 뽑기(Set에)
	 */
	private Set<Integer> RndLotto() {
		// 로또 번호를 저장할(인덱스가 없다.) set 집합 만들기.
		Set<Integer> set = new HashSet<>();
		while(set.size() < 7) {	//7자리 모두 채울 때까지 진행
			int num = (int)(Math.random()*45+1);	// 1~45중 하나인 숫자.
			set.add(num);	//AutoBoxing되어 자동으로 객체로 들어간다. 
		}
		return set;
	}
	
	private int[] sortLotto() {
		// 서로 다른 7개의 숫자를 배열에 저장한다. 
		int[] num = new int[7];
		Set<Integer> set = this.RndLotto();	// 7자리가 저장되어 있는 Set 집합을 불러온다. 
		Iterator<Integer> it = set.iterator();	// 인덱스가 존재하는 배열에 집합의 각 요소를 넣을 준비 => Iterator
		int i = 0;
		while(it.hasNext()) {
			num[i++] = it.next().intValue();
		}
		return num;
	}
	
	private int[] shuffle() {
		int[] num = this.sortLotto();
		for(int j = 0 ; j < 100 ; j++) {
			for (int i = 0; i < num.length ; i++) {	// 배열의 인덱스 0부터 시작해서 랜덤으로 하나씩 섞기..
				int tmp = num[i];
				num[i] = num[(int)Math.random()*num.length];
				num[(int)Math.random()*num.length] = tmp;
			}
		}
		return num;
	}
	
	public int[] provLotto() {
		this.RndLotto();
		this.sortLotto();
		int[] result = this.shuffle();
		return result;
	}
}