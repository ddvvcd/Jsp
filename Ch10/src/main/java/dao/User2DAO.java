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

import dto.User2DTO;

public class User2DAO {
	
	//로거 생성 (Logger : 인터페이스 타입)
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	//기본 CRUD 메소드
	public void insertUser2(User2DTO dto) {
		
		try {
			logger.info("User2DAO insertUser2...1");
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			
			DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
			Connection conn = ds.getConnection();
			
			PreparedStatement psmt = conn.prepareStatement("INSERT INTO `user2` VALUES (?,?,?,?)");
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getHp());
			psmt.setInt(4, dto.getAge());
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			logger.info("User2DAO insertUser2...2");

		} catch (Exception e) {
			logger.error("User2DAO insertUser2 error : " + e.getMessage());
		}
	}
	
	public User2DTO selectUser2(String uid) {
		
		User2DTO dto = new User2DTO();
		
		try {
			logger.info("User2DAO selectUser2...1");
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
			Connection conn = ds.getConnection();

			PreparedStatement psmt = conn.prepareStatement("SELECT * FROM `user2` WHERE `uid`=?");
			psmt.setString(1, uid);
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setUid(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setHp(rs.getString(3));
				dto.setAge(rs.getInt(4));
			}
			
			rs.close();
			psmt.close();
			logger.info("User2DAO selectUser2...2");

		} catch (Exception e) {
			logger.error("User2DAO selectUser2 error : " + e.getMessage());
		}
		
		return dto;
	}
	
	public List<User2DTO> selestUser2s() {
		
		List<User2DTO> users = new ArrayList<>();
		
		try {
			logger.info("User2DAO selectUser2s...1");
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
			Connection conn = ds.getConnection();
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `user2`");
			
			while(rs.next()) {
				User2DTO dto = new User2DTO();
				dto.setUid(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setHp(rs.getString(3));
				dto.setAge(rs.getInt(4));
				users.add(dto);
			}
			
			rs.close();
			stmt.close();
			conn.close();
			logger.info("User2DAO selectUser2s...2");

		} catch (Exception e) {
			logger.error("User2DAO selectUser2s error : " + e.getMessage());
		}
		
		return users;
	}
	
	public void updateUser2(User2DTO dto) {
		try {
			logger.info("User2DAO updateUser2...1");
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
			Connection conn = ds.getConnection();
			
			PreparedStatement psmt = conn.prepareStatement("UPDATE `user2` SET `name`=?, `hp`=?, `age`=? WHERE `uid`=?");
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getHp());
			psmt.setInt(3, dto.getAge());
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			logger.info("User2DAO updateUser2...2");

		}catch(Exception e){
			logger.error("User2DAO updateUser2 error : " + e.getMessage());
		}
	}
	
	public void deleteUser2(String uid) {
		try {
			logger.info("User2DAO deleteUser2...1");
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
			Connection conn = ds.getConnection();
			
			PreparedStatement psmt = conn.prepareStatement("DELETE FROM `user2` WHERE `uid`=?");
			psmt.setString(1, uid);
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
			logger.info("User2DAO deleteUser2...2");

		}catch(Exception e){
			logger.error("User2DAO deleteUser2 error : " + e.getMessage());
		}
	}
}
