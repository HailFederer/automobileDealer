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
			System.out.print("┣ ");
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
			System.out.print("┣ ");
			System.out.print(e.toString());
		}
	}

	@Override
	public void input() {
		
		CarVO car = new CarVO();
		
		Boolean flag = true;
		
		do{
			System.out.print("┣ 차종(영어와 숫자만 가능!) : ");
			car.setModel(sc.next());
			
			try {
				flag = carEx.modelEx(car.getModel(), li);
			} catch (Exception e) {
				flag = false;
				System.out.print(e.toString());
			}
		}while(flag == false);
		
		do{
			System.out.print("┣ 차이름(영어와 숫자만 가능!) : ");
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
			System.out.print("┣ 연식(1~9999) : ");
			x = sc.next();
			car.setYear(x);
			y = Integer.parseInt(x);
		}while(y<1 || y>9999);
		
		do{
			System.out.print("┣ 키로수(km)(0이상) : ");
			y = sc.nextInt();
			car.setKm(y);
		}while(y<0);
		
		String imDate = "";
		do{
			System.out.print("┣ 입고 연도(1~9999) : ");
			x = sc.next();
			imDate = x;
			y = Integer.parseInt(x);
		}while(y<1 || y>9999);
		
		do{
			System.out.print("┣ 입고 월(1~12) : ");
			x = sc.next();
			imDate += ( "-" + x );
			y = Integer.parseInt(x);
		}while(y<1 || y>12);
		
		do{
			System.out.print("┣ 입고 일(1~31) : ");
			x = sc.next();
			imDate += ( "-" + x );
			y = Integer.parseInt(x);
		}while(y<1 || y>31);
		
		car.setImDate(imDate);
		
		do{
			System.out.print("┣ 입고 금액(만원)(0이상) : ");
			y = sc.nextInt();
			car.setImPrice(y);
		}while(y<0);
		
		do{
			System.out.print("┣ 예상출고가(만원)(0이상) : ");
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
		
		System.out.println("┣──────────────────────────────────────────────────────────────");
		
		System.out.printf("┃%6s ┃%5s ┃%7s ┃%4s ┃%6s ┃%8s ┃%8s ┃%6s ┃%6s ┃%6s ┃%3s",
				"Serial Number","차  종","차 이름","연  식","주행거리","입고날짜","출고날짜",
				"입고가격","예상출고가","출고가격","판매가능 여부");
		System.out.println();	
		
		while(it.hasNext()){
			
			CarVO car = it.next();
			
			System.out.println(car.toString());
		}
	}

	@Override
	public void delete() {
		
		Iterator<CarVO> it = li.iterator();
		System.out.print("┣ 삭제할 시리얼넘버 : ");
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
			System.out.println("┣ 삭제성공!");
		} else{
			System.out.println("┣ 삭제할 넘버가 존재하지 않습니다!");
		}
		System.out.println();
	}

	@Override
	public void update() {
		
		System.out.print("┣─────────────────────────────────────────────┫\n"
				   + "┃                           ┎───────┓┎───────┓                           ┃\n"
				   + "┃                           ┃  1.단순수정  ┃┃   2.판 매    ┃                           ┃\n"
				   + "┃                           ┖───────┚┖───────┚                           ┃\n"
				   + "┣─────────────────────────────────────────────┚\n");
		System.out.print("┣ 입력 : ");
		int num = sc.nextInt();
		
		if(num == 2){
			
			System.out.print("┣ 판매할 차량의 시리얼넘버 :");
			String sellSeri = sc.next();
			
			Iterator<CarVO> it = li.iterator();
			
			if (searchSerial(sellSeri)){
				
				while (it.hasNext()) {
					
					CarVO vo = it.next();
					
					if (vo.getSerial().equals(sellSeri)) {
		
						System.out.print("┣ 출고 연도 : ");
						String exDate = sc.next();
		
						System.out.print("┣ 출고 월 : ");
						exDate += ("-" + sc.next());
		
						System.out.print("┣ 출고 일 : ");
						exDate += ("-" + sc.next());
		
						vo.setExDate(exDate);
		
						System.out.print("┣ 실제출고가(만원) : ");
						vo.setExPrice(sc.nextInt());
						
						vo.setValid("Sold");
		
						System.out.println("┣ 판매완료!");
					}	
				}
			}else{
				System.out.println("┣ 수정할 넘버가 존재하지 않습니다!");
			}
			
		}else if(num == 1){
		
			System.out.print("┣ 수정할 차량의 시리얼넘버 :");
			String modSeri = sc.next();
			
			Iterator<CarVO> it = li.iterator();
	
			if (searchSerial(modSeri)){
				
				while (it.hasNext()) {
					
					CarVO vo = it.next();
					
					if (vo.getSerial().equals(modSeri)) {
		
						System.out.print("┣ 차종(영어만 가능) : ");
						vo.setModel(sc.next());
		
						/*
						 * try { flag = carEx.idEx(car.getModel(), li); } catch
						 * (Exception e) { flag = false;
						 * System.out.println(e.toString()); } }while(flag == false);
						 */
		
						System.out.print("┣ 차이름(영어만 가능) : ");
						vo.setName(sc.next());
		
						System.out.print("┣ 연식 : ");
						vo.setYear(sc.next());
		
						System.out.print("┣ 키로수(km) : ");
						vo.setKm(sc.nextInt());
		
						System.out.print("┣ 입고 연도 : ");
						String imDate = sc.next();
		
						System.out.print("┣ 입고 월 : ");
						imDate += ("-" + sc.next());
		
						System.out.print("┣ 입고 일 : ");
						imDate += ("-" + sc.next());
		
						vo.setImDate(imDate);
		
						System.out.print("┣ 입고 금액(만원) : ");
						vo.setImPrice(sc.nextInt());
		
						System.out.print("┣ 예상출고가(만원) : ");
						vo.setPrePrice(sc.nextInt());
		
						System.out.println("┣ 변경완료!");
					}	
				}
			}else{
				System.out.println("┣ 수정할 넘버가 존재하지 않습니다!");
			}
		}
	}
	
	@Override
	public void findComplex() {
		
		System.out.print("┣─────────────────────────────────────────────┫\n"
				   + "┃         ┎───────┓┎───────┓┎───────┓┎───────┓         ┃\n"
				   + "┃         ┃  1.차종검색  ┃┃ 2.차이름검색 ┃┃ 3.가격대검색 ┃┃4.판매여부검색┃         ┃\n"
				   + "┃         ┖───────┚┖───────┚┖───────┚┖───────┚         ┃\n"
				   + "┃          ex) 콤마(,) 로 구분하여 검색항목 설정 예) 1,3,4                                 ┃\n"
				   + "┣─────────────────────────────────────────────┚\n");
		System.out.print("┣ 입력 : ");
		
		String searchComplex = "";
		
		try {
			searchComplex = br.readLine();
		} catch (IOException e) {
			System.out.print("┣ ");
			e.toString();
		}
		
		searchComplex = searchComplex.replaceAll("\\s", "");
		
		String[] item = searchComplex.split(",");
		
		li2.addAll(li);
		
		for(String index : item){
			
			switch(Integer.parseInt(index)){
			
			case 1:
				
				System.out.print("┣ 검색할 차종 : ");
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
				
				System.out.print("┣ 검색할 차이름(영어만 가능) : ");
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
				
				System.out.print("┣ 검색할 가격대(만원) <예: 3500 ~ 5000> : ");
				String searchprice = "";
				
				try {
					searchprice = br.readLine();
				} catch (IOException e) {
					System.out.print("┣ ");
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
				
				System.out.print("┣ forSale(1) or Sold(2) : ");
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
		
		System.out.println("┣──────────────────────────────────────────────────────────────");
		
		System.out.printf("┃%6s %5s %7s %4s %6s %8s %8s %6s %6s %6s %3s",
						  "Serial Number","차종","차이름","연식","주행거리","입고날짜","출고날짜",
						  "입고가격","예상출고가","출고가격","판매가능 여부");
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
			
			System.out.println("┣ 저장 성공!");
			
		} catch (Exception e) {
			System.out.print("┣ ");
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
				System.out.print("┣ ");
				System.out.print(e.toString());
			}
		}
	}
}























