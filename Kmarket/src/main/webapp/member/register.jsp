<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script>

$('#km_uid').click(function(){
	
	const uid = $('input[name=km_uid]').val();
	
	$.ajax({
		url:'/Kmarket/member/checkUid.do?uid='+uid,
		type:'get',
		dataType:'json',
		success: function(data){
			
			if(data.result > 0){
				$('.msgId').css('color', 'red').text('이미 사용중인 별명입니다.');
			}else{
				$('.msgId').css('color', 'green').text('사용 가능한 별명입니다.');
			}
			
		}
	});
	
	
});//btnCheckNick end
</script>
<main id="member">
  <div class="register">
    <nav>
      <h1>일반 회원가입</h1>
    </nav>
    <form id="formMember" action="/Kmarket/member/register.do" method="POST">
    <input type="hidden" name="type" value="1"/>
      <section>
        <table>
          <caption>필수 정보입력</caption>
          <tbody>
            <tr>
              <th><span class="essential">*</span>아이디</th>
              <td>
                <input type="text" name="km_uid" id="km_uid"placeholder="아이디를 입력" required />
                <span class="msgId" id="msgId">영문, 숫자로 4~12자까지 설정해 주세요.</span>
              </td>
            </tr>
            <tr>
              <th><span class="essential">*</span>비밀번호</th>
              <td>
                <input type="password" name="km_pass1" placeholder="비밀번호를 입력" required />
                <span class="msgId">영문, 숫자, 특수문자를 조합하여 8~12자까지 설정해
                  주세요.</span>
              </td>
            </tr>
            <tr>
              <th><span class="essential">*</span>비밀번호확인</th>
              <td>
                <input type="password" name="km_pass2" placeholder="비밀번호를 입력" required />
                <span class="msgId">비밀번호 재입력</span>
              </td>
            </tr>
          </tbody>
        </table>
      </section>
      <section>
        <table>
          <caption>
            기본 정보입력
          </caption>
          <tbody>
            <tr>
              <th><span class="essential">*</span>이름</th>
              <td>
                <input type="text" name="km_name" placeholder="이름을 입력" required />
              </td>
            </tr>
            <tr>
              <th><span class="essential">*</span>성별</th>
              <td>
                <label>
                  <input type="radio" name="km_gender" value="1" />&nbsp;남
                </label>
                <label>
                  <input type="radio" name="km_gender" value="2" checked/>&nbsp;여
                </label>
              </td>
            </tr>
            <tr>
              <th><span class="essential">*</span>EMAIL</th>
              <td>
                <input type="email" name="km_email" placeholder="이메일 입력" required />
              </td>
            </tr>
            <tr>
              <th><span class="essential">*</span>휴대폰</th>
              <td>
                <input type="text" name="km_hp" maxlength="13" placeholder="휴대폰번호 입력" required />
                <span class="msgHp"> - 포함 13자리를 입력하세요.</span>
              </td>
            </tr>
            <tr class="addr">
              <th>주소</th>
              <td>
                <div>
                  <input type="text" name="km_zip" id="zip" placeholder="우편번호 입력 클릭"/>
                </div>
                <div>
                  <input type="text" name="km_addr1" id="addr1" size="50" placeholder="주소를 검색하세요."/>
                </div>
                <div>
                  <input type="text" name="km_addr2" id="addr2" size="50" placeholder="상세주소를 입력하세요."/>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </section>
      <div>
        <input type="submit" class="join" value="회원가입" />
      </div>
    </form>
  </div>
</main>
<%@ include file="./_footer.jsp" %>