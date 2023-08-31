package kr.co.jboard2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.service.ArticleService;

@WebServlet("/view.do")
public class ViewController extends HttpServlet{

	private static final long serialVersionUID = -1638433365507040980L;

	ArticleService service = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//번호로 글 목록을 일치시켜서 조회
		String no = req.getParameter("no");
		
		//글 내용 조회
		ArticleDTO article = service.selectArticle(no);
		
		//댓글 조회
		List<ArticleDTO> comments = service.selectComments(no);
		
		//View 공유 참조
		req.setAttribute("no", no);
		req.setAttribute("article", article);
		req.setAttribute("comments", comments);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view.jsp");
		dispatcher.forward(req, resp);
	}
}
