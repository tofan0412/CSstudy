package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/*
 * 문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
 * 	컴퓨터의 숫자는 난수를 이용하여 구한다.
 * (스트라이크는 'S', 볼은 'B'로 출력한다.)
 * 
 * 컴퓨터의 난수가 9 5 7일 때 실행예시)
 * 	숫자입력 -> 3 5 9
 * 	3 5 9 -> 1S 1B
 * 	숫자입력 -> 7 8 9
 * 	7 8 9 -> 0S 2B
 * 
 * ...
 * 
 *	숫자입력 -> 9 5 7
 *	9 5 7 -> 3S 0B
 *
 * 	5번째 만에 맞췄군요.
 */
public class T11_BaseBallTest {
	public static void main(String[] args) {
		
		// 1~9인 숫자 3개를 정답 Set에 넣어두기.
		HashSet<Integer> AnswerSet = new HashSet<>(3);
		for (int i = 0; i < 3; i++) {
			int num = (int)(Math.random()*9 + 1);
			AnswerSet.add(num);	// Ball을 확인하기 위해 사용한다...? 혹시 몰라서 일단 만들어둠 ㅎ 
		}
		List<Integer> AnswerList = new ArrayList<Integer>(AnswerSet);
		
//		System.out.println("정답은 " + AnswerList + "입니다.");
		int count = 0;
		loop : while(true) {
			List<Integer> user = new ArrayList<>();
			// 사용자로부터 입력 3개 받기.
			Scanner sc = new Scanner(System.in);
			System.out.println("첫번째 숫자를 입력하십시오(1~9):");
			int in1 = sc.nextInt();
			user.add(in1);
			System.out.println("두번째 숫자를 입력하십시오(1~9):");
			int in2 = sc.nextInt();
			user.add(in2);
			System.out.println("세번째 숫자를 입력하십시오(1~9):");
			int in3 = sc.nextInt();
			user.add(in3);
			
			System.out.println("사용자가 낸 답은 " + user + "입니다. ");
			int strike = 0;
			int ball = 0;
			for (int i = 0; i < user.size(); i++) {	// 정답과 동일한 자리에 같은 숫자이면 스트라이크를 +1한다. 
				if (user.get(i).equals(AnswerList.get(i))) {
					strike++;
				}else if (AnswerList.contains(user.get(i))){	// 동일한 자리는 아니지만, 해당 숫자가 정답에 포함되는 경우.
					ball++;
				}
			}
			
			System.out.println(strike+"스트라이크 "+ball+"볼입니다.");
			count++;
			
			if (strike == 3) {
				System.out.println("정답입니다.");
				System.out.println(count+"번 만에 맞추셨군요.");
				break loop;
			}
		}
	}
}

// 객체 지향으로 다시 설계하기
// 다음과 같이 나누자. 
// 1. 정답 만들기
// 2. 사용자한테 입력받기
// 3. 정답이랑 비교하고, 출력하기

/*
	int[] num = new int[3];
	int[] user;
	
	public void gameStart(){
		getRndNum();	// 정답 생성하기
		
		// 확인용 출력
		System.out.println("난수값 ->"
							+ num[0]+ " "+ num[1]+" " + num[2]);
		
		int cnt = 0; 
		
		do { 
			cnt++;
			intputNum();	// 사용자가 입력한 값을 받아서 저장하기..
			ballCount();	// 사용자의 입력값과 정답을 비교해서 strike, ball 여부 판단하기
		}while(strike !=3);
		System.out.println(cnt + "번 만에 맞추셨군요.");
	}
	
	
	private void ballCount(){
		int strike = 0;	// do-while문에서 반복적으로 돌기 때문에 초기화를 먼저 해준다.
		int ball = 0;	
		
		for(int i = 0 ; i < num.length ; i++){
			for (int j = 0 ; j < user.length ; j++){
				if (num[i] = = user[j]){	// 값이 같은지 비교
					if (i == j){	// 값도 같고 인덱스도 같은 경우
						strike++;
					}else{
					 	ball++;
					 }
				}		
			}
		}		
						
		System.out.println(user[0] + " " 
		                 + user[1] + " " 
		                 + user[2] + " ===> "
		                 + strike + "S " + ball + "B");
		
	}
	
	
	// 1~9 사이의 서로 다른 난수를 3개 만들어 배열에 저장하는 메서드(Set 이용)
	private void getRndNum(){
		Set<Integer> bbNumSet = new HashSet<>();
		
		// Set을 이용한 3개의 난수 만들기
		while(bbNumSet.size() < 3){
			bbNumSet.add((int)((Math.random)*9 + 1));	// 이 경우 중복되는 수가 들어가지 않는다.
		}
		
		Iterator<Integer> it = bbNumSet.iterator();	// for-each 문을 사용해도 된다. 
		
		int i = 0;
		while(it.hasNext()){
			num[i++] = it.next().intValue();	// intValue는 int값으로 추출하여 저장하는 것이다.
			// 미리 만들어둔 num이라는 배열에다가 bbNumSet의 값을 하나씩 넣는 것이다.
			 
		}
		
		// 문제점 : 수 자체는 랜덤하지만, 3개의 숫자가 항상 일정한 패턴으로 정렬이 된다.
		// 데이터 섞기 (0번째 자료와 난수번째 자료를 교환하는 방법으로 데이터를 섞는다.)
		
		for(int j = 1; j <= 100 ; j++){	//100번 동안 섞는다.
			int rnd = (int)(Math.random()*num.length);	// 0 ~ 2
			int temp = num[0];
			num[0] = num[rnd];
			num[rnd] = temp;
		}
	}
	
	// 사용자로부터 3개의 정수를 입력받아 배열에 저장하는 메서드.
	// (입력한 정수들은 서로 중복되지 않게 처리한다.)
	private void intPutNum(){
		int n1 = 0, n2 = 0, n3 = 0;	// 입력한 값을 저장할 변수
		
		do{
			System.out.println("중복 되지 않는 정수 3개 입력=>");
			n1 = scan.nextInt();
			n2 = scan.nextInt();
			n3 = scan.nextInt();
			
			if (n1 == n2 || n1 == n3 || n2 == n3){
				System.out.println("중복되는 숫자는 입력할 수 없습니다. 다시 입력해 주세요.");
			}
			
		{while(n1 == n2 || n1 == n3 || n2 == n3);
		// 사용자로부터 3개의 정수를 받는데, 3개의 수가 중복되는 값이 있으면 계속 돈다.
		user = new int[] {n1, n2, n3};
		
	public static void main(String[] args) {
		new T11_BaseBallTest().game.start();
	 
		
*/


