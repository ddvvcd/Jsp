package kr.co.kmarket.service;

import kr.co.kmarket.dao.TermsDAO;
import kr.co.kmarket.dto.TermsDTO;

public class TermsService {
	
	TermsDAO dao = new TermsDAO();
	
	public TermsDTO selectTerms() {
		return dao.selectTerms();
	}
}
