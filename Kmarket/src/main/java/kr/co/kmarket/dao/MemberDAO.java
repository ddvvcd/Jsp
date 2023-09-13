package kr.co.kmarket.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.MemberDTO;

public class MemberDAO extends DBHelper{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//기본 CRUD 메소드 정의
	public void insertMember(MemberDTO dto) {
		try {
			logger.info("MemberDAO insertMember...1");
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_MEMBER);
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getName());
			psmt.setInt(4, dto.getGender());
			psmt.setString(5, dto.getHp());
			psmt.setString(6, dto.getEmail());
			psmt.setString(7, dto.getZip());
			psmt.setString(8, dto.getAddr1());
			psmt.setString(9, dto.getAddr2());
			psmt.setString(10, dto.getRegip());
			psmt.executeUpdate(); //쿼리문 실행
			close();
			
			logger.info("MemberDAO insertMember...2");
		} catch (Exception e) {
			logger.error("MemberDAO insertMember error : " + e.getMessage());
		}
	}
	
	public MemberDTO selectMember(String uid, String pass) {
		
		MemberDTO dto = null;
		
		try {
			logger.info("MemberDAO selectMember...1");
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_MEMBER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setUid(rs.getString(1));
				dto.setPass(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setGender(rs.getString(4));
				dto.setEmail(rs.getString(5));
				dto.setHp(rs.getString(6));
				dto.setZip(rs.getString(7));
				dto.setAddr1(rs.getString(8));
				dto.setAddr2(rs.getString(9));
				dto.setRegip(rs.getString(10));
				dto.setRdate(rs.getString(11));
				dto.setWdate(rs.getString(12));
			}
			
			close();
			
			logger.info("MemberDAO selectMember...2");
			
		} catch (Exception e) {
			logger.error("MemberDAO selectMember error : " + e.getMessage());
		}
		
		return null;
	}
	
	public void selectMembers() {}
	public void updateMember() {}
	public void deleteMember() {}
}