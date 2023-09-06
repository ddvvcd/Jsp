<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>



			<section class="write">
			    <h3>글쓰기</h3>
			    <article>
			        <form action="/Farmstory2/board/write.do" method="post" enctype="multipart/form-data">
			        <input type="hidden" name="writer" value="${sessUser.uid}"/>
			        <input type="hidden" name="group" value="${group}"/>
			        <input type="hidden" name="cate" value="${cate}"/>
			            <table border="0">
			                <tr>
			                    <td>제목</td>
			                    <td><input type="text" name="title" required placeholder="제목을 입력하세요."/></td>
			                </tr>
			                <tr>
			                    <td>내용</td>
			                    <td>
			                        <textarea name="content" required></textarea>                                
			                    </td>
			                </tr>
			                <tr>
			                    <td>첨부</td><!-- multiple 속성 : 파일업로드 시 파일 여러개를 넣을 수 있음 -->
			                    <td><input type="file" name="file"/></td>
			                </tr>
			            </table>
			            <div>
			                <a href="./board/list.do" class="btnCancel">취소</a>
			                <input type="submit"  class="btnWrite" value="작성완료">
			            </div>
			        </form>
			    </article>
			</section>
			<!-- 내용 끝 -->
        </article>
    </section>
</div>			
<%@ include file="../_footer.jsp" %>
