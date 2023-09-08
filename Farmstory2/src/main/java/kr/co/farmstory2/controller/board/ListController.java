package kr.co.farmstory2.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.service.ArticleService;

@WebServlet("/board/list.do")
public class ListController extends HttpServlet {

	private static final long serialVersionUID = 3792246722935648540L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	ArticleService service = new ArticleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String group = req.getParameter("group");
		String cate  = req.getParameter("cate");
		String pg    = req.getParameter("pg");
		
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		req.setAttribute("pg", pg);
		
		logger.debug("group : " + group);
		logger.debug("cate : " + cate);
		logger.debug("pg : " + pg);
		
		// 현재 페이지 번호
		int currentPage = service.getCurrentPage(pg);
		
		// 전체 게시물 갯수 
		int total = service.selectCountTotal(cate);
		
		// 마지막 페이지 번호
		int lastPageNum = service.getLastPageNum(total);
		
		// 페이지 그룹 start, end 번호
		int[] result = service.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호
		int pageStartNum = service.getPageStartNum(total, currentPage);
		
		// 시작 인덱스
		int start = service.getStartNum(currentPage);
		
		//현재 페이지 게시글 조회
		List<ArticleDTO> articles = service.selectArticles(cate, start);
		
		req.setAttribute("articles", articles);
		req.setAttribute("cate", cate);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("total", total);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("result", result);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/list.jsp");
		dispatcher.forward(req, resp);
	}
	
}
