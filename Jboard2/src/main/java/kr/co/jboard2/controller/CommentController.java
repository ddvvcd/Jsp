package kr.co.jboard2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

<<<<<<< HEAD
import com.google.gson.JsonObject;

=======
>>>>>>> 08f7dbee393fd43b9eb7ac06d6102beac78b3667
import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.service.ArticleService;

@WebServlet("/comment.do")
public class CommentController extends HttpServlet{

	private static final long serialVersionUID = 7686345524627400840L;
	private ArticleService service = ArticleService.INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
<<<<<<< HEAD
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String kind = req.getParameter("kind");
		String no   = req.getParameter("no");
		
		int result = 0;
		
		switch(kind) {
			case "REMOVE":
				result = service.deleteComment(no);
				break;
		}
		
		//Json 출력
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		resp.getWriter().print(json);
			
		
	}
	
	@Override
=======
>>>>>>> 08f7dbee393fd43b9eb7ac06d6102beac78b3667
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String parent  = req.getParameter("parent");
		String content = req.getParameter("content");
		String writer  = req.getParameter("writer");
<<<<<<< HEAD
		String regip   = req.getRemoteAddr(); //regip는 파라미터로 넘겨받는게 아님!
=======
		String regip   = req.getParameter("regip");
>>>>>>> 08f7dbee393fd43b9eb7ac06d6102beac78b3667
		
		logger.debug("parent : " + parent);
		logger.debug("content : " + content);
		logger.debug("writer : " + writer);
		logger.debug("regip : " + regip);
		
		ArticleDTO dto = new ArticleDTO();
		dto.setParent(parent);
		dto.setContent(content);
		dto.setWriter(writer);
		dto.setRegip(regip);
		
		//댓글 입력
<<<<<<< HEAD
		int result = service.insertComment(dto);
		
		//리다이렉트(폼 전송)
		resp.sendRedirect("/Jboard2/view.do?no="+parent);
		
		//Json 출력(AJAX 요청)
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		resp.getWriter().print(json);
=======
		service.insertComment(dto);
		
		//댓글 카운트 수정 plus
		service.updateArticleForCommentPlus(regip);
		
		//리다이렉트
		resp.sendRedirect("/Jboard2/view.do?no=\"+parent");
>>>>>>> 08f7dbee393fd43b9eb7ac06d6102beac78b3667
	}
}
