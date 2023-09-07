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
import kr.co.farmstory2.service.ProductService;

@WebServlet("/admin/productList.do")
public class ProductionListController extends HttpServlet{

	private static final long serialVersionUID = 2687586710997558482L;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	ProductService service = new ProductService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pno 		   = req.getParameter("pno");
		String type        = req.getParameter("type");
		String productName = req.getParameter("productName");
		String price       = req.getParameter("price");
		String delivery    = req.getParameter("delivery");
		String stock       = req.getParameter("stock");
		String sold        = req.getParameter("sold");
		String thumb120    = req.getParameter("thumb120");
		String thumb240    = req.getParameter("thumb240");
		String thumb750    = req.getParameter("thumb750");
		String seller      = req.getParameter("seller");
		String etc         = req.getParameter("etc");
		String rdate       = req.getParameter("rdate");
		
	
		
		//현재페이지 게시글 조회
		List<ProductDTO> products = service.selectProducts(type);
		
		req.setAttribute("products", products);
		req.setAttribute("pno", pno);
		req.setAttribute("type", type);
		req.setAttribute("productName", productName);
		req.setAttribute("price", price);
		req.setAttribute("delivery", delivery);
		req.setAttribute("stock", stock);
		req.setAttribute("sold", sold);
		req.setAttribute("thumb120", thumb120);
		req.setAttribute("thumb240", thumb240);
		req.setAttribute("thumb750", thumb750);
		req.setAttribute("seller", seller);
		req.setAttribute("etc", etc);
		req.setAttribute("rdate", rdate);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/productList.jsp");
		dispatcher.forward(req, resp);
		
		
	}
	
	
}
