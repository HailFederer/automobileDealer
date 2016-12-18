package car;

import java.io.Serializable;

public class CarVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//입력
	private String model; //차종
	private String name; //차이름
	private String year; //연식
	private int km; //키로수
	private String imDate; //입고날짜
	private int imPrice; //입고금액
	private int prePrice; //예상출고가
	
	//자동생성 혹은 자동수정
	private String serial; //시리얼넘버
	private String valid = "forSale"; //판매가능 유무
	
	//수정
	private String exDate = "0000-00-00"; //출고날짜
	private int exPrice; //실제출고가
	
	
	
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getKm() {
		return km;
	}
	public void setKm(int km) {
		this.km = km;
	}
	public String getImDate() {
		return imDate;
	}
	public void setImDate(String imDate) {
		this.imDate = imDate;
	}
	public String getExDate() {
		return exDate;
	}
	public void setExDate(String exDate) {
		this.exDate = exDate;
	}
	public int getImPrice() {
		return imPrice;
	}
	public void setImPrice(int imPrice) {
		this.imPrice = imPrice;
	}
	public int getExPrice() {
		return exPrice;
	}
	public void setExPrice(int exPrice) {
		this.exPrice = exPrice;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public int getPrePrice() {
		return prePrice;
	}
	public void setPrePrice(int prePrice) {
		this.prePrice = prePrice;
	}

	@Override
	public String toString() {
		String str = "";
		
		str = String.format("┣ %12s ┃%7s ┃%10s ┃%6s ┃%8dkm ┃%12s ┃%12s ┃%10d ┃%11d ┃%10d ┃%12s!",
				serial,model,name,year,km,imDate,exDate,imPrice,prePrice,exPrice,valid);
		
		return str;		
	}
}
























