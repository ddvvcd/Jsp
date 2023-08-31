package kr.co.jboard2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.ArticleService;

@WebServlet("/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = 7787169292569437228L;
	
	ArticleService service = ArticleService.INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//현재 세션 가져오기
		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
		
		int page=1;
		int pg = 10;
		int start = (page -1) * pg;
		
		if(sessUser != null) {
			List<ArticleDTO> articles = service.selectArticles(start);
			
			//List의 articles는 list.jsp에서 <forEach>문에서 반복문으로 들어감
			req.setAttribute("articles", articles);

			RequestDispatcher dispatcher = req.getRequestDispatcher("/list.jsp");
			dispatcher.forward(req, resp);
		}else {
			resp.sendRedirect("/Jboard2/user/login.do?success=101");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
