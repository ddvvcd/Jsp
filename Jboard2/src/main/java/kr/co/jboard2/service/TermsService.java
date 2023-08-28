package kr.co.jboard2.service;

import kr.co.jboard2.dao.TermsDAO;
import kr.co.jboard2.dto.TermsDTO;

public class TermsService {
	
	//TermsDAO 객체 생성
	private TermsDAO dao = new TermsDAO();
	
	public TermsDTO selectTerms() {
		return dao.selectTerms();
	}
	
}	
