package car;

public interface Car {

	public void inputDB();		// DBȣ��
	public void input(); 		// �Է�
	public boolean searchSerial(String serial); // �ø����ȣ �˻�
	public void print();		// ���
	public void delete(); 		// ����
	public void update(); 		// ���� (1.�ܼ����� 2.����, ��������� �ǸŰ��� ���� ����)
	public void findComplex(); 	// ���� �˻�(����/���̸�/�������/�ǸŰ��ɿ���)
	public void save(); 		// ����
}
