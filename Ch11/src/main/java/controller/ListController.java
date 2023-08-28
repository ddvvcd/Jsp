package controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MemberDTO;
import service.MembereService;

@WebServlet("/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = 2053973570967237161L;
	
	Logger logger = Logger.getGlobal();
	
	private MembereService service = MembereService.INSTANCE;
	
	@Override
	public void init() throws ServletException {
		logger.info("ListController init()...1");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("ListController doGet()...1");

		List<MemberDTO> members = service.selectrMembers();
		
		//List의 members는 list.jsp에서 <forEach>문에서 반복문으로 들어감
		req.setAttribute("members", members);
		
		//View 포워딩
		RequestDispatcher dispatcher = req.getRequestDispatcher("/list.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("ListController doPost()...1");
		
		
	}
	
}
