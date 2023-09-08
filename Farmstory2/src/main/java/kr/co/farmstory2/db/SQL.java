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
	public static final String SELECT_COUNT_UID = "SELECT COUNT(*) FROM `User` WHERE `uid`=?";
	
	//닉네임 중복확인
	public static final String SELECT_COUNT_NICK = "SELECT COUNT(*) FROM `User` WHERE `nick`=?";
	
	//이메일 중복확인
	public static final String SELECT_COUNT_EMAIL = "SELECT COUNT(*) FROM `User` WHERE `email`=?";
	
	//휴대폰 중복확인
	public static final String SELECT_COUNT_HP = "SELECT COUNT(*) FROM `User` WHERE `hp`=?";

	//Article (글쓰기)
	public final static String INSERT_ARTICLE = "INSERT INTO `Article` SET "
												+ "`cate`=?, "
												+ "`title`=?, "
												+ "`content`=?, "
												+ "`file`=?, "
												+ "`writer`=?, "
												+ "`regip`=?, "
												+ "`rdate`=NOW()";
	
	//글 수정
	
	//글 삭제
	public final static String DELETE_ARTICLE = "DELETE FROM `Article` WHERE `no`=? OR `parent`=?";
	
	//파일 전송 관련 (no열 중 최대 값을 선택하는 쿼리)
	public final static String SELECT_MAX_NO = "SELECT MAX(`no`) FROM `Article`";

	//글 목록 조회 (Article)
	public static final String SELECT_ARTICLE = "SELECT * FROM `Article` WHERE `no`=?";
	
	public final static String SELECT_ARTICLES = "SELECT "
												+ "a.*, "
												+ "b.`nick` "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b ON a.writer = b.uid "
												+ "WHERE `parent`=0 AND `cate`=? "
												+ "ORDER BY `no` DESC "
												+ "LIMIT ?, 10";
	//댓글 조회
	public final static String SELECT_COMMENTS = "SELECT "
												+ "a.*, "
												+ "b.`nick` "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b ON a.writer = b.uid "
												+ "WHERE `parent`=?";
	
	//댓글 작성
	public final static String INSERT_COMMENT = "INSERT INTO `Article` SET "
												+ "`parent`=?, "
												+ "`content`=?,"
												+ "`writer`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";
	
	//댓글 삭제
	public final static String DELETE_COMMENT = "DELETE FROM `Article` WHERE `no`=?";
	
	//전체 게시물 갯수
	public final static String SELECT_COUNT_TOTAL = "SELECT COUNT(*) FROM `Article` WHERE `parent`=0 AND `cate`=?";
	
	
	//File 
	public static final String INSERT_FILE = "INSERT INTO `File` SET "
											+ "`fno`=?, "
											+ "`ano`=?, "
											+ "`oriName`=?, "
											+ "`newName`=?, "
											+ "`download`=?, "
											+ "`rdate`=NOW()";
	
	//Product
	//상품 등록(insert)
	public static final String INSERT_PRODUCT = "INSERT INTO `Product` SET "
											  + "`type`=?,"
											  + "`productName`=?,"
											  + "`price`=?,"
											  + "`delivery`=?,"
											  + "`stock`=?,"
											  + "`thumb120`=?,"
											  + "`thumb240`=?,"
											  + "`thumb750`=?,"
											  + "`seller`=?,"
											  + "`etc`=?,"
											  + "`rdate`=NOW()";
	
	public static final String SELECT_PRODUCT = "SELECT * FROM `Product` WHERE `pNo`=?";
	
	//상품 목록 구현(list)
	public static final String SELECT_PRODUCTS = "SELECT * FROM `Product` WHERE `stock` > 0";
	
	public static final String SELECT_PRODUCTS_ALL = "SELECT * FROM `Product` WHERE `stock` > 0 LIMIT ?, 10";
	
	public static final String SELECT_PRODUCTS_TYPE = "SELECT * FROM `Product` WHERE `stock` > 0 AND `type`=? LIMIT ?, 10";
	
	//
	public static final String SELECT_PRODUCT_COUNT_PRODUCTS_ALL = "SELECT COUNT(*) FROM `Product` WHERE `stock` > 0";
	
	public static final String SELECT_PRODUCT_COUNT_PRODUCTS_TYPE = "SELECT COUNT(*) FROM `Product` WHERE `stock` > 0 AND `type`=?";
	
	//Order
	//주문 등록
	public static final String INSERT_ORDER = "INSERT INTO `Order` SET "
											+ "`orderProduct`=?,"
											+ "`orderCount`=?,"
											+ "`orderDelivery`=?,"
											+ "`orderPrice`=?,"
											+ "`orderTotal`=?,"
											+ "`orderUser`=?,"
											+ "`hp`=?,"
											+ "`zip`=?,"
											+ "`addr1`=?,"
											+ "`addr2`=?,"
											+ "`orderEtc`=?";

}
