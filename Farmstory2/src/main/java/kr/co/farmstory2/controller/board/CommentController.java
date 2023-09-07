package kr.co.farmstory2.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.service.ArticleService;

@WebServlet("/board/comment.do")
public class CommentController extends HttpServlet{

	private static final long serialVersionUID = 3096232538471515350L;
	ArticleService service = new ArticleService();
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//페이지 띄우기 위해서 작성?
		String group = req.getParameter("group");
		String cate  = req.getParameter("cate");
		
		//댓글 삭제
		String kind = req.getParameter("kind");
		String no = req.getParameter("no");
		
		int result = 0;
		
		switch(kind) {
			case "REMOVE":
				result = service.deleteComment(no);
				break;
		}
		
		//JSON 출력
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		resp.getWriter().print(json);
		
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//view.jsp에서 넘겨받은 에이젝스 요청을 처리 시작
		String parent  = req.getParameter("parent");
		String content = req.getParameter("content");
		String writer  = req.getParameter("writer");
		String regip   = req.getRemoteAddr();
		
		//댓글 입력
		req.setAttribute("parent", parent);
		req.setAttribute("content", content);
		req.setAttribute("writer", writer);
		req.setAttribute("regip", regip);
		
		
		logger.debug("parent : " + parent);
		logger.debug("content : " + content);
		logger.debug("writer : " + writer);
		logger.debug("regip : " + regip);
		
		//DTO 생성
		ArticleDTO dto = new ArticleDTO();
		dto.setParent(parent);
		dto.setContent(content);
		dto.setWriter(writer);
		dto.setRegip(regip);
		
		//댓글 입력
		int result = service.insertComment(dto);
		
		//댓글 삭제
		
		
		//리다이렉트(폼 전송) (이거 할 필요 없다고 하셨음)
		//resp.sendRedirect("/Farmstory2/board/view.do?no="+parent);
		
		//Json 출력(AJAX 요청)
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		resp.getWriter().print(json);
	}
}
