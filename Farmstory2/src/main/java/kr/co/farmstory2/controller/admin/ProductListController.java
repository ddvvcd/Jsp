package kr.co.farmstory2.controller.admin;

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

import kr.co.farmstory2.dto.ProductDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.ProductService;

@WebServlet("/admin/productList.do")
public class ProductListController extends HttpServlet{

	private static final long serialVersionUID = 2687586710997558482L;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	ProductService pService = new ProductService();
	ArticleService aService = new ArticleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/productList.jsp");
		dispatcher.forward(req, resp);
		
	}
}
