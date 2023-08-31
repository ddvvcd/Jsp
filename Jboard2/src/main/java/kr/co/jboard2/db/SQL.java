package kr.co.jboard2.db;

public class SQL {
	// User
	public static final String INSERT_USER = "INSERT INTO `user` SET "
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
	
	public static final String SELECT_USER        = "SELECT * FROM `User` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
	//아이디 찾기 이메일 인증
	public static final String SELECT_USER_BY_NAME_AND_EMAIL = "SELECT * FROM `user` WHERE `name`=? AND `email`=?";
	public static final String SELECT_COUNT_UID   = "SELECT COUNT(*) FROM `user` WHERE `uid`=?";
	public static final String SELECT_COUNT_NICK  = "SELECT COUNT(*) FROM `user` WHERE `nick`=?";
	public static final String SELECT_COUNT_EMAIL = "SELECT COUNT(*) FROM `user` WHERE `email`=?";
	public static final String SELECT_COUNT_NAME_EMAIL = "SELECT COUNT(*) FROM `user` WHERE `name`=? AND `email`=?";
	public static final String SELECT_COUNT_UID_EMAIL = "SELECT COUNT(*) FROM `user` WHERE `uid`=? AND `email`=?";
	
	public static final String SELECT_COUNT_HP    = "SELECT COUNT(*) FROM `user` WHERE `hp`=?";
	public static final String SELECT_TERMS       = "SELECT * FROM `terms`";
	
	//회원정보 수정
	public static final String UPDATE_USER = "UPDATE `user` SET "
											+ "`name`=?,"
											+ "`nick`=?,"
											+ "`email`=?,"
											+ "`hp`=?,"
											+ "`zip`=?,"
											+ "`addr1`=?,"
											+ "`addr2`=? "
											+ " WHERE `uid`=?";
	
	//비밀번호 변경
	public static final String UPDATE_USER_PASS   = "UPDATE `user` SET `pass`=SHA2(?, 256) WHERE `uid`=?";
	
	//회원탈퇴
	public static final String UPDATE_USER_FOR_WITHDRAW = "UPDATE `user` SET "
														+ "`pass`=null,"
														+ "`name`=null,"
														+ "`nick`=null,"
														+ "`email`=null,"
														+ "`hp`=null,"
														+ "`role`=null,"
														+ "`zip`=null,"
														+ "`addr1`=null,"
														+ "`addr2`=null,"
														+ "`leaveDate`=NOW() "
														+ " WHERE `uid`=?";
	
	// Article
	//게시판 글 작성
	public final static String INSERT_ARTICLE = "INSERT INTO `Article` SET "
												+ "`title`=?, "
												+ "`content`=?,"
												+ "`file`=?,"
												+ "`writer`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";
	
	public final static String INSERT_COMMENT = "INSERT INTO `Article` SET "
												+ "`parent`=?, "
												+ "`content`=?,"
												+ "`writer`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";
	
	//파일 전송 관련
	public final static String SELECT_MAX_NO = "SELECT MAX(`no`) FROM `Article`";

	//게시판 글 목록
	public final static String SELECT_ARTICLE = "SELECT * FROM `Article` WHERE `no`=?";
	
	//게시판 글 쓰기
	public final static String SELECT_ARTICLES = "SELECT "
												+ "a.*, "
												+ "b.`nick` "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b ON a.writer = b.uid "
												+ "WHERE `parent`=0 "
												+ "ORDER BY `no` DESC "
												+ "LIMIT ?, 10";
			
	public final static String SELECT_COMMENTS = "SELECT "
												+ "a.*, "
												+ "b.`nick` "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b ON a.writer = b.uid "
												+ "WHERE `parent`=?";
	
	public final static String SELECT_COUNT_TOTAL = "SELECT COUNT(*) FROM `Article` WHERE `parent`=0";
	
	//댓글 쓰기
	public final static String UPDATE_ARTICLE = "UPDATE `article` SET `title`=?, `content`=? WHERE `no`=?";
	public final static String UPDATE_ARTICLE_FOR_COMMENT_PLUS = "UPDATE `article` SET `comment` = `comment` + 1 WHERE `no`=?";
	public final static String UPDATE_ARTICLE_FOR_COMMENT_MINUS = "UPDATE `article` SET `comment` = `comment` - 1 WHERE `no`=?";
	
	//댓글 수정
	public final static String UPDATE_COMMENT = "UPDATE `Article` SET `content`=? WHERE `no`=?";
	
	//댓글 삭제
	public final static String DELETE_COMMENT = "DELETE FROM `article` WHERE `no`=?";
	
	//게시판 글 삭제
	public final static String DELETE_ARTICLE = "DELETE FROM `article` WHERE `no`=? OR `parent`=?";
	
	//File
	//파일 테이블 Insert
	public final static String INSERT_FILE = "INSERT INTO `file` SET "
											+ "`ano`=?,"
											+ "`ofile`=?,"
											+ "`sfile`=?,"
											+ "`rdate`=NOW()";
	
}
