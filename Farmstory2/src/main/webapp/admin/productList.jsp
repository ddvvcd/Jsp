<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp"%>
        <main>
            <aside>
                <h3>주요기능</h3>
                <ul>
                    <li class="on"><a href="./productList.do">상품관리</a></li>
                    <li><a href="./orderList.do">주문관리</a></li>
                    <li><a href="./userList.do">회원관리</a></li>                    
                </ul>
            </aside>
            <section id="productList">
                <nav>
                    <h3>상품목록</h3>
                </nav>

                <article>

                    <table border="0">
                        <tr>
                            <th><input type="checkbox" name="all"/></th>
                            <th>사진</th>
                            <th>상품번호</th>
                            <th>상품명</th>
                            <th>구분</th>
                            <th>가격</th>
                            <th>재고</th>
                            <th>등록일</th>
                        </tr>
                        <c:forEach var="product" items="${requestScope.products}">
	                        <tr>
	                            <td><input type="checkbox" name=""/></td>
	                            <td><img src="/Farmstory2/upload/${product.thumb120}" class="thumb" alt="샘플1"></td>
	                            <td>${product.pNo}</td>
	                            <td>${product.productName}</td>
	                            <td>${product.type}</td>
	                            <td>${product.price}</td>
	                            <td>${product.stock}</td>
	                            <td>${product.rdate}</td>
	                        </tr>
	                	</c:forEach>   
                    </table>

                    <p>
                        <a href="#" class="productDelete">선택삭제</a>
                        <a href="./productRegister.do" class="productRegister">상품등록</a>
                    </p>
                    
                    <p class="paging">
                        <a href="#"><</a>
                        <a href="#" class="on">[1]</a>
                        <a href="#">[2]</a>
                        <a href="#">[3]</a>
                        <a href="#">[4]</a>
                        <a href="#">[5]</a>
                        <a href="#">></a>
                    </p>

                </article>

                
            </section>
        </main>
<%@ include file="./_footer.jsp" %>