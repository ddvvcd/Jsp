<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<script>
	$(function(){
		//작성완료 버튼 클릭 시 에이젝스로 댓글 전송
		$('#btnComment').click(function(e){
			e.preventDefault(); //기본 동작 방지
			
			//댓글 데이터 수집
			const parent = $('#formComment > input[name=parent]').val();
			const content = $('#formComment > textarea[name=content]').val();
			const writer = $('#formComment > input[name=writer]').val();
			
			//AJAX를 사용하여 댓글 데이터를 서버로 전송
			const jsonData = {
				"parent": parent,
				"content": content,
				"writer": writer
			};
			
			console.log('jsonData : ' + jsonData);
			
			//에이젝스 요청 (CommentController로 보냄)
			$.ajax({
				
				url: '/Farmstory2/board/comment.do',
				type: 'post',
				data: jsonData,
				dataType: 'json',
				success: function(data){
					
					alert('댓글이 작성되었습니다.');
					
					
					
					
				}
				
			});
			
		});
	});

</script>
<jsp:include page="./_aside${group}.jsp"/>
			<section class="view">
			    <h3>글보기</h3>
			    <table>
			        <tr>
			            <td>제목</td>
			            <td><input type="text" name="title" value="${article.title}" readonly/></td>
			        </tr>
			        <tr>
			            <td>첨부파일</td>
			            <td>
			                <a href="#">${article.file}</a>
			                <span>${article.hit}</span>회 다운로드
			            </td>
			        </tr>
			        <tr>
			            <td>내용</td>
			            <td>
			                <textarea name="content" readonly>${article.content}</textarea>
			            </td>
				        </tr>
			    </table>
			    <div>
			        <a href="#" class="btnDelete">삭제</a>
			        <a href="#" class="btnModify">수정</a>
			        <a href="#" class="btnList">목록</a>
			    </div>
			    
			    <!-- 댓글리스트 -->
			    <section class="commentList">
			        <h3>댓글목록</h3>
			        <article class="comment">
			        	<form action="/Farmstory2/board/view.do" method="post">
      				    	<c:forEach var="comment" items="${requestScope.comments}">
								<span>
									<span>${comment.writer}</span>
									<span>${comment.rdate}</span>
								</span>
								<textarea name="comment" readonly>댓글내용</textarea>
				             
								<div>
									<a href="#" class="del">삭제</a>
									<a href="./list.do?group=${group}&cate=${cate}" class="can">취소</a>
									<a href="./modify.do?group=${group}&cate=${cate}" class="mod">수정</a>
								</div>
					        </c:forEach>
			            </form>
			        </article>
			        <p class="empty">등록된 댓글이 없습니다.</p>
			    </section>
			
			    <!-- 댓글입력폼 -->
			    <section class="commentForm">
			        <form id="formComment" action="/Farmstory2/board/comment.do" method="post">
			        <h3>댓글쓰기</h3>
		            	<input type="hidden" name="parent" value="${no}"/>
    					<input type="hidden" name="writer" value="${sessUser.uid}"/>
			            <textarea name="content"></textarea>
			            <div>
			                <a href="#" class="btnCancel">취소</a>
			                <input type="submit" id="btnComment" class="btnWrite" value="작성완료"/>
			            </div>
		            </form>
			    </section>
			</section>
			<!-- 내용 끝 -->
        </article>
    </section>
</div>			
<%@ include file="../_footer.jsp" %>