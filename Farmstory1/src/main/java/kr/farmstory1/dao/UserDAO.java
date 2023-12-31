package kr.farmstory1.dao;

import kr.farmstory1.db.DBHelper;
import kr.farmstory1.db.SQL;
import kr.farmstory1.dto.TermsDTO;
import kr.farmstory1.dto.UserDTO;

public class UserDAO extends DBHelper {
	
	private static UserDAO instance = new UserDAO();
	public static UserDAO getInstance() {
		return instance;
	} 
	private UserDAO() {}
	
	public void insertUser(UserDTO dto) {
		try {
			System.out.println("1");
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_USER);
			System.out.println("2");
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getNick());
			psmt.setString(5, dto.getEmail());
			psmt.setString(6, dto.getHp());
			psmt.setString(7, dto.getZip());
			psmt.setString(8, dto.getAddr1());
			psmt.setString(9, dto.getAddr2());
			psmt.setString(10, dto.getRegip());
			System.out.println("3");
			psmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			System.out.println("insertUser : " + e.getMessage());
		}
	}
	
	public TermsDTO selectTerms() {
		TermsDTO dto = new TermsDTO();
		
		try {
			conn = getConnection();
			System.out.println("2");
			stmt = conn.createStatement();
			System.out.println("3");
			rs = stmt.executeQuery(SQL.SELECT_TERMS);
			System.out.println("4");
			if(rs.next()) {
				dto.setTerms(rs.getString(1));
				dto.setPrivacy(rs.getString(2));
			}
			System.out.println("5");
			close();
			System.out.println("6");
		} catch (Exception e) {
			System.out.println("TermsDTO : " + e.getMessage());
		}
		
		return dto;
	}
	
	public UserDTO selectUser(String uid, String pass) {
		
		UserDTO user = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_USER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				user = new UserDTO();
				user.setUid(rs.getString(1));
				user.setPass(rs.getString(2));
				user.setName(rs.getString(3));
				user.setNick(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setHp(rs.getString(6));
				user.setRole(rs.getString(7));
				user.setZip(rs.getString(8));
				user.setAddr1(rs.getString(9));
				user.setAddr2(rs.getString(10));
				user.setRegip(rs.getString(11));
				user.setRegDate(rs.getString(12));
				user.setLeaveDate(rs.getString(13));
			}
			close();
			
		} catch (Exception e) {
			System.out.println("selectUser : " + e.getMessage());
		}
		
		return user;
	}

	
	// 추가
	public int selectCountUid(String uid) {
		int result = 0;
		try{
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_UID);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt(1);
			}
			close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public int selectCountNick(String nick) {
		int result = 0;
		try{
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_NICK);
			psmt.setString(1, nick);
			rs = psmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt(1);
			}
			close();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public int selectCountHp(String hp) {
		int result = 0;
		try{
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_HP);
			psmt.setString(1, hp);
			rs = psmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt(1);
			}
			
			close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public int selectCountEmail(String email) {
		int result = 0;
		try{
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_EMAIL);
			psmt.setString(1, email);
			rs = psmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt(1);
			}
			close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
