package kr.or.ddit.util;
// Properties 에서 정보를 가져와서 사용하자.

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * db.properties파일의 내용으로 DB정보를 설정하는 방법
 * 방법1) Properties객체 이용하기
 */
public class JDBCUtil2 {
	static Properties prop;	//Properties 객체 변수 선언.
	
	static {
		prop = new Properties();	// 객체 생성
		
		File file = new File("res/db.properties");
		
		try {
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis);
			
			Class.forName(prop.getProperty("driver"));
		} catch (IOException e) {
			System.out.println("파일이 없거나 입출력 오류입니다.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(prop.getProperty("url"),
					                           prop.getProperty("userName"), 
					                           prop.getProperty("pass"));
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
			return null;
		}
	}
}
