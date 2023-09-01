package kr.co.jboard2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.db.SQL;
import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.FileDTO;

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
				//파일 정보
				FileDTO fileDto = new FileDTO();
				fileDto.setFno(rs.getInt(12));
				fileDto.setAno(rs.getInt(13));
				fileDto.setOfile(rs.getNString(14));
				fileDto.setSfile(rs.getString(15));
				fileDto.setDownload(rs.getInt(16));
				fileDto.setRdate(rs.getString(17));
				article.setFileDto(fileDto);
			}
			
			logger.info("ArticleDAO selectArticle...2");
			close();
			
		} catch (Exception e) {
			logger.error("ArticleDAO selectArticle error : " + e.getMessage());
		}
		
		return article;
	}
	
	public List<ArticleDTO> selectArticles(int start, String search) {
		
		List<ArticleDTO> articles = new ArrayList<>();
		
		try {
			logger.info("ArticleDAO selectArticles...1");

			conn = getConnection();
			if(search == null) {
				psmt = conn.prepareStatement(SQL.SELECT_ARTICLES);
				psmt.setInt(1, start);
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_ARTICLES_FOR_SEARCH);
				psmt.setString(1, "%"+search+"%");
				psmt.setInt(2, start);
			}
			
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
		
		try {
			logger.info("ArticleDAO updateArticle...1");
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getNo());
			psmt.executeUpdate();
			close();
			
			logger.info("ArticleDAO updateArticle...2");

		} catch (Exception e) {
			logger.error("ArticleDAO updateArticle error : " + e.getMessage());
		}
	}
	
	public void deleteArticle(String no) {
		
		try {
			
			logger.info("ArticleDAO deleteArticle...1");
			
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_ARTICLE);
			psmt.setString(1, no);
			psmt.setString(2, no);
			psmt.executeUpdate();
			close();
			
			logger.info("ArticleDAO deleteArticle...2");

		} catch (Exception e) {
			logger.error("ArticleDAO deleteArticle error : " + e.getMessage());
		}
	}
	
	//여기서 부턴 추가
	
<<<<<<< HEAD
	public int selectCountTotal(String search) {
=======
	public int selectCountTotal() {
>>>>>>> 08f7dbee393fd43b9eb7ac06d6102beac78b3667
		
		int total = 0;
		
		try {
			conn = getConnection();
<<<<<<< HEAD
			if(search == null) {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL);

			}else {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL_FOR_SEARCH);
				psmt.setString(1, "%"+search+"%");
			}
			
=======
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL);
>>>>>>> 08f7dbee393fd43b9eb7ac06d6102beac78b3667
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
		} catch (Exception e) {
			logger.error("ArticleDAO selectCountTotal error : " + e.getMessage());
		}
		
		return total;
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
	
<<<<<<< HEAD
	//댓글 작성
	public int insertComment(ArticleDTO dto) {
		
		int result = 0;
=======
	public void insertComment(ArticleDTO dto) {
>>>>>>> 08f7dbee393fd43b9eb7ac06d6102beac78b3667
		
		try {
			logger.info("ArticleDAO insertComment...1");
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_COMMENT);
			psmt.setInt(1, dto.getParent());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getWriter());
			psmt.setString(4, dto.getRegip());
<<<<<<< HEAD
			result = psmt.executeUpdate();
=======
			psmt.executeUpdate();
>>>>>>> 08f7dbee393fd43b9eb7ac06d6102beac78b3667
			close();
			
			logger.info("ArticleDAO insertComment...2");
			
		} catch (Exception e) {
			logger.error("ArticleDAO insertComment error : " + e.getMessage());
		}
<<<<<<< HEAD
		
		return result;
=======
>>>>>>> 08f7dbee393fd43b9eb7ac06d6102beac78b3667
	}
	
	public void updateArticleForCommentPlus(String no) {
		
		try {
			logger.info("ArticleDAO updateArticleForCommentPlus...1");
			
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE_FOR_COMMENT_PLUS);
			psmt.setString(1, no);
			psmt.executeUpdate();
			close();
			
			logger.info("ArticleDAO updateArticleForCommentPlus...2");

		} catch (Exception e) {
			logger.error("ArticleDAo updateArticleForCommentPlus error : " + e.getMessage());
		}
	}
	
	public void updateArticleForCommentMinus(String no) {
		
		try {
			logger.info("ArticleDAO updateArticleForCommentMinus...1");
			
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE_FOR_COMMENT_MINUS);
			psmt.setString(1, no);
			psmt.executeUpdate();
			close();
			
			logger.info("ArticleDAO updateArticleForCommentMinus...2");

		} catch (Exception e) {
			logger.error("ArticleDAo updateArticleForCommentMinus error : " + e.getMessage());
		}
	}
	
	public void updateComment(String no, String content) {
		try {
			logger.error("ArticleDAO updateComment...1");

			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_COMMENT);
			psmt.setString(1, no);
			psmt.setString(2, content);
			psmt.executeUpdate();
			close();
			
			logger.error("ArticleDAO updateComment...2");
			
		} catch (Exception e) {
			logger.error("ArticleDAO updateComment error : " + e.getMessage());
		}
	}
	
<<<<<<< HEAD
	public int deleteComment(String no) {
		int result = 0;
=======
	public void deleteComment(String no) {
>>>>>>> 08f7dbee393fd43b9eb7ac06d6102beac78b3667
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_COMMENT);
			psmt.setString(1, no);
<<<<<<< HEAD
			result = psmt.executeUpdate();
=======
			psmt.executeUpdate();
>>>>>>> 08f7dbee393fd43b9eb7ac06d6102beac78b3667
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
<<<<<<< HEAD
		return result;
=======
>>>>>>> 08f7dbee393fd43b9eb7ac06d6102beac78b3667
	}
	
}
