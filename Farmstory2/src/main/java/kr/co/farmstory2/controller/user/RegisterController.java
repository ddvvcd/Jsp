package kr.co.farmstory2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.dto.UserDTO;
import kr.co.farmstory2.service.UserService;

@WebServlet("/user/register.do")
public class RegisterController extends HttpServlet{

	private static final long serialVersionUID = 7145469640436751507L;
	UserService service = new UserService();
	
	//doGet으로 받아서 페이지를 포워딩 (연결) (이게 없으면 출력이 안 됨)
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/register.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid   = req.getParameter("uid");
		String pass1 = req.getParameter("pass1");
		String name  = req.getParameter("name");
		String nick  = req.getParameter("nick");
		String email = req.getParameter("email");
		String hp    = req.getParameter("hp");
		String regip = req.getRemoteAddr();
		
		UserDTO dto = new UserDTO();
		
		dto.setUid(uid);
		dto.setPass(pass1);
		dto.setName(name);
		dto.setNick(nick);
		dto.setEmail(email);
		dto.setHp(hp);
		dto.setRegip(regip);
		
		service.insertUser(dto);
		
		resp.sendRedirect("/Farmstory2/user/login.do?success=200");
		
	}
}
