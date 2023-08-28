<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	String target = request.getParameter("target"); //추가(2)
	String group  = request.getParameter("group");
	String cate   = request.getParameter("cate");
	String no     = request.getParameter("no");
%>
<div id="user">
	<section class="login">
		<form action="/Farmstory1/user/loginProc.jsp" method="post">
			<input type="text" name="target" value="<%= target %>"> <!-- 하나씩 추가해줌(1) -->
			<input type="text" name="group" value="<%= group %>">  <!-- 추가(3) -->
			<input type="text" name="cate" value="<%= cate %>">
			<input type="text" name="no" value="<%= no %>">
			<table border="0">
				<tr>
					<td><img src="/Farmstory1/images/login_ico_id.png" alt="아이디"></td>
					<td><input type="text" name="uid" placeholder="아이디 입력"></td>
				</tr>
				<tr>
					<td><img src="/Farmstory1/images/login_ico_pw.png" alt="비밀번호"></td>
					<td><input type="password" name="pass" placeholder="비밀번호 입력"></td>
				</tr>
			</table>
			<input type="submit" value="로그인" class="btnLogin">
		</form>
		<div>
			<h3>회원 로그인 안내</h3>
			<p>아직 회원이 아니시면 회원으로 가입하세요.</p>
			<a href="/Farmstory1/user/terms.jsp">회원가입</a>
		</div>
	</section>
</div>
<%@ include file="../_footer.jsp"%>
