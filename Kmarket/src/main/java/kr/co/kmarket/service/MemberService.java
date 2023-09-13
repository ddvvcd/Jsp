package kr.co.kmarket.service;

import kr.co.kmarket.dao.MemberDAO;
import kr.co.kmarket.dto.MemberDTO;

public class MemberService {
	
	MemberDAO dao = new MemberDAO();
	
	public void insertMember(MemberDTO dto) {
		dao.insertMember(dto);
	}
}
