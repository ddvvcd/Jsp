package kr.co.farmstory2.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.service.ArticleService;

@WebServlet("/board/delete.do")
public class DeleteController extends HttpServlet{

	private static final long serialVersionUID = -4550516370748336927L;
	ArticleService service = new ArticleService();
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no    = req.getParameter("no");
		String group = req.getParameter("group");
		String cate  = req.getParameter("cate");
		
		service.deleteArticle(no);
		
		resp.sendRedirect("/Farmstory2/board/list.do?group="+group+"&cate="+cate);
	}
	
}
