package kr.co.farmstory2.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.TermsDTO;

public class TermsDAO extends DBHelper{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public TermsDTO selectTerms() {
		
		TermsDTO dto = new TermsDTO();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL.SELECT_TERMS);
			
			if(rs.next()) {
				dto.setTerms(rs.getString(1));
				dto.setPrivacy(rs.getString(2));
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			logger.error("TermsDAO selectTerms error : " + e.getMessage());
		}
		
		return dto;
		
	}

	
}
