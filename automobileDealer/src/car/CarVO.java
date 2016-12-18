package car;

import java.io.Serializable;

public class CarVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//�Է�
	private String model; //����
	private String name; //���̸�
	private String year; //����
	private int km; //Ű�μ�
	private String imDate; //�԰�¥
	private int imPrice; //�԰�ݾ�
	private int prePrice; //�������
	
	//�ڵ����� Ȥ�� �ڵ�����
	private String serial; //�ø���ѹ�
	private String valid = "forSale"; //�ǸŰ��� ����
	
	//����
	private String exDate = "0000-00-00"; //���¥
	private int exPrice; //�������
	
	
	
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
		
		str = String.format("�� %12s ��%7s ��%10s ��%6s ��%8dkm ��%12s ��%12s ��%10d ��%11d ��%10d ��%12s!",
				serial,model,name,year,km,imDate,exDate,imPrice,prePrice,exPrice,valid);
		
		return str;		
	}
}
























