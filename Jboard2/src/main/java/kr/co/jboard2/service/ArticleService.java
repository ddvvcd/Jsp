package kr.co.jboard2.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.jboard2.dao.ArticleDAO;
import kr.co.jboard2.dto.ArticleDTO;

public enum ArticleService {
	
	INSTANCE;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleDAO dao = new ArticleDAO();
	

	public int insertArticle(ArticleDTO dto) {
		return dao.insertArticle(dto);
	}
	
	public ArticleDTO selectArticle(String no) {
		return dao.selectArticle(no);
	}
	
	public List<ArticleDTO> selectArticles(int start) {
		return dao.selectArticles(start);
	}
	
	//추가
	public List<ArticleDTO> selectComments(String parent) {
		return dao.selectComments(parent);
	}
		
	public void updateArticle(ArticleDTO dto) {
		dao.updateArticle(dto);
	}
	
	public void deleteArticle(int no) {
		dao.deleteArticle(no);
	}
	
	//업로드 경로 구하기
	public String getFilePath(HttpServletRequest req) {
		//파일 업로드 경로 구하기
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/upload");
		
		return path;
	}
	
	//파일명 수정
	public String renameToFile(HttpServletRequest req, String oName) {
		
		String path = getFilePath(req);
		
		int i = oName.lastIndexOf(".");
		String ext = oName.substring(i); //fname.substring(i) : 파일명 확장자(텍스트로 연습해보며 이해하기)
		
		String uuid  = UUID.randomUUID().toString();
		String sName = uuid + ext; 
		
		File f1 = new File(path+"/"+oName);
		File f2 = new File(path+"/"+sName);
		
		//파일명 수정
		f1.renameTo(f2);
		
		return sName;
	}
	
	//파일 업로드
	public MultipartRequest uploadFile(HttpServletRequest req) {
		//파일 경로 구하기
		String path = getFilePath(req);
		
		//최대 업로드 파일 크기
		int maxSize = 1024 * 1024 * 10;
		
		//파일 업로드 (MultipartRequest라는 생성자에서 파일 업로드 처리/스트림 처리)
		
		MultipartRequest mr = null;
		
		try {
			mr = new MultipartRequest(req, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {
			logger.error("ArticleService uploadFile error : " + e.getMessage());
		}
		
		return mr;
	}
	
	//파일 다운로드
	public void downloadFile() {
		
	}
}
