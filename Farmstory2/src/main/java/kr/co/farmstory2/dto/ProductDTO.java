package kr.co.farmstory2.dto;

import java.io.File;
import java.util.UUID;

public class ProductDTO {
	
	private int pNo;
	private int type;
	private String productName;
	private int price;
	private int delivery;
	private int stock;
	private int sold;
	private String thumb120;
	private String thumb240;
	private String thumb750;
	private String seller;
	private String etc;
	private String rdate;
	//추가 (파일이름 재정의)
	private String path;
	
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public void setpNo(String pNo) {
		this.pNo = Integer.parseInt(pNo);
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setType(String type) {
		this.type = Integer.parseInt(type);
	}

	public String getProductName() {
	    return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setPrice(String price) {
		this.price = Integer.parseInt(price);
	}
	public int getDelivery() {
		return delivery;
	}
	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = Integer.parseInt(delivery);
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public void setStock(String stock) {
		this.stock = Integer.parseInt(stock);
	}
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	public String getThumb120() {
		return thumb120;
	}
	public void setThumb120(String thumb120) {
		this.thumb120 = thumb120;
	}
	public String getThumb240() {
		return thumb240;
	}
	public void setThumb240(String thumb240) {
		this.thumb240 = thumb240;
	}
	public String getThumb750() {
		return thumb750;
	}
	public void setThumb750(String thumb750) {
		this.thumb750 = thumb750;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	
	//파일이름 재정의
	public String fileRename(String thumb) {
		int i = thumb.lastIndexOf(".");
		String ext = thumb.substring(i);
		
		String uuid = UUID.randomUUID().toString();
		String sName = uuid + ext;
		
		File f1 = new File(path + "/" + thumb);
		File f2 = new File(path + "/" + sName);
		f1.renameTo(f2);
		
		return sName;
	}
	
	@Override
	public String toString() {
		return "ProductDTO [pNo=" + pNo + ", type=" + type + ", productName=" + productName + ", price=" + price
				+ ", delivery=" + delivery + ", stock=" + stock + ", sold=" + sold + ", thumb120=" + thumb120
				+ ", thumb240=" + thumb240 + ", thumb750=" + thumb750 + ", seller=" + seller + ", etc=" + etc
				+ ", rdate=" + rdate + "]";
	}
}
