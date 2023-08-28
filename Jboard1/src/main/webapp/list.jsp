<%@page import="kr.co.jboard1.dto.ArticleDTO"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@page import="kr.co.jboard1.dto.UserDTO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %> <!-- include는 상대경로만 가능 -->
<%
	request.setCharacterEncoding("UTF-8");
	String pg = request.getParameter("pg");//페이지 가져옴
	
	//DAO 객체 생성
	ArticleDAO dao = new ArticleDAO();

	//페이지 관련 변수 선언
	int start = 0;
	int currentPage = 1;
	int total = 0;
	int lastPageNum = 0;
	int pageGroupCurrent = 1; //페이지 10개씩 분류하기
	int pageGroupStart = 1;
	int pageGroupEnd = 0;
	int pageStartNum = 0;
	
	if(pg != null){
		currentPage = Integer.parseInt(pg); //String pg를 int로 변환
	}
	
	//limit 시작값 계산
	start = (currentPage - 1) * 10;
	
	//전체 게시물 갯수 조회
	total = dao.selectCountTotal(); //메소드 이름 부터 지정 -> 결과를 토탈에 대입
	
	//페이지 번호 계산
	if(total % 10 == 0){
		lastPageNum = (total / 10);
	}else{
		lastPageNum = (total / 10) + 1;
	}
	
	//페이지 그룹 계산
	pageGroupCurrent = (int) Math.ceil(currentPage / 10.0);
	pageGroupStart = (pageGroupCurrent - 1) * 10 + 1;
	pageGroupEnd = pageGroupCurrent * 10;
	
	if(pageGroupEnd > lastPageNum){
		pageGroupEnd = lastPageNum;
	}
	
	//페이지 시작번호 계산
	pageStartNum = total - start;
	
	
	//현재 페이지 게시물 조회
	List<ArticleDTO> articles = dao.selectArticles(start);
%>
<main>
    <section class="list">
        <h3>글목록</h3>
        <article>
            <table border="0">
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th>날짜</th>
                    <th>조회</th>
                </tr>
                <%
                for(ArticleDTO article : articles){
                %>
                <tr>
                    <td><%= pageStartNum-- %> </td> <!-- 글번호 시작번호로 출력 -->
                    <td><a href="/Jboard1/view.jsp?no=<%= article.getNo() %>"><%= article.getTitle() %></a>&nbsp;[<%= article.getComment() %>]</td>
                    <!-- list에서 view로 글 번호를 넘겨줌 -->
                    <td><%= article.getNick() %></td> <!-- 닉네임으로 출력 -->
                    <td><%= article.getRdate() %></td>
                    <td><%= article.getHit() %></td>
                </tr>
                <% } %>
            </table>
        </article>

        <!-- 페이지 네비게이션 -->
        <div class="paging">
            <!-- 이전 버튼 누르면 이전 그룹으로 넘어감 -->
            <% if(pageGroupStart > 1){ %>
            <a href="/Jboard1/list.jsp?pg=<%=pageGroupStart -1 %>" class="prev">이전</a>
            <% } %>
            <% for(int i=pageGroupStart; i<=pageGroupEnd; i++){ %>
            <!-- 현재 페이지 번호 하이라이트 -->
            <a href="/Jboard1/list.jsp?pg=<%=i %>" class="num <%= (currentPage == i)?"current":""%> "><%= i %></a><!-- 시작하는 페이지 -->         
            <% } %>
            <!-- 다음 버튼 누르면 다음 그룹으로 넘어감 -->
            <% if(pageGroupEnd < lastPageNum){ %>
            <a href="/Jboard1/list.jsp?pg=<%= pageGroupEnd + 1 %>" class="next">다음</a>
            <% } %>
        </div>

        <!-- 글쓰기 버튼 -->
        <a href="/Jboard1/write.jsp" class="btnWrite">글쓰기</a>
    </section>
</main>
<%@ include file="./_footer.jsp" %> <!-- include는 상대경로만 가능 -->