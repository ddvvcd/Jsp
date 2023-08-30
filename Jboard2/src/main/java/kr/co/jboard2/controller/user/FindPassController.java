package kr.co.jboard2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.UserService;

@WebServlet("/user/findPass.do")
public class FindPassController extends HttpServlet{

	private static final long serialVersionUID = -2456492756360307051L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/findPass.jsp");
		dispatcher.forward(req, resp);
	}
	
	//findPass의 form 태그에서 post로 받았으므로 doPost 추가 (입력필드 findPass의 데이터 uid, email, auth가 form태그 통하여 여기로 전송됨)
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uid = req.getParameter("uid");
		
		HttpSession session = req.getSession();
		session.setAttribute("uid", uid);
		
		//sendRedirect는 get이므로 findPassChangeController doPost를 doGet으로 수정
		resp.sendRedirect("/Jboard2/user/findPassChange.do");
		
	}
}
