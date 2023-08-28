/**
 * 회원가입 유효성 검사
 */
//폼 데이터 검증결과 상태변수
   		let isUidOk   = false;
   		let isPassOk  = false;
   		let isNameOk  = false;
   		let isNickOk  = false;
   		let isEmailOk = false;
   		let isHpOk    = false;
   		
   		//데이터 검증에 사용하는 정규표현식
   		let reUid   = /^[a-z]+[a-z0-9]{4,19}$/g;
   		let rePass  = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{5,16}$/;
   		let reName  = /^[가-힣]{2,10}$/ 
   		let reNick  = /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
   		let reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
   		let reHp    = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;
   		
   		//유효성 검증(Validation)
   		$(function(){
   			
   			//아이디 검사 (회원가입 후 isUidOk = true가 되므로 다시 설정/취약점 대응)
   			$('input[name=uid]').keydown(function(){
   				$('.resultId').text('');
   				isUidOk = false;
   			});
   			
   			
		    //비밀번호 검사
		    $('input[name=pass2]').focusout(function(){
		    												//비번 입력했을 경우
		    	const pass1 = $('input[name=pass1]').val(); //1번째 비번 입력값 가져옴
		    	const pass2 = $('input[name=pass2]').val(); //2번째 비번 입력값 가져옴
 				
		    	if(pass1 == pass2){
		    		
		    		isPassOk = true;
					
		    		if(pass1.match(rePass)){ //1번째 비번이 정규표현식과 부합하는지 검사
			    		$('.resultPass').css('color', 'green').text('사용할 수 있는 비밀번호 입니다.');
			    		isPassOk = true;
		    		}else{
			    		$('.resultPass').css('color', 'red').text('비밀번호는 숫자, 영문, 특수문자 조합 5자리 이상이어야 합니다.');
						isPassOk = false;
		    		}
		    		
		    	}else{
		    		$('.resultPass').css('color', 'red').text('비밀번호가 일치하지 않습니다.');
		    		isPassOk = false;
		    	}
		    	
		    });
		    
   			//이름 검사
   			$('input[name=name]').focusout(function(){
   				
   				const name = $(this).val();
   				
   				if(name.match(reName)){ //이름이 정규표현식과 부합하지 않는다면
   					$('.resultName').text('');
   					isNameOk = true; //아래쪽 폼 전송 취소 실행
   				}else{
   					$('.resultName').css('color', 'red').text('유효한 이름이 아닙니다.');
   					isNameOk = false; //폼 전송 시작
   				}
   				
   			});
   			
   			//별명 검사(checkUser.js에 작성)
   			
   			//이메일 검사(checkUser.js에 작성)

   			//휴대폰 검사
			$('input[name=hp]').focusout(function(){
   				
   				const hp = $(this).val();
   				
   				if(email.match(reHp)){
   					$('.resultHp').text('');
   					isHpOk = true;
   				}else{
   					$('.resultHp').css('color', 'red').text('유효한 휴대폰번호가 아닙니다.');
   					isHpOk = false;
   				}
   			});
   			
   			//최종 전송
   			$('#formUser').submit(function(){
				
   				if(!isUidOk){
   					alert('아이디를 확인 하십시오.');
   					return false; //폼 전송 취소
   				}
   				
   				if(!isPassOk){
   					alert('비밀번호를 확인 하십시오.');
   					return false; //폼 전송 취소
   				}
   				
   				if(!isNameOk){
   					alert('이름을 확인 하십시오.');
   					return false; //폼 전송 취소
   				}
   				
   				if(!isNickOk){
   					alert('닉네임을 확인 하십시오.');
   					return false; //폼 전송 취소
   				}
   				
   				if(!isEmailOk){
   					alert('이메일을 확인 하십시오.');
   					return false; //폼 전송 취소
   				}
   				
   				if(!isHpOk){
   					alert('휴대폰 번호를 확인하십시오.');
   					return false; //폼 전송 취소
   				}
   				
   				return true; //폼 전송 시작
   				
   			}); //최종 전송 끝
   			
   		}); //유효성 검증 끝
   		