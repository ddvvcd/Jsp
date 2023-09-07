package kr.co.farmstory2.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;

import kr.co.farmstory2.dao.ArticleDAO;
import kr.co.farmstory2.dto.ArticleDTO;

public class ArticleService {
	
	ArticleDAO dao = new ArticleDAO();
	
	//게시물 작성
	public int insertArticle(ArticleDTO dto) {
		return dao.insertArticle(dto);
	}
	
	//게시물 상세 조회
	public ArticleDTO selectArticle(String no) {
		return dao.selectArticle(no);
	}
	
	//게시물 전체 조회 출력
	public List<ArticleDTO> selectArticles(int start) {
		return dao.selectArticles(start);
	}
	
	///////////////////////////////////////////////////////////////////////
	//전체 게시물 갯수
	public int selectCountTotal(String cate) {
		return dao.selectCountTotal(cate);
	}
	
	//댓글 조회
	public List<ArticleDTO> selectComments(String parent) {
		return dao.selectComments(parent);
	}
	
	//댓글 작성
	public int insertComment(ArticleDTO dto) {
		return dao.insertComment(dto);
	}
	
	//댓글 삭제
	public int deleteComment(String no) {
		return dao.deleteComment(no);
	}
	
	//현재 페이지 번호
	public int getCurrentPage(String pg) {
		int currentPage = 1;
		
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		return currentPage;
	}
	
	//마지막 페이지 번호
	public int getLastPageNum(int total) {
		int lastPageNum = 0;
		
		if(total % 10 == 0) {
			lastPageNum = total / 10;
		}else {
			lastPageNum = total /10 + 1;
		}
		
		return lastPageNum;
	}
	
	//페이지 그룹
	public int[] getPageGroupNum(int currentPage, int lastPageNum) {
		int currentPageGroup = (int) Math.ceil(currentPage / 10.0);
		int pageGroupStart = (currentPageGroup - 1) * 10 + 1;
		int pageGroupEnd = currentPageGroup * 10;
		
		if(pageGroupEnd > lastPageNum) {
			pageGroupEnd = lastPageNum;
		}
		
		int[] result = {pageGroupStart, pageGroupEnd};
		
		return result;
	}
	
	// 페이지 시작번호
		public int getPageStartNum(int total, int currentPage) {
			int start = (currentPage - 1) * 10;
			return total - start;
		}
	
	//Limit 시작번호
	public int getStartNum(int currentPage) {
		return (currentPage - 1) * 10;
	}
	
	//업로드 경로 구하기
	public String getFilePath(HttpServletRequest req) {
		return dao.getFilePath(req);
	}
	
	//파일 업로드
	public String renameToFile(HttpServletRequest req ,String oriName) {
		return dao.renameToFile(req, oriName);
	}
	
	//파일 다운로드
	public MultipartRequest uploadFile(HttpServletRequest req, String path) {
		return dao.uploadFile(req, path);
	}
}

