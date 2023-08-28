package service;

import java.util.List;

import dao.MemberDAO;
import dto.MemberDTO;

//열겨타입 enum으로 싱글톤 객체 생성(클래스에 enum을 지정)
public enum MembereService {
	
	//INSTANCE를 적어주면 싱글톤이 됨
	INSTANCE;
	
	//DAO 객체 호출
	private MemberDAO dao = MemberDAO.getInstance();
	
	//DAO의 메소드를 호출
	public void insertMember(MemberDTO dto) {
		dao.insertMember(dto);
	}
	
	public MemberDTO selectMember(String uid) {
		return dao.selectMember(uid);
	}
	
	public List<MemberDTO> selectrMembers() {
		return dao.selectrMembers();
	}
	
	public void updateMember(MemberDTO dto) {
		dao.updateMember(dto);
	}
	
	public void deleteMember(String uid) {
		dao.deleteMember(uid);
	}
	
}
