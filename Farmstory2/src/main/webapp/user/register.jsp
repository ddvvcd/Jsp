<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<script>
	//아이디 중복체크
	const inputUid = document.getElementByName('uid')[0];
	const uidResult = document.getElementByClassName('uidResult')[0];
	const btnCheckUid = document.getElementById('btnCheckUid');
	
	if(btnCheckUid != null){
		
		btnCheckUid.onclick = function(){
			
			const uid = inputUid.value;
			
			//아이디 유효성 검사
			if(!uid.match(reUid)){
				uidResult.innerText = '유효한 아이디가 아닙니다.';
				uidResult.style.color = 'red';
				isUidOk = false;
				return;
			}
			
			//서버 전송
			const xhr = new XMLHttpResuext();
			xhr.open('GET', '/Farmstory2/user/checkUid.do?uid='+uid);
			xhr.send();
			
		}
	}

</script>
<div id="user">
	<section class="register">
        <form id="formUser" action="/Farmstory2/user/register.do" method="post">
            <table border="1">
                <caption>사이트 이용정보 입력</caption>
                <tr>
                    <td>아이디</td>
                    <td>
                        <input type="text" name="uid" placeholder="아이디 입력"/>
                        <button type="button" id="btnCheckUid"><img src="./images/chk_id.gif" alt=""></button>
                        <span class="resultId"></span>
                    </td>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td>
                        <input type="password" name="pass1" placeholder="비밀번호 입력"/>                            
                    </td>
                </tr>
                <tr>
                    <td>비밀번호 확인</td>
                    <td>
                        <input type="password" name="pass2" placeholder="비밀번호 확인 입력"/>
                        <span class="resultPass"></span>
                    </td>
                </tr>
            </table>
            <table border="1">
                <caption>개인정보 입력</caption>
                <tr>
                    <td>이름</td>
                    <td>
                        <input type="text" name="name" placeholder="이름 입력"/>
                        <span class="resultName"></span>
                    </td>
                </tr>
                <tr>
                    <td>별명</td>
                    <td>
                        <p>공백없이 한글, 영문, 숫자만 입력가능</p>
                        <input type="text" name="nick" placeholder="별명 입력"/>
                        <span class="resultNick"></span>                            
                    </td>
                </tr>
                <tr>
                    <td>E-Mail</td>
                    <td>
                        <input type="email" name="email" placeholder="이메일 입력"/>
                        <span id="resultEmail"></span>
                    </td>
                </tr>
                <tr>
                    <td>휴대폰</td>
                    <td>
                        <input type="text" name="hp" placeholder="- 포함 13자리 입력" minlength="13" maxlength="13" />
                        <span id="resultHp"></span>
                    </td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td>
                        <div>
                            <input type="text" name="zip" placeholder="우편번호" readonly/>                                
                            <button type="button" class="btnZip" onclick="zipcode()"><img src="./images/chk_post.gif" alt=""></button>
                        </div>                            
                        <div>
                            <input type="text" name="addr1" placeholder="주소를 검색하세요." readonly/>
                        </div>
                        <div>
                            <input type="text" name="addr2" placeholder="상세주소를 입력하세요."/>
                        </div>
                    </td>
                </tr>
            </table>

            <div>
                <a href="./login.do" class="btnCancel">취소</a>
                <input type="submit" class="btnSubmit" value="회원가입"/>
            </div>    
        </form>
    </section>
</div>
<%@ include file="../_footer.jsp" %>