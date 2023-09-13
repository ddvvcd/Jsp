package kr.co.kmarket.db;

public class SQL {
	
	//Terms
	public static final String SELECT_TERMS = "SELECT * FROM `Terms`";
	
	//Member
	//회원가입
	public static final String INSERT_MEMBER = "INSERT INTO `Member` SET "
											 + "uid=?,"
											 + "pass=SHA2(?, 256),"
											 + "name=?,"
											 + "gender=?,"
											 + "hp=?,"
											 + "email=?,"
											 + "zip=?,"
											 + "addr1=?,"
											 + "addr2=?,"
											 + "regip=?,"
											 + "`rdate`=NOW()";

	public static final String SELECT_MEMBER = "SELECT * FROM `Member` `uid`=? AND `pass`=SHA2(?, 256)";

	
}
