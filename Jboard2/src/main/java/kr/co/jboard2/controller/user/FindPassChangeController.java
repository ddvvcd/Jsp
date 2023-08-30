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

import kr.co.jboard2.service.UserService;

@WebServlet("/user/findPassChange.do")
public class FindPassChangeController extends HttpServlet{

	private static final long serialVersionUID = 3184963205046179155L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//'비밀번호 찾기'에서 인증 받아야 다음 페이지로 넘어가는 기능?
		//(세션을 이용)
		HttpSession session = req.getSession();
		String uid = (String) session.getAttribute("uid");
		
		//'비밀번호 찾기'에서 인증 안 받았으면 '비밀번호 찾기 페이지'에서 인증받도록 리다이렉트
		if(uid == null) {
			resp.sendRedirect("/Jboard2/user/findPass.do");
		}else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/user/findPassChange.jsp");
			dispatcher.forward(req, resp);
		}
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/findPassChange.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uid   = req.getParameter("uid");
		String pass = req.getParameter("pass1");
		
		service.updateUserPass(uid, pass);
		
		resp.sendRedirect("/Jboard2/user/login.do?success=300");
		
	}
}	
