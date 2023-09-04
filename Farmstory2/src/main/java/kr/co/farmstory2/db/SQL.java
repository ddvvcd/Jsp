package kr.co.farmstory2.db;

public class SQL {
	
	//Terms (약관 조회)
	public static final String SELECT_TERMS = "SELECT * FROM `Terms`";
	
	//User (회원가입)
	public static final String INSERT_USER = "INSERT INTO `User` SET "
											+ "`uid`=?,"
											+ "`pass`=SHA2(?, 256),"
											+ "`name`=?,"											
											+ "`nick`=?,"
											+ "`email`=?,"
											+ "`hp`=?,"
											+ "`zip`=?,"
											+ "`addr1`=?,"
											+ "`addr2`=?,"
											+ "`regip`=?,"
											+ "`regDate`=NOW()";
	
	//로그인
	public static final String SELECT_USER = "SELECT * FROM `User` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
	
	//아이디 중복확인
	public static final String SELECT_COUNT_UID = "SELECT * COUNT(*) FROM `User` WHERE `uid`=?";
	
	}
