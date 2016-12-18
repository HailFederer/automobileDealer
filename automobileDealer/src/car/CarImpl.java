package car;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class CarImpl implements Car, Runnable{
	
	List<CarVO> li = new ArrayList<CarVO>();
	List<CarVO> li2 = new ArrayList<CarVO>();
	List<CarVO> li3 = new ArrayList<CarVO>();
	
	CarException carEx = new CarException();
	
	Scanner sc = new Scanner(System.in);
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	Calendar now = Calendar.getInstance();
	
	String today = "";
	String yest = "";
	int cnt = 0;
	
	int term = 0;
	
	FileInputStream fis;
	ObjectInputStream ois;
	
	FileOutputStream fos;
	ObjectOutputStream oos;
	
	public void setDC(){
		
		try {
			
			today = "";
			today += Integer.toString(now.get(Calendar.YEAR));
			today += Integer.toString(now.get(Calendar.MONTH)+1);
			today += Integer.toString(now.get(Calendar.DATE));
			
			fis = new FileInputStream("d:\\carDB\\yest.txt");
			ois = new ObjectInputStream(fis);
			yest = (String)ois.readObject();
			
			fis = new FileInputStream("d:\\carDB\\cnt.txt");
			ois = new ObjectInputStream(fis);
			cnt = (int)ois.readObject();
			
			ois.close();
			fis.close();
			
			if(!(today.equals(yest)))
				cnt = 0;
			
		} catch (Exception e) {
			System.out.print("�� ");
			System.out.print(e.toString());
		}
	}

	@Override
	public void inputDB() {
		
		try {
			
			fis = new FileInputStream("d:\\carDB\\carDB.txt");
			ois = new ObjectInputStream(fis);
			@SuppressWarnings("unchecked")
			List<CarVO> li2 = (List<CarVO>)ois.readObject();
			li.addAll(li2);
			
			fis.close();
			ois.close();
		
		} catch (Exception e) {
			System.out.print("�� ");
			System.out.print(e.toString());
		}
	}

	@Override
	public void input() {
		
		CarVO car = new CarVO();
		
		Boolean flag = true;
		
		do{
			System.out.print("�� ����(����� ���ڸ� ����!) : ");
			car.setModel(sc.next());
			
			try {
				flag = carEx.modelEx(car.getModel(), li);
			} catch (Exception e) {
				flag = false;
				System.out.print(e.toString());
			}
		}while(flag == false);
		
		do{
			System.out.print("�� ���̸�(����� ���ڸ� ����!) : ");
			car.setName(sc.next());
			
			try {
				flag = carEx.nameEx(car.getName(), li);
			} catch (Exception e) {
				flag = false;
				System.out.print(e.toString());
			}
		}while(flag == false);
		
		int y;
		String x;
		do{
			System.out.print("�� ����(1~9999) : ");
			x = sc.next();
			car.setYear(x);
			y = Integer.parseInt(x);
		}while(y<1 || y>9999);
		
		do{
			System.out.print("�� Ű�μ�(km)(0�̻�) : ");
			y = sc.nextInt();
			car.setKm(y);
		}while(y<0);
		
		String imDate = "";
		do{
			System.out.print("�� �԰� ����(1~9999) : ");
			x = sc.next();
			imDate = x;
			y = Integer.parseInt(x);
		}while(y<1 || y>9999);
		
		do{
			System.out.print("�� �԰� ��(1~12) : ");
			x = sc.next();
			imDate += ( "-" + x );
			y = Integer.parseInt(x);
		}while(y<1 || y>12);
		
		do{
			System.out.print("�� �԰� ��(1~31) : ");
			x = sc.next();
			imDate += ( "-" + x );
			y = Integer.parseInt(x);
		}while(y<1 || y>31);
		
		car.setImDate(imDate);
		
		do{
			System.out.print("�� �԰� �ݾ�(����)(0�̻�) : ");
			y = sc.nextInt();
			car.setImPrice(y);
		}while(y<0);
		
		do{
			System.out.print("�� �������(����)(0�̻�) : ");
			y = sc.nextInt();
			car.setPrePrice(y);
		}while(y<0);
		
		car.setSerial(today+Integer.toString(cnt));
		
		cnt++;
		
		li.add(car);
	}

	@Override
	public boolean searchSerial(String serial) {
		
		Boolean flag = false;
		
		Iterator<CarVO> it = li.iterator();
		
		while(it.hasNext()){
			
			CarVO vo = it.next();
			
			if( (vo.getSerial()).equals(serial) ){
				flag = true;
				break;
			}
		}
		return flag;
	}

	@Override
	public void print() {
		
		Iterator<CarVO> it = li.iterator();
		
		System.out.println("������������������������������������������������������������������������������������������������������������������������������");
		
		System.out.printf("��%6s ��%5s ��%7s ��%4s ��%6s ��%8s ��%8s ��%6s ��%6s ��%6s ��%3s",
				"Serial Number","��  ��","�� �̸�","��  ��","����Ÿ�","�԰�¥","���¥",
				"�԰���","�������","�����","�ǸŰ��� ����");
		System.out.println();	
		
		while(it.hasNext()){
			
			CarVO car = it.next();
			
			System.out.println(car.toString());
		}
	}

	@Override
	public void delete() {
		
		Iterator<CarVO> it = li.iterator();
		System.out.print("�� ������ �ø���ѹ� : ");
		String srl = sc.next();

		if (searchSerial(srl)) {

			int cnt = 0;

			while (it.hasNext()) {

				CarVO ca = it.next();

				if (ca.getSerial().equals(srl)) {

					li.remove(cnt);
					break;
				}
				cnt++;
			}
			System.out.println("�� ��������!");
		} else{
			System.out.println("�� ������ �ѹ��� �������� �ʽ��ϴ�!");
		}
		System.out.println();
	}

	@Override
	public void update() {
		
		System.out.print("����������������������������������������������������������������������������������������������\n"
				   + "��                           �Ǧ����������������Ǧ���������������                           ��\n"
				   + "��                           ��  1.�ܼ�����  ����   2.�� ��    ��                           ��\n"
				   + "��                           �Ŧ��������������æŦ���������������                           ��\n"
				   + "����������������������������������������������������������������������������������������������\n");
		System.out.print("�� �Է� : ");
		int num = sc.nextInt();
		
		if(num == 2){
			
			System.out.print("�� �Ǹ��� ������ �ø���ѹ� :");
			String sellSeri = sc.next();
			
			Iterator<CarVO> it = li.iterator();
			
			if (searchSerial(sellSeri)){
				
				while (it.hasNext()) {
					
					CarVO vo = it.next();
					
					if (vo.getSerial().equals(sellSeri)) {
		
						System.out.print("�� ��� ���� : ");
						String exDate = sc.next();
		
						System.out.print("�� ��� �� : ");
						exDate += ("-" + sc.next());
		
						System.out.print("�� ��� �� : ");
						exDate += ("-" + sc.next());
		
						vo.setExDate(exDate);
		
						System.out.print("�� �������(����) : ");
						vo.setExPrice(sc.nextInt());
						
						vo.setValid("Sold");
		
						System.out.println("�� �ǸſϷ�!");
					}	
				}
			}else{
				System.out.println("�� ������ �ѹ��� �������� �ʽ��ϴ�!");
			}
			
		}else if(num == 1){
		
			System.out.print("�� ������ ������ �ø���ѹ� :");
			String modSeri = sc.next();
			
			Iterator<CarVO> it = li.iterator();
	
			if (searchSerial(modSeri)){
				
				while (it.hasNext()) {
					
					CarVO vo = it.next();
					
					if (vo.getSerial().equals(modSeri)) {
		
						System.out.print("�� ����(��� ����) : ");
						vo.setModel(sc.next());
		
						/*
						 * try { flag = carEx.idEx(car.getModel(), li); } catch
						 * (Exception e) { flag = false;
						 * System.out.println(e.toString()); } }while(flag == false);
						 */
		
						System.out.print("�� ���̸�(��� ����) : ");
						vo.setName(sc.next());
		
						System.out.print("�� ���� : ");
						vo.setYear(sc.next());
		
						System.out.print("�� Ű�μ�(km) : ");
						vo.setKm(sc.nextInt());
		
						System.out.print("�� �԰� ���� : ");
						String imDate = sc.next();
		
						System.out.print("�� �԰� �� : ");
						imDate += ("-" + sc.next());
		
						System.out.print("�� �԰� �� : ");
						imDate += ("-" + sc.next());
		
						vo.setImDate(imDate);
		
						System.out.print("�� �԰� �ݾ�(����) : ");
						vo.setImPrice(sc.nextInt());
		
						System.out.print("�� �������(����) : ");
						vo.setPrePrice(sc.nextInt());
		
						System.out.println("�� ����Ϸ�!");
					}	
				}
			}else{
				System.out.println("�� ������ �ѹ��� �������� �ʽ��ϴ�!");
			}
		}
	}
	
	@Override
	public void findComplex() {
		
		System.out.print("����������������������������������������������������������������������������������������������\n"
				   + "��         �Ǧ����������������Ǧ����������������Ǧ����������������Ǧ���������������         ��\n"
				   + "��         ��  1.�����˻�  ���� 2.���̸��˻� ���� 3.���ݴ�˻� ����4.�Ǹſ��ΰ˻���         ��\n"
				   + "��         �Ŧ��������������æŦ��������������æŦ��������������æŦ���������������         ��\n"
				   + "��          ex) �޸�(,) �� �����Ͽ� �˻��׸� ���� ��) 1,3,4                                 ��\n"
				   + "����������������������������������������������������������������������������������������������\n");
		System.out.print("�� �Է� : ");
		
		String searchComplex = "";
		
		try {
			searchComplex = br.readLine();
		} catch (IOException e) {
			System.out.print("�� ");
			e.toString();
		}
		
		searchComplex = searchComplex.replaceAll("\\s", "");
		
		String[] item = searchComplex.split(",");
		
		li2.addAll(li);
		
		for(String index : item){
			
			switch(Integer.parseInt(index)){
			
			case 1:
				
				System.out.print("�� �˻��� ���� : ");
				String searchModel = sc.next();
				System.out.println();
					
				for(int i=0; i<li2.size(); i++){
						
					if( !(li2.get(i).getModel().equals(searchModel)) ){
						li2.remove(i);
						i--;
					}
				}
				break;
			
			case 2: 
				
				//it = li.iterator();
				
				System.out.print("�� �˻��� ���̸�(��� ����) : ");
				String searchName = sc.next();
				System.out.println();
				
				for(int i=0; i<li2.size(); i++){
					
					if( !(li2.get(i).getName().equals(searchName)) ){
						li2.remove(i);
						i--;
					}
				}
				break;
				
			case 3: 
				
				System.out.print("�� �˻��� ���ݴ�(����) <��: 3500 ~ 5000> : ");
				String searchprice = "";
				
				try {
					searchprice = br.readLine();
				} catch (IOException e) {
					System.out.print("�� ");
					e.toString();
				}
				
				searchprice = searchprice.replaceAll("\\s", "");
				
				String[] price = searchprice.split("~");
				
				for(int i=0; i<li2.size(); i++){
					
					if( !( (li2.get(i).getPrePrice() >= Integer.parseInt(price[0])) 
						&& (li2.get(i).getPrePrice() <= Integer.parseInt(price[1])) ) ){
						
						li2.remove(i);
						i--;
					}
				}
				break;
				
			case 4: 
				
				System.out.print("�� forSale(1) or Sold(2) : ");
				int valid = sc.nextInt();
				
				String validStr = "";
			
				if(valid == 1)
					validStr = "forSale";
				else if(valid == 2)
					validStr = "Sold";
				
				for(int i=0; i<li2.size(); i++){
					
					if( !( li2.get(i).getValid().equals(validStr) ) ) {
						
						li2.remove(i);
						i--;
					}
				}
				break;
			}
		}
		
		System.out.println("������������������������������������������������������������������������������������������������������������������������������");
		
		System.out.printf("��%6s %5s %7s %4s %6s %8s %8s %6s %6s %6s %3s",
						  "Serial Number","����","���̸�","����","����Ÿ�","�԰�¥","���¥",
						  "�԰���","�������","�����","�ǸŰ��� ����");
		System.out.println();
		
		for(int i=0; i<li2.size(); i++){
			
			System.out.println(li2.get(i).toString());
		}
	}

	@Override
	public void save() {
		
		try {
			
			yest = "";
			
			yest += Integer.toString(now.get(Calendar.YEAR));
			yest += Integer.toString(now.get(Calendar.MONTH)+1);
			yest += Integer.toString(now.get(Calendar.DATE));
			
			fos = new FileOutputStream("d:\\carDB\\carDB.txt");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(li);
			
			fos = new FileOutputStream("d:\\carDB\\yest.txt");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(yest);
			
			fos = new FileOutputStream("d:\\carDB\\cnt.txt");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(cnt);
			
			oos.close();
			fos.close();
			
			System.out.println("�� ���� ����!");
			
		} catch (Exception e) {
			System.out.print("�� ");
			System.out.print(e.toString());
		}
	}

	@Override
	public void run() {
		
		while(true){
		
			try {
				
				term = 86400 - (((now.get(Calendar.HOUR_OF_DAY))*60 + now.get(Calendar.MINUTE))*60 + now.get(Calendar.SECOND));
				
				Thread.sleep(term*1000);
				
				cnt = 0;
				
			} catch (Exception e) {
				System.out.print("�� ");
				System.out.print(e.toString());
			}
		}
	}
}























