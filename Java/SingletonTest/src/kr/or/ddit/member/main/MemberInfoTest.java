package kr.or.ddit.member.main;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil;
import kr.or.ddit.util.JDBCUtil3;

/**
 * MVC패턴에서 Controller와 View 역할을 담당하는 클래스
 * @author PC-23
 */
public class MemberInfoTest {
	//Service 객체 변수의 선언
	private IMemberService service;
	
	private Scanner scan; 
	
	public MemberInfoTest() {
		service = new MemberServiceImpl().getInstance();
		scan = new Scanner(System.in);
	}
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 자료 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
					break;
				case 2 :  // 자료 삭제
					deleteMember();
					break;
				case 3 :  // 자료 수정
					updateMember();
					break;
				case 4 :  // 전체 자료 출력
					getAllMember();
					break;
				case 5 :  // 검색
					searchMember();
					break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=5);
	}
	
	
	/**
	 * 회원 정보를 검색하는 메서드.
	 */
	private void searchMember() {
		/*
		 검색할 회원ID, 회원이름, 전화번호, 주소 등을 입력하면, 
		 입력한 정보만 사용하여 검색하는 기능 구현하기
		 주소는 입력한 값이 포함만 되어도 검색되도록 처리한다. 
		 입력을 하지 않을 자료는 엔터키로 다음 입력으로 넘긴다. 
		 */
		
		Scanner sc = new Scanner(System.in);
		sc.nextLine();	// 버퍼 비우기
		
		System.out.println("");
		System.out.println("검색할 정보를 입력하세요.");
		System.out.print("회원 ID >> ");
		String memId = sc.nextLine().trim();	// 사용자가 입력한 거에서 공백 제거(trim)
		
		System.out.print("회원 이름 >>");
		String memName = sc.nextLine().trim();
		
		System.out.print("회원 전화번호 >>");
		String memTel = sc.nextLine().trim();
		
		System.out.print("회원 주소 >>");
		String memAddr = sc.nextLine().trim();
		
		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		mv.setMem_name(memName);
		mv.setMem_tel(memTel);
		mv.setMem_addr(memAddr);
		
		List<MemberVO> memList = service.getSearchMember(mv);
		
		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println("ID\t이 름\t전화번호\t\t주 소");
		System.out.println("-----------------------------------------------");
		
		if (memList.size() == 0 || memList == null) {
			System.out.println("검색한 자료가 없습니다.");
			displayMenu();
		}else {
			for(MemberVO mv2 : memList) {
				System.out.println(mv2.getMem_id()  + "\t" + mv2.getMem_name() + "\t" 
									+ mv2.getMem_tel() + "\t\t" + mv2.getMem_addr());
			}
			System.out.println("-----------------------------------------------");
			System.out.println("출력작업 끝");
			displayMenu();
		}
	}
	
	
	/**
	 * 회원 정보를 삭제하는 메서드(입력받은 회원ID를 이용하여 삭제한다.)
	 */
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원ID를 입력하세요 >>");
		String memId = scan.next();
		
		int cnt = service.deleteMember(memId);
		if (cnt != 0) System.out.println("삭제되었습니다.");
		else System.out.println("삭제 실패하였습니다.");
	}

	private void updateMember() {
		boolean chk = false;
		String memId ="";
		
		do {
			System.out.println("수정할 회원ID를 입력하세요 >>");
			memId = scan.next();
			
			chk = getMember(memId);
			
			if(chk == false) {
				System.out.println(memId + " 회원은 없는 회원입니다.");
				System.out.println("수정할 자료가 없으니 다시 입력해 주세요.");
			}
			
		}while(chk == false);
		System.out.println("수정할 내용을 입력하세요:");
		System.out.println("새로운 회원 이름 >>");
		String memName = scan.next();
		System.out.print("새로운 회원 전화번호 >>");
		String memTel = scan.next();
		
		scan.nextLine();	//입력 버퍼 지우기
		System.out.println("새로운 회원 주소 >>");
		String memAddr = scan.nextLine();
		
		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		mv.setMem_name(memName);
		mv.setMem_tel(memTel);
		mv.setMem_addr(memAddr);
		
		int cnt = service.updateMember(mv);
		
		if (cnt > 0) {
			System.out.println("회원 정보를 수정하였습니다.");
		}else {
			System.out.println("회원 정보 수정 실패..");
		}
	} 

	/**
	 * 전체 회원을 출력하는 메서드
	 */
	private void getAllMember() {
		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println("ID\t이 름\t전화번호\t\t주 소");
		System.out.println("-----------------------------------------------");
		
		List<MemberVO> memList = service.getAllMember();
		
//		for (int i = 0; i < memList.size(); i++) {
//			System.out.print(memList.get(i).getMem_id() + "\t");
//			System.out.print(memList.get(i).getMem_name() + "\t");
//			System.out.print(memList.get(i).getMem_tel() + "\t\t");
//			System.out.println(memList.get(i).getMem_addr());
//		}
		
		//for-each문을 이용하여 다음과 같이 작성할 수 있다. 
		for(MemberVO mv : memList) {
			System.out.println(mv.getMem_id()  + "\t" + mv.getMem_name() + "\t" 
								+ mv.getMem_tel() + "\t\t" + mv.getMem_addr());
		}
		
		System.out.println("-----------------------------------------------");
		System.out.println("출력작업 끝");
		
	}
	
	/**
	 * 회원정보를 입력하는 메서드.
	 */
	private void insertMember() {
		boolean chk = false;
		String memId;
		
		do {
			System.out.println();
			System.out.println("추가할 회원 정보를 입력하세요 : ");
			System.out.print("회원 ID >>");
			
			memId = scan.next();
			
			chk = getMember(memId);
			
			if(chk) {
				System.out.println("회원ID가 " + memId + "인 회원이 이미 존재합니다.");
				System.out.println("다시 입력해 주세요..");
			}
		}while(chk == true);
		
		System.out.print("회원 이름 >>");
		String memName = scan.next();
		
		System.out.print("회원 전화번호>>");
		String memTel = scan.next();
		scan.nextLine(); // 입력 버퍼 지우기..
		
		System.out.println("회원 주소 >>");
		String memAddr = scan.nextLine();
		
		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		mv.setMem_name(memName);
		mv.setMem_tel(memTel);
		mv.setMem_addr(memAddr);
		
		int cnt = service.insertMember(mv);
			
		if (cnt > 0) {
			System.out.println(memId + " 회원 추가 작업 성공 ! ");
		}else {
			System.out.println(memId + " 회원 추가 작업 실패..");
		}
	}

	/**
	 * 회원 ID를 이용하여 회원이 존재하는 지를 알려주는 메서드
	 * @param memId
	 * @return true : 이미 ID가 존재한다. false : 해당 ID가 존재하지 않는다. 
	 */
	private boolean getMember(String memId) {
		boolean chk = service.getMember(memId);
		return chk;
	}
	
	
	public static void main(String[] args) {
		MemberInfoTest memObj = new MemberInfoTest();
		memObj.start();
	}
}
