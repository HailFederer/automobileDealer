package car;

public interface Car {

	public void inputDB();		// DB호출
	public void input(); 		// 입력
	public boolean searchSerial(String serial); // 시리얼번호 검색
	public void print();		// 출력
	public void delete(); 		// 삭제
	public void update(); 		// 수정 (1.단순수정 2.출고시, 실제출고가와 판매가능 여부 변경)
	public void findComplex(); 	// 복합 검색(차종/차이름/예상출고가/판매가능여부)
	public void save(); 		// 저장
}
