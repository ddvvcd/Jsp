<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- ctrl+space해서 찾을 것 --> <!-- core 라이브러리를 c로 선언 -->
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %> <!-- ctrl+space해서 찾을 것 --> <!-- functions 라이브러리를 f로 선언 -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<!-- 
			날짜 : 2023/08/22
			이름 : 박성용
			내용 : JSP 표현언어 연산자 실습하기
		 -->
	</head>
	<body>
		<h3>4.JSTL</h3>
		
		<h4>스크립트릿</h4>
		<%
			//변수선언
			String str = "hello";
			out.print("<p>str : "+str+"</p>");
			
			int num1 = 1;
			int num2 = 2;
			int num3 = 3;
			
			//조건문
			if(num1 < num2){
				out.print("<p>num1은 num2보다 작다.</p>");
			}
			
			if(num1 > num2){
				out.print("<p>num1은 num2보다 크다.</p>");
			}else{
				out.print("<p>num1은 num2보다 작다.</p>");
			}
			
			if(num1 > num2){
				out.print("<p>num1은 num2보다 크다.</p>");
			}else if(num2 > num3){
				out.print("<p>num2는 num3보다 크다.</p>");
			}else{
				out.print("<p>num3가 가장 크다.");
			}
			//반복문
			for(int i =1; i<=5; i++){
				out.print("<p>i : " + i + "</p>");
			}
			
			int sum = 0;
			for(int k=1; k<=10; k++){
				sum += k;
			}
			
			out.print("<p>1부터 10까지의 합 : " + sum + "</p>");
			
			String[] persons = {"김유신", "김춘추", "장보고", "강감찬", "이순신"};
			for(String person : persons){
				out.print("<p>person : " + person + "</p>");
			}
			
			//자주 쓰는 문자열 처리
			String hello = "Hello Korea";
			out.print("<p>문자열 길이 : "+hello.length()+"</p>");
			out.print("<p>문자열 자르기 : "+hello.substring(6, 10)+"</p>");
			out.print("<p>문자열 교체 : "+hello.replace("Korea", "Busan")+"</p>");
			out.print("<p>문자열 인덱스 : "+hello.indexOf("e")+"</p>");
			
		%>
		
		<h4>JSTL</h4>
		<!-- 변수선언 -->
		<c:set var="str" value="hello"/>
		<p>str : ${str}</p>
		
		<c:set var="num1" value="1"/>
		<c:set var="num2" value="2"/>
		<c:set var="num3" value="3"/>
		
		<!-- 조건문 -->
		<c:if test="${num1 lt num2}">
			<p>num1이 num2보다 작다.</p> <!-- html 검사페이지로 보면 위쪽 스크립트릿과 서로 같음 -->
		</c:if>
		
		<c:choose>
			<c:when test="${num1 gt num2}"> <!-- if -->
				<p>num1이 num2보다 크다.</p>
			</c:when> 	
			<c:otherwise>
				<p>num1이 num2보다 작다.</p> 	<!-- else -->
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${num1 gt num2}"> <!-- if -->
				<p>num1이 num2보다 크다.</p>
			</c:when> 	
			<c:when test="${num2 gt num3}"> <!-- else if -->
				<p>num2가 num3보다 크다.</p>
			</c:when> 	
			<c:otherwise>
				<p>num3가 가장 크다.</p> 		<!-- else -->
			</c:otherwise>
		</c:choose>
		
		<!-- 반복문 -->
		<c:forEach var="i" begin="1" end="5"> 
			<p>i : ${i}</p>
		</c:forEach>
		
		<c:set var="sum" value="0"/>
		<c:forEach var="k" begin="1" end="10">
			<c:set var="sum" value="${sum + k}"/>
		</c:forEach>
		<p>1부터 10까지 합 : ${sum}</p>
		
		<c:set var="persons">김유신,김춘추,장보고,강감찬,이순신</c:set>
		<c:forEach var="person" items="${persons}">
			<p>person : ${person}</p>
		</c:forEach>
		
		
		<!-- 자주 쓰는 문자열 처리 -->
		<c:set var="hello" value="Hello Korea"/>
		<p>문자열 길이 : ${f:length(hello)}</p>
		<p>문자열 자르기 : ${f:substring(hello, 6, 11)}</p>
		<p>문자열 교체 : ${f:replace(hello, "Korea", "Busan")}</p>
		<p>문자열 인덱스 : ${f:indexOf(hello, "e")}</p>
	</body>
</html>