package kr.co.jboard2.service;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.dao.UserDAO;
import kr.co.jboard2.dto.UserDTO;

public class UserService {
	
	//싱글톤으로 생성
	private static UserService instance = new UserService();
	public static UserService getInstance() {
		return instance;
	}
	private UserService() {}
	
	private static String generatedCode;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//UserDAO dao 호출
	private UserDAO dao = UserDAO.getInstance();
	
	public void insertUser(UserDTO dto) {
		dao.insertUser(dto);
	}
	
	public int selectCountUid(String uid) {
		return dao.selectCountUid(uid);
	}
	
	public int selectCountNick(String nick) {
		return dao.selectCountNick(nick);
	}
	
	public int selectCountHp(String hp) {
		return dao.selectCountHp(hp);
	}
	
	public int selectCountEmail(String email) {
		return dao.selectCountEmail(email);
	}
	
	public int selectCountNameAndEmail(String name, String email) {
		return dao.selectCountNameAndEmail(name, email);
	}
	
	public UserDTO selectUser(String uid, String pass) {
		return dao.selectUser(uid, pass);
	}
	
	public UserDTO selectUserByNameAndEmail(String name, String email) {
		return dao.selectUserByNameAndEmail(name, email);
	}
	
	public List<UserDTO> selectUsers() {
		return dao.selectUsers();
	}
	public void updateUser(UserDTO dto) {
		dao.updateUser(dto);
	}
	public void deleteUser(String uid) {
		dao.deleteUser(uid);
	}
	
	public int sendCodeByEmail(String receiver) {
		
		//인증코드 생성 (ThreadLocalRandom 클래스를 이용, 10만 ~ 100만사이의, 6자리 인증코드 생성)
		int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
		generatedCode = ""+code;
		
		//기본 정보
		String sender = "ekfkddjj85@gmail.com";
		String password = "pbgxdcomoloafudm"; //7장 proc -> sendEmail에 적혀있음/다시 발급받음
		String title = "Jboard2 인증코드입니다.";
		String content = "<h1>인증코드는 "+ code + " </h1>"; //인증코드를 이메일로 전송함
		
		// Gmail SMTP 서버 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		// Gmail STMP 세션 생성
		Session gmailSession = Session.getInstance(props, new Authenticator(){
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		});
		
		// 메일 발송
		int status = 0;
		Message message = new MimeMessage(gmailSession);
		
		try{
			logger.info("here1");
			message.setFrom(new InternetAddress(sender, "보내는 사람", "UTF-8"));
			logger.info("here2");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver)); //receiver는 매개변수 선언
			logger.info("here3");
			message.setSubject(title);
			message.setContent(content, "text/html;charset=UTF-8");
			Transport.send(message);
			logger.info("here4");
			status = 1;
			
		}catch(Exception e){
			status = 0;
			logger.info("sendCodeByEmail : " + e.getMessage());
		}
		
		return status; //생성한 코드를 리턴 -> AuthEmailController로 이동 
					   //code를 status로 수정 
	}//sendCodeByEmail end
	
	public int confirmCodeByEmail(String code) {
		
		if(code.equals(generatedCode)) {
			logger.info("return 1...");
			return 1;
		}else {
			logger.info("return 0...");
			return 0;
		}
	}
	
	
}
