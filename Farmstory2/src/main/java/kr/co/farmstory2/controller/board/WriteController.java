package kr.co.farmstory2.controller.board;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.dto.FileDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.FileService;

@WebServlet("/board/write.do")
public class WriteController extends HttpServlet{

	private static final long serialVersionUID = 4770924791750877308L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	ArticleService aService = new ArticleService();
	FileService fService = new FileService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String group = req.getParameter("group");
		String cate  = req.getParameter("cate");
		
		
		req.setAttribute("group", group);
		//카테고리
		req.setAttribute("cate", cate);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/write.jsp");
		dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//업로드 경로 구하기 (ArticleDAO에 메소드 만들어 놓음)
		aService.getFilePath(req);
		
		//파일 업로드 (ArticleDAO에 메소드 만들어 놓음)
		MultipartRequest mr = aService.uploadFile(req);
		
		//폼 데이터 수신
		String group   = mr.getParameter("group");
		String cate    = mr.getParameter("cate");
		String title   = mr.getParameter("title");
		String content = mr.getParameter("content");
		String writer  = mr.getParameter("writer");
		String oriName = mr.getOriginalFileName("file"); //원래 파일 이름?
		String regip   = req.getRemoteAddr();
		
		logger.debug("group : " + group);
		logger.debug("cate : " + cate);
		logger.debug("title : " + title);
		logger.debug("content : " + content);
		logger.debug("writer : " + writer);
		logger.debug("oriName : " + oriName);
		logger.debug("regip : " + regip);
		
		//DTO 생성
		ArticleDTO dto = new ArticleDTO();
		dto.setCate(cate);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setWriter(writer);
		dto.setFile(oriName);
		dto.setRegip(regip);
		
		//글(DB) Insert
		int no = aService.insertArticle(dto);
		
		logger.debug("no : " + no);
		
		//파일명 수정 및 파일테이블 INSERT (ArticleDAO에 메소드 만들어 놓음)
		if(oriName != null) {
			
			String newName = aService.renameToFile(req, oriName);
			
			//파일 테이블 Insert
			FileDTO fileDto = new FileDTO();
			fileDto.setAno(no);
			fileDto.setOriName(oriName);
			fileDto.setNewName(newName);
			
			logger.debug("no : " + no);
			logger.debug("oriName : " + oriName);
			logger.debug("newName : " + newName);
			
			
			fService.insertFile(fileDto);
		}
		
		//리다이렉트
		resp.sendRedirect("/Farmstory2/board/list.do?group="+group+"&cate="+cate);
		
		}
}
