package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JDBCUtil3 {
	static ResourceBundle bundle;	// ResourceBundle 객체 변수 선언
	 
	static {
		bundle = ResourceBundle.getBundle("db");	//객체 생성
		
		try {
			Class.forName(bundle.getString("driver"));
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패!");
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(bundle.getString("url"), 
											bundle.getString("userName"), 
											bundle.getString("pass"));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 연결 실패");
			return null;
		}
	}
}
