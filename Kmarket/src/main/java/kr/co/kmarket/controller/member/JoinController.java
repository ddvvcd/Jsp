package kr.co.kmarket.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.MemberDTO;
import kr.co.kmarket.service.MemberService;

@WebServlet("/member/join.do")
public class JoinController extends HttpServlet {

	private static final long serialVersionUID = -9165570067205844583L;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	MemberService service = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String type = req.getParameter("type");
		
		if ("normal".equals(type)) {
		    // 개인 구매회원 약관 페이지로 리디렉션
		    resp.sendRedirect("/Kmarket/member/signup.jsp");
		} else if ("seller".equals(type)) {
		    // 판매회원 약관 페이지로 리디렉션
		    resp.sendRedirect("/Kmarket/member/signup.jsp");
		}
		
		req.getRequestDispatcher("/member/join.jsp").forward(req, resp);
	}
}
