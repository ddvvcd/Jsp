package controller.user1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.User1DAO;
import dto.User1DTO;
import service.User1Service;

@WebServlet("/user1/register.do")
public class RegisterController extends HttpServlet{

	private static final long serialVersionUID = -6039486181754851659L;
	
	private User1Service service = new User1Service();
	
	@Override
	public void init() throws ServletException {
	
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispathcer = req.getRequestDispatcher("/user1/register.jsp");
		dispathcer.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//인코딩은 자주 써야 하니 필터 처리
		//req.setCharacterEncoding("UTF-8");
		String uid  = req.getParameter("uid");
		String name = req.getParameter("name");
		String hp   = req.getParameter("hp");
		String age  = req.getParameter("age");
		
		//try ~ catch문 작성 -> DAO가 수행 -> DAO 작성
		User1DTO dto = new User1DTO();
		dto.setUid(uid);
		dto.setName(name);
		dto.setHp(hp);
		dto.setAge(age);
		
		service.insertUser1(dto);
		
		resp.sendRedirect("/Ch10/user1/list.do");
		
	}
	
}
