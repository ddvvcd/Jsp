package kr.co.jboard2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.db.SQL;
import kr.co.jboard2.dto.ArticleDTO;

public class ArticleDAO extends DBHelper {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int insertArticle(ArticleDTO dto) {
		
		int no = 0;
		
		try {
			logger.info("ArticleDAO insertArticle...1");
			conn = getConnection();
			conn.setAutoCommit(false); //Transaction 처리 시작
			
			stmt = conn.createStatement(); //쿼리문 파라미터가 없으면 stmt
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE); //쿼리문 파라미터가 있으면 psmt
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getFile());
			psmt.setString(4, dto.getWriter());
			psmt.setString(5, dto.getRegip());
			psmt.executeUpdate(); //여기서 insert 하고
			rs = stmt.executeQuery(SQL.SELECT_MAX_NO); //여기서 다시 새로 작업 함
			conn.commit(); //Transaction 작업 확정
			
			if(rs.next()) {
				no = rs.getInt(1);
			}
			
			close();
			logger.info("ArticleDAO insertArticle...2");
		}catch(Exception e){
			logger.error("ArticleDAO insertArticle error : " + e.getMessage());
		}
		
		return no;
	}
	
	public ArticleDTO selectArticle(String no) {
		
		ArticleDTO article = null;
		
		try {
			logger.info("ArticleDAO selectArticle...1");
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLE);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				article = new ArticleDTO();
				article.setNo(rs.getInt(1));
				article.setParent(rs.getInt(2));
				article.setComment(rs.getInt(3));
				article.setCate(rs.getString(4));
				article.setTitle(rs.getString(5));
				article.setContent(rs.getString(6));
				article.setFile(rs.getInt(7));
				article.setHit(rs.getInt(8));
				article.setWriter(rs.getString(9));
				article.setRegip(rs.getString(10));
				article.setRdate(rs.getString(11));
			}
			
			logger.info("ArticleDAO selectArticle...2");
			close();
			
		} catch (Exception e) {
			logger.error("ArticleDAO selectArticle error : " + e.getMessage());
		}
		
		return article;
	}
	
	public List<ArticleDTO> selectArticles(int start) {
		
		List<ArticleDTO> articles = new ArrayList<>();
		
		try {
			logger.info("ArticleDAO selectArticles...1");

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
				dto.setNick(rs.getString(12));
				articles.add(dto);
			}
			
			rs.close();
			psmt.close();
			conn.close();
			
			logger.info("ArticleDAO selectArticles...2");

		} catch (Exception e) {
			logger.error("ArticleDAO selectArticles error : " + e.getMessage());
		}
		
		return articles;
	}
	
	public void updateArticle(ArticleDTO dto) {
		
	}
	
	public void deleteArticle(int no) {
		
	}
	
	public List<ArticleDTO> selectComments(String parent) {
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
				dto.setFile(rs.getInt(7));
				dto.setHit(rs.getInt(8));
				dto.setWriter(rs.getString(9));
				dto.setRegip(rs.getString(10));
				dto.setRdate(rs.getString(11));
				dto.setNick(rs.getString(12));
				
				comments.add(dto);

			}
			
		} catch (Exception e) {
			logger.error("ArticleDAO selectComments error : " + e.getMessage());
		}
		
		return comments;
	}
	
}
