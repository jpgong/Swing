package jpgong.JDBCStudent;

public class test {
	public static void main(String[] args) {
		//���ѧ����Ϣ
		StuDao stuDao = new StuDao();
		Student student = new Student();
		student.setId(3);
		student.setName("����");
		student.setSex("��");
		student.setAge(21);
		student.setHobby("����");
		student.setSclass("�������");
		stuDao.addStudent(student);
		System.out.println("��ӳɹ�");
	}

}
