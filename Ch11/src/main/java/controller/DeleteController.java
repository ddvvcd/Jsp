package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MembereService;

@WebServlet("/delete.do")
public class DeleteController extends HttpServlet{

	private static final long serialVersionUID = -893782725067255864L;
	
	//private MembereService service = new membereservice
	
	@Override
	public void init() throws ServletException {
	
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
