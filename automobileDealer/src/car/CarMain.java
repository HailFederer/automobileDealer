package car;

import java.util.Scanner;

public class CarMain {
	
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		CarImpl ob = new CarImpl();
				
		ob.inputDB();
		ob.setDC();
		
		Thread setDC = new Thread(ob);
		
		setDC.start();
		
		int ch;
		
		System.out.print("�Ǧ�������������������������������������������������������������������������������������������\n"
				   + "��          i@B@@@B@B@B@r                                                     MB@M          ��\n"
				   + "��              BB@                                                F@NLr7L.   iB@j          ��\n"
				   + "��             ,@@@BBME.               B@B@B@B@B@@@B                 ;@@@B@.    @B7         ��\n"
				   + "��         ..vB@j   :MBF                         @BL                      ,Ji  .B@r         ��\n"
				   + "��                                               B@:              r@@@@B@M@BB   @@r         ��\n"
				   + "��   .B@B@BBMMM@B@@@B@@@B@M                      @B                 i. MBr      B@:         ��\n"
				   + "��     7@B@OXJv:i. X@B.                 .B@5.    B@                   F@@5      @B7 uu:     ��\n"
				   + "��                  B@                   iB@B    M                  .@BU M@@B   B@B@B@B     ��\n"
				   + "��                  @G                     B@                     ,G@X    iB@q  @Bi         ��\n"
				   + "��             NB@@@B:                     @@ .:ivP@@8:         :7L;        .   @@:         ��\n"
				   + "��            @Bu   SBL           @B@B@B@B@@@B@@@B@B@B@                         @@          ��\n"
				   + "��            B@i   i@B                                                         O@          ��\n"
				   + "��             E@B@B@B                                                          NL          ��\n"
				   + "��               ruY                                                            .           ��\n");
		
		
		while(true){
			
			do{
				System.out.print("����������������������������������������������������������������������������������������������\n"
					   	   + "���Ǧ����������������Ǧ����������������Ǧ����������������Ǧ����������������Ǧ�����������������\n"
					   	   + "����  1.�����߰�  ����  2.�������  ����   3. �� ��   ����   4. �� ��   ����  5.���հ˻�  ����\n"
			    		   + "���Ŧ��������������æŦ��������������æŦ��������������æŦ��������������æŦ��������������æ�\n"
						   + "���Ǧ����������������Ǧ����������������Ǧ�����������������������������������������������������\n"
						   + "����   6. Save    ����   7. �� ��   ����         ������ : ������, �����, �̱���          ����\n"
						   + "���Ŧ��������������æŦ��������������æŦ��������������������������������������������������æ�\n"
						   + "����������������������������������������������������������������������������������������������\n"
						   + "��");
			System.out.print(" �޴� ����  : "); 
			
			ch = sc.nextInt();
			
			}while(ch<1 || ch>7);
			
			switch(ch){
			
			case 1: ob.input(); break;
			case 2: ob.print(); break;
			case 3: ob.delete(); break;
			case 4: ob.update(); break;
			case 5: ob.findComplex(); break;
			case 6: ob.save(); break;
			default:
				ob.save();
				System.out.println("�� �����մϴ�. ���� ����Ϸ�! ����������������������������������������������������������������");
				System.exit(0);
			}
		}
	}
}
