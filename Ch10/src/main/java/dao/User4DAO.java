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


import dto.User4DTO;

public class User4DAO {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//기본 CRUD메소드 정의
	public void insertUser4(User4DTO dto) {
		try {
			logger.info("User4 insertUser4...1");
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
			Connection conn = ds.getConnection();
			
			PreparedStatement psmt = conn.prepareStatement("INSERT INTO `User4` VALUES(?,?,?,?,?)");
			psmt.setInt(1, dto.getSeq());
			psmt.setString(2, dto.getName());
			psmt.setInt(3, dto.getGender());
			psmt.setInt(4, dto.getAge());
			psmt.setString(5, dto.getAddr());
			
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
			logger.info("User4 insertUser4...2");

		} catch (Exception e) {
			logger.error("User4 insertUser4 error : " + e.getMessage());
		}
	}
	
	public User4DTO selectUser4(int seq) {
		
		User4DTO dto = new User4DTO();
		
		try {
			logger.info("User4 selectUser4...1");
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
			Connection conn = ds.getConnection();
			
			PreparedStatement psmt = conn.prepareStatement("SELECT * FROM `User4` WHERE `seq`=?");
			psmt.setInt(1, seq);
			
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				dto.setSeq(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setGender(rs.getString(3));
				dto.setAge(rs.getString(4));
				dto.setAddr(rs.getString(5));
			}
			
			rs.close();
			psmt.close();
			conn.close();
			
		} catch (Exception e) {
			logger.error("User4DAO selectUser4 error : " + e.getMessage());
		}
		
		return dto;
	}
	
	public List<User4DTO> selectUser4s() {
		
		List<User4DTO> user4 = new ArrayList<>();
		
		try {
			logger.info("User4 selectUser4s...1");
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
			Connection conn = ds.getConnection();
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `User4`");
			
			while(rs.next()) {
				User4DTO dto = new User4DTO();
				dto.setSeq(rs.getNString(1));
				dto.setName(rs.getString(2));
				dto.setGender(rs.getString(3));
				dto.setAge(rs.getString(4));
				dto.setAddr(rs.getString(5));
				user4.add(dto);
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
			logger.info("User4 selectUser4s...2");

		} catch (Exception e) {
			logger.error("User4DAO selectUser4s error : " + e.getMessage());
		}
		
		return user4;
	}
	
	public void updateUser4(User4DTO dto) {
		try {
			logger.info("User4 updateUser4...1");
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
			Connection conn = ds.getConnection();
			
			PreparedStatement psmt = conn.prepareStatement("UPDATE `User4` SET `name`=?, `gender`=?, `age`=?, `addr`=? WHERE `seq`=?");
			psmt.setString(1, dto.getName());
			psmt.setInt(2, dto.getGender());
			psmt.setInt(3, dto.getAge());
			psmt.setString(4, dto.getAddr());
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
			logger.info("User4 updateUser4...2");

		} catch (Exception e) {
			logger.error("User4DAO updateUser4 error : " + e.getMessage());
		}
	}
	
	public void deleteUser4(int seq) {
		try {
			
			logger.info("User4 deleteUser4...1");
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) ctx.lookup("jdbc/userdb");
			Connection conn = ds.getConnection();
			
			PreparedStatement psmt = conn.prepareStatement("DELETE `User4` SET `seq`=?");
			psmt.setInt(1, seq);
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
			logger.info("User4 deleteUser4...2");
			
		} catch (Exception e) {
			logger.error("User4 deleteUser4 error : " + e.getMessage());
		}
	}
}
