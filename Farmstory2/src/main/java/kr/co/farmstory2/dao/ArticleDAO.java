package kr.co.farmstory2.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.ArticleDTO;

public class ArticleDAO extends DBHelper {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//게시글 작성
	public int insertArticle(ArticleDTO dto) {
		
		int no = 0;
		
		try {
			logger.info("ArticleDAO insertArticle...1");
			
			conn = getConnection();
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getCate());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setInt(4, dto.getFile());
			psmt.setString(5, dto.getWriter());
			psmt.setString(6, dto.getRegip());
			psmt.executeUpdate();
			rs = stmt.executeQuery(SQL.SELECT_MAX_NO);
			
			if(rs.next()) {
				no = rs.getInt(1);
			}
			
			close();
			
			logger.info("ArticleDAO insertArticle...2");
			
		} catch (Exception e) {
			logger.error("ArticleDAO insertArticle error : " + e.getMessage());
		}
		
		return no;
	}
	
	//특정 게시물 번호를 받아와서 해당 게시물의 상세 정보를 조회하는 메소드
	public ArticleDTO selectArticle(String no) {

		ArticleDTO dto = new ArticleDTO();

		try {
			
			logger.info("ArticleDAO selectArticle...1");
			
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLE);
			psmt.setString(1, no);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setParent(rs.getInt("parent"));
				dto.setComment(rs.getInt("comment"));
				dto.setCate(rs.getString("cate"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setFile(rs.getInt("file"));
				dto.setHit(rs.getInt("hit"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegip(rs.getString("regip"));
				dto.setRdate(rs.getString("rdate"));
			}
			
			close();
			
			logger.info("ArticleDAO selectArticle...2");

			
		} catch (Exception e) {
			logger.error("ArticleDAO selectArticle error : " + e.getMessage());
		}
		
		return dto;
	}
	
	//게시물을 조회하는 메소드로, start라는 파라미터를 받아와서 시작 위치부터 일정 개수의 게시물을 검색
	public List<ArticleDTO> selectArticles(int start) {
		
		List<ArticleDTO> articles = new ArrayList<>();
		
		try {
			
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLES);
			psmt.setInt(1, start);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleDTO dto = new ArticleDTO();
				dto.setNo(rs.getInt(1));
				dto.setParent(rs.getInt(2));
				dto.setComment(rs.getInt(3));
				dto.setCate(rs.getString(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setFile(rs.getInt(7));
				dto.setHit(rs.getInt(8));
				dto.setWriter(rs.getString(9));
				dto.setRegip(rs.getString(10));
				dto.setRdate(rs.getString(11));
				articles.add(dto);
			}
			
			close();
			
			
		} catch (Exception e) {
			logger.error("ArticleDAO selectArticles error : " + e.getMessage());
		}
		
		return articles;
	}
	
	public void updateArticle(ArticleDTO dto) {
		
	}
	
	public void deleteArticle(int no) {
		
	}
	
	//////////////////////////////////////////////////////////
	//전체 게시물 갯수
	public int selectCountTotal(String cate) {
		
		int total = 0;
		
		try {
			logger.info("ArticleDAO selectCountTotal...1");
			
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL);
			psmt.setString(1, cate);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
			close();
			
			logger.info("ArticleDAO selectCountTotal...2");

		} catch (Exception e) {
			logger.error("ArticleDAO selectCountTotal error : " + e.getMessage());
		}
		
		return total;
	}
	
	//댓글을 조회하는 메소드로, parent라는 파라미터를 받아와서 해당 부모 게시물에 대한 댓글을 검색
	public List<ArticleDTO> selectComments(String parent){
		
		List<ArticleDTO> comments = new ArrayList<>();
		
		try {
			
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COMMENTS);
			psmt.setString(1, parent);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				ArticleDTO dto = new ArticleDTO();
				dto.setNo(rs.getInt(1));
				dto.setParent(rs.getInt(2));
				dto.setComment(rs.getInt(3));
				dto.setCate(rs.getString(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setFile(rs.getString(7));
				dto.setHit(rs.getInt(8));
				dto.setWriter(rs.getString(9));
				dto.setRegip(rs.getString(10));
				dto.setRdate(rs.getString(11));
				comments.add(dto);
			}
			
		} catch (Exception e) {
			logger.error("ArticleDAO selectComments error : " + e.getMessage());
		}
		
		return comments;
	}
	
	
	//댓글 작성
	public int insertComment(ArticleDTO dto) {
		
		int result = 0;
		
		try {
			
			logger.info("ArticleDAO insertComment...1");
			
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_COMMENT);
			psmt.setInt(1, dto.getParent());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getWriter());
			psmt.setString(4, dto.getRegip());
			
			result = psmt.executeUpdate();
			
			logger.info("ArticleDAO insertComment...2");
			
		} catch (Exception e) {
			logger.error("ArticleDAO insertComment error : " + e.getMessage());
		}
		
		return result;
	}
	
	//파일 업로드 경로 구하기
	public String getFilePath(HttpServletRequest req) {
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/upload");
		
		return path;
	}
	
	//파일명 수정 및 파일 테이블 Insert
	public String renameToFile(HttpServletRequest req ,String oriName) {
		
		String path = getFilePath(req);
		
		int i = oriName.lastIndexOf(".");
		String ext = oriName.substring(i);
		
		String uuid = UUID.randomUUID().toString();
		String newName = uuid + ext;
		
		File f1 = new File(path+"/"+oriName);
		File f2 = new File(path+"/"+newName);
		
		logger.debug("f1 : " + f1);
		logger.debug("f2 : " + f2);
		 
		//파일명 수정
		f1.renameTo(f2);
		
		return newName;
		
	}
	
	public MultipartRequest uploadFile(HttpServletRequest req) {
		
		//파일 경로 구하기
		String path = getFilePath(req);
		
		//최대 업로드 파일 크기
		int maxSize = 1024 * 1024 * 10;
		
		//파일 업로드
		MultipartRequest mr = null;
				
		try {
			mr = new MultipartRequest(req, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {
			logger.error("MultipartRequest uploadFile eorror : " + e.getMessage());
		}
		
		return mr;
	}


}
