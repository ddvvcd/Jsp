package kr.co.jboard2.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.db.SQL;
import kr.co.jboard2.dto.UserDTO;

public class UserDAO extends DBHelper{
	
	//싱글톤으로 생성
	private static UserDAO instance = new UserDAO();
	public static UserDAO getInstance() {
		return instance;
	}
	private UserDAO() {}
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertUser(UserDTO dto) {
		try {
			logger.info("UserDAO insertUser...1");
			
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_USER);
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
			psmt.executeUpdate(); //쿼리문 실행
			close();
			logger.info("UserDAO insertUser...2");

		} catch (Exception e) {
			logger.error("UserDAO insertUser error : " + e.getMessage());
		}
	}
	
	public int selectCountUid(String uid) {
			
		int result = 0;
		
		try {
			logger.info("UserDAO selectCountUid...1");
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_UID);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
			logger.info("UserDAO selectCountUid...2");
			
		}catch (Exception e) {
			logger.error("selectCountUid() error : " + e.getMessage());
		}
		
		return result;
	}
	
	public int selectCountNick(String nick) {
		
		int result = 0;
		
		try {
			logger.info("UserDAO selectCountNick...1");
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_NICK);
			psmt.setString(1, nick);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
			
			logger.info("UserDAO selectCountNick...2");
			
		} catch (Exception e) {
			logger.error("UserDAO selectCountNick error : " + e.getMessage());
		}
		
		return result;
	}
	
	public int selectCountHp(String hp) {
		
		int result = 0;
		
		try {
			logger.info("UserDAO selectCountHp...1");
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_HP);
			psmt.setString(1, hp);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
			
			logger.info("UserDAO selectCountHp...2");
			
		} catch (Exception e) {
			logger.error("UserDAO selectCountHp error : " + e.getMessage());
		}
		
		return result;
	}
	
	public UserDTO selectUser(String uid) {
		return null;
	}
	
	public List<UserDTO> selectUsers() {
		return null;
	}
	
	public void updateUser(UserDTO dto) {
		
	}
	public void deleteUser(String uid) {
		
	}
}
