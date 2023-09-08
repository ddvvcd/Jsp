package kr.co.farmstory2.controller.market;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dto.OrderDTO;
import kr.co.farmstory2.dto.ProductDTO;
import kr.co.farmstory2.service.ProductService;

@WebServlet("/market/order.do")
public class OrderController extends HttpServlet {

	private static final long serialVersionUID = -9147289505350596743L;
	
	ProductService service = new ProductService();
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pName      = req.getParameter("pName");
		String pNo        = req.getParameter("pNo");
		String delivery   = req.getParameter("delivery");
		String price      = req.getParameter("price");
		String total      = req.getParameter("total");
		String finalPrice = req.getParameter("finalPrice");
		String thumb240      = req.getParameter("thumb240");
		
		req.setAttribute("pName", pName);
		req.setAttribute("pNo", pNo);
		req.setAttribute("delivery", delivery);
		req.setAttribute("price", price);
		req.setAttribute("total", total);
		req.setAttribute("finalPrice", finalPrice);
		req.setAttribute("thumb240", thumb240);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/market/order.jsp");
		dispatcher.forward(req, resp);
	}
}
