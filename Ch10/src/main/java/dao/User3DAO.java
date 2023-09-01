package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.User3DTO;

public class User3DAO {
	
	//로거 생성 (Logger : 인터페이스 타입)
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//기본 CRUD 메소드 정의
	public void insertUser(User3DTO dto) {
		
		try {
			logger.info("User3DAO insertUser3...1");
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
			Connection conn = ds.getConnection();
			
			PreparedStatement psmt = conn.prepareStatement("INSERT INTO `User3` VALUES (?,?,?,?)");
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getHp());
			psmt.setInt(4, dto.getAge());
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			logger.info("User3DAO insertUser3...2");
			
		} catch (Exception e) {
			logger.error("User3DAO insertUser3DAO : " + e.getMessage());
		}
	}
	
	public User3DTO selectUser3 (String uid) {
		
		User3DTO dto = new User3DTO();
		
		try {
			logger.info("User3DAO selectUser3...1");
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
			Connection conn = ds.getConnection();
			
			PreparedStatement psmt = conn.prepareStatement("SELECT * FROM `User3` WHERE `uid`=?");
			psmt.setString(1, uid);
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setUid(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setHp(rs.getString(3));
				dto.setAge(rs.getString(4));
			}
			
			rs.close();
			psmt.close();
			conn.close();
			logger.info("User3DAO selectUser3...2");

		} catch (Exception e) {
			logger.error("User3DAO selectUser3DAO error : " + e.getMessage());
		}
		
		return dto;
	}
	
	public List<User3DTO> selectUser3s() {
		
		List<User3DTO> user3 = new ArrayList<>();
		
		try {
			logger.info("User3DAO selectUsers3...1");
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
			Connection conn = ds.getConnection();
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `User3`");
			
			while(rs.next()) {
				User3DTO dto = new User3DTO();
				dto.setUid(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setHp(rs.getString(3));
				dto.setAge(rs.getString(4));
				user3.add(dto);
			}
			
			rs.close();
			stmt.close();
			conn.close();
			logger.info("User3DAO selectUsers3...2");

		} catch (Exception e) {
			logger.error("User3DAO selectUser3s error : " + e.getMessage());
		}
		
		return user3;
	}
	
	public void updateUser3(User3DTO dto) {
		
		try {
			logger.info("User3DAO updateUser3...1");
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
			Connection conn = ds.getConnection();
			
			PreparedStatement psmt = conn.prepareStatement("UPDATE `User3` SET `name`=?, `hp`=?, `age`=? WEHRE `uid`=?");
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getHp());
			psmt.setInt(3, dto.getAge());
			psmt.setString(4, dto.getUid());
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
		} catch (Exception e) {
			logger.error("User3DAO updateUser3 error : " + e.getMessage());
		}
	}
	
	public void deleteUser3(String uid) {
		try {
			logger.info("User3DAO deleteUser3...1");
			Context initCtx = new InitialContext();
			Context ctx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)ctx.lookup("jdbc/userdb");
			Connection conn = ds.getConnection();
			
			PreparedStatement psmt = conn.prepareStatement("DELETE `User3` SET `uid`=?");
			psmt.setString(1, uid);
			
			psmt.close();
			conn.close();
			
		} catch (Exception e) {
			logger.error("User3DAO deleteUser3 error : " + e.getMessage());
		}
	}
	
}
