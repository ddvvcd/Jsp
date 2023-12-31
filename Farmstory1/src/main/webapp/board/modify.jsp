<%@page import="kr.farmstory1.dto.ArticleDTO"%>
<%@page import="kr.farmstory1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");
	String group = request.getParameter("group");
	String cate  = request.getParameter("cate");
	String no    = request.getParameter("no"); //1. (2)요청 받음
	
	ArticleDAO dao = new ArticleDAO(); 
	ArticleDTO dto = dao.selectArticle(no); //1. 글번호 no 필요하므로 요청(1)
											//1. 
	
	pageContext.include("./_aside"+group+".jsp");
%>
<section class="modify">
    <h3>글수정</h3>
    <article>
        <form action="/Farmstory1/board/proc/modifyProc.jsp" method="post"> <!-- 여기 자료를 modifyProc로 전송 -->
        	<input type="hidden" name="group" value="<%= group %>">
        	<input type="hidden" name="cate" value="<%= cate %>">
        	<input type="hidden" name="no" value="<%= dto.getNo() %>"> <!-- 추가 필드/화면에 안 보이게 hidden -->
            <table>
                <tr>
                    <td>제목</td>									<!-- (3) -->
                    <td><input type="text" name="title" value="<%= dto.getTitle() %>" placeholder="제목을 입력하세요."/></td>
                </tr>
                <tr>
                    <td>내용</td>
                    <td>
                        <textarea name="content"><%= dto.getContent() %></textarea>  <!-- (4)-->                             
                    </td>
                </tr>
                <tr>
                    <td>첨부</td>
                    <td><input type="file" name="file"/></td>
                </tr>
            </table>
            <div>
                <a href="#" class="btnCancel">취소</a>
                <input type="submit"  class="btnWrite" value="수정완료">
            </div>
        </form>
    </article>
</section>
<!-- 내용 끝 -->

        </article>
    </section>
</div>			
<%@ include file="../_footer.jsp" %>