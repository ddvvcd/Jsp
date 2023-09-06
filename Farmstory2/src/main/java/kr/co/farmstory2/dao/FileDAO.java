package kr.co.farmstory2.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.FileDTO;

public class FileDAO extends DBHelper{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertFile(FileDTO dto) {
		try {
			
			logger.info("FileDAO insertFile...1");
			
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_FILE);
			
			psmt.setInt(1, dto.getFno());
			psmt.setInt(2, dto.getAno());
			psmt.setString(3, dto.getOriName());
			psmt.setString(4, dto.getNewName());
			psmt.setInt(5, dto.getDownload());
			
			psmt.executeUpdate();
			
			close();
			
			logger.info("FileDAO insertFile...2");
			
		} catch (Exception e) {
			logger.error("FileDAO insertFile error : " + e.getMessage());
		}
	}
	
	public FileDTO selectFile(int fno) {
		return null;
	}
	
	public List<FileDTO> selectFiles() {
		return null;
	}
	
	public void updateFile(FileDTO dto) {
		
	}
	
	public void deleteFile(int fno) {
		
	}
}
