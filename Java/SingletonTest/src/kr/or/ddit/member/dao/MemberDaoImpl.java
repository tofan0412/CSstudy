package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil;
import kr.or.ddit.util.JDBCUtil2;
import kr.or.ddit.util.JDBCUtil3;

public class MemberDaoImpl implements IMemberDao{
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static IMemberDao dao;
	
	private MemberDaoImpl()	{
		
	}
	
	public static IMemberDao getInstance() {
		if(dao == null) {
			dao = new MemberDaoImpl();
		}
		
		return dao;
	}
	
	
	/**
	 * 자원반납용 메서드
	 */
	private void disconnect() {
		// 6. 종료(사용했던 자원을 반납한다.)
		if(rs !=null) try { rs.close();} catch(SQLException e) {}
		if(stmt !=null) try { stmt.close();} catch(SQLException e) {}
		if(conn !=null) try { conn.close();} catch(SQLException e) {}
	}
	
	
//	public static void main(String[] args) {
//		MemberInfoTest memObj = new MemberInfoTest();
//		memObj.start();
//	}
	
	
	@Override
	public int insertMember(MemberVO mv) {
		int cnt = 0;
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "INSERT INTO MYMEMBER (MEM_ID, MEM_NAME, MEM_TEL, MEM_ADDR) "
					     +"VALUES(?,?,?,?) ";
			// ?가 나왔으면 PREPAREDSTATEMENT를 사용한다는 걸 뜻한다. 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMem_id());
			pstmt.setString(2, mv.getMem_name());
			pstmt.setString(3, mv.getMem_tel());
			pstmt.setString(4, mv.getMem_addr());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return cnt;
	}
	

	@Override
	public boolean getMember(String memId) {
		boolean chk = false;
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "SELECT COUNT(*) AS CNT FROM MYMEMBER "
					    +"WHERE MEM_ID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);	// ? 에 memId가 들어간다. 
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("CNT");
			}
			
			if (cnt > 0) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			chk = false;	// 예외가 발생하면 chk를 false로 바꾼다. but 디폴트가 false이므로 크게 의미는 없다..
		}finally {
			disconnect();
		}
		return chk;
	}

	
	@Override
	public List<MemberVO> getAllMember() {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM MYMEMBER";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			// 반복문 안에서 가져온 레코드 하나 하나를 MembeVO에 맵핑하고 이 VO를 List에 추가한다.
			while(rs.next()) {
				String memId = rs.getString("MEM_ID");
				String memName = rs.getString("MEM_NAME");
				String memTel = rs.getString("MEM_TEL");
				String memAddr = rs.getString("MEM_ADDR");
				
				MemberVO mv = new MemberVO();
				mv.setMem_id(memId);
				mv.setMem_name(memName);
				mv.setMem_tel(memTel);
				mv.setMem_addr(memAddr);
				
				memList.add(mv);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return memList;
	}
	
	
	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "UPDATE MYMEMBER SET "
						  +"MEM_NAME = ?, "
					      +"MEM_TEL = ?, "
					      +"MEM_ADDR = ? "
					      +"WHERE MEM_ID = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMem_name());
			pstmt.setString(2, mv.getMem_tel());
			pstmt.setString(3, mv.getMem_addr());
			pstmt.setString(4, mv.getMem_id());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return cnt;
	}

	
	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memId);
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		List<MemberVO> memList = new ArrayList<>();
		
		try {
			conn = JDBCUtil2.getConnection();
			
			String sql = "SELECT * FROM MYMEMBER WHERE 1=1 ";
			if (mv.getMem_id() != null && !mv.getMem_id().equals("")) {
				sql += " and mem_id = ? ";	// 이를 통해 where 뒤에 추가 조건을 추가할 수 있다.
			}
			if (mv.getMem_name() != null && !mv.getMem_name().equals("")) {
				sql += " and mem_name = ? ";	
			}
			if (mv.getMem_tel() != null && !mv.getMem_tel().equals("")) {
				sql += " and mem_tel = ? ";	
			}
			if (mv.getMem_addr() != null && !mv.getMem_addr().equals("")) {
				sql += " and mem_addr like '%' || ? || '%' ";	
			}
			// ? 에 넣을 값을 prepareStatement를 통해 넣는다. 
			pstmt = conn.prepareStatement(sql);
			// 검색하고자 할 때, 4가지를 모두 검색할 수도 있고, 1가지만 검색할 수도 있으므로 이 또한 동적으로 작성
			int index = 1;
			
			
			//만약 엔터를 치면 ""이므로, 해당 사항이 검색 옵션에서 고려되지 않는다. 
			if (mv.getMem_id() != null && !mv.getMem_id().equals("")) {
				pstmt.setString(index++, mv.getMem_id());
			}
			if (mv.getMem_name() != null && !mv.getMem_name().equals("")) {
				pstmt.setString(index++, mv.getMem_name());
			}
			if (mv.getMem_tel() != null && !mv.getMem_tel().equals("")) {
				pstmt.setString(index++, mv.getMem_tel());
			}
			if (mv.getMem_addr() != null && !mv.getMem_addr().equals("")) {
				pstmt.setString(index++, mv.getMem_addr());
			}
			
			rs = pstmt.executeQuery();
			
			// 반복문 안에서 가져온 레코드 하나 하나를 MembeVO에 맵핑하고 이 VO를 List에 추가한다.
			while(rs.next()) {
				String memId = rs.getString("MEM_ID");
				String memName = rs.getString("MEM_NAME");
				String memTel = rs.getString("MEM_TEL");
				String memAddr = rs.getString("MEM_ADDR");
				
				MemberVO mv2 = new MemberVO();
				mv2.setMem_id(memId);
				mv2.setMem_name(memName);
				mv2.setMem_tel(memTel);
				mv2.setMem_addr(memAddr);
				
				memList.add(mv2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return memList;
	}
}
