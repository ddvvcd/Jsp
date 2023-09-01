package kr.co.jboard2.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.db.SQL;
import kr.co.jboard2.dto.FileDTO;

public class FileDAO extends DBHelper {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertFile(FileDTO dto) {
		try {
			logger.info("FileDAO insertFile...1");
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_FILE);
			psmt.setInt(1, dto.getAno());
			psmt.setString(2, dto.getOfile());
			psmt.setString(3, dto.getSfile());
			psmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			logger.error("FileDAO insertFile error : " + e.getMessage());
		}
	}
	
	//파일 다운로드
	public FileDTO selectFile(String fno) {
		
		FileDTO dto = null;
		
		try {
			logger.info("FileDAo selectFile...1");

			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_FILE);
			psmt.setString(1, fno); //? (파라미터)가 하나였음
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new FileDTO();
				dto.setFno(rs.getInt(1));
				dto.setAno(rs.getInt(2));
				dto.setOfile(rs.getString(3));
				dto.setSfile(rs.getString(4));
				dto.setDownload(rs.getInt(5));
				dto.setRdate(rs.getString(6));
			}
		
			close();
			logger.info("FileDAo selectFile...2");
		} catch (Exception e) {
			logger.error("FileDAO selectFile error : " + e.getMessage());
		}
		
		return dto;
	}
	
	public List<FileDTO> selectFiles() {
		return null;
	}
	
	public void updateFile(FileDTO dto) {
		
	}
	
	public int deleteFile(String ano) {
		
		int result = 0;
		
		try {
			logger.info("FileDAO deleteFile...1");

			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_FILE);
			psmt.setString(1, ano);
			result = psmt.executeUpdate();
			close();
			
			logger.info("FileDAO deleteFile...2");
		} catch (Exception e) {
			logger.error("FileDAO deleteFile error : " + e.getMessage());
		}
		
		return result;
	}
}
