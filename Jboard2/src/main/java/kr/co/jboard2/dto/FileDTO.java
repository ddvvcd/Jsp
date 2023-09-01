package kr.co.jboard2.dto;

public class FileDTO {
	
	//기존 필드
	private int fno;
	private int ano;
	private String ofile;
	private String sfile;
	private int download;
	private String rdate;
	
	//기존 필드 Getter, Setter
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getOfile() {
		return ofile;
	}
	public void setOfile(String ofile) {
		this.ofile = ofile;
	}
	public String getSfile() {
		return sfile;
	}
	public void setSfile(String sfile) {
		this.sfile = sfile;
	}
	public int getDownload() {
		return download;
	}
	public void setDownload(int download) {
		this.download = download;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	@Override //마우스 오른쪽 -> source -> generate toString -> 모두 선택 -> 생성
	public String toString() { //dto의 값을 출력하는 toString()
		return "FileDTO [fno=" + fno + ", ano=" + ano + ", ofile=" + ofile + ", sfile=" + sfile + ", download="
				+ download + ", rdate=" + rdate + "]";
	}
	
}
