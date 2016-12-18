package car;

import java.util.List;

public class CarException {
	
	public Boolean modelEx(String str, List<CarVO> li) throws Exception{
			
		for(int i=0; i<str.length(); i++){
	
			char ch = str.charAt(i);
				
			if(!((ch>47 && ch<58) || (ch>64 && ch<91) || (ch>96 && ch<123))){
				
				System.out.print("┣ ");
					
				throw new Exception("영어와 숫자만 가능!\n");
			}
		}
		
		return true;
	}
	
	public Boolean nameEx(String str, List<CarVO> li) throws Exception{
			
		for(int i=0; i<str.length(); i++){
	
			char ch = str.charAt(i);
				
			if(!((ch>47 && ch<58) || (ch>64 && ch<91) || (ch>96 && ch<123))){
				
				System.out.print("┣ ");
					
				throw new Exception("영어와 숫자만 가능!\n");
			}
		}
		
		return true;
	}
}
