package jpgong.JDBCStudent;

public class test {
	public static void main(String[] args) {
		//添加学生信息
		StuDao stuDao = new StuDao();
		Student student = new Student();
		student.setId(3);
		student.setName("王五");
		student.setSex("男");
		student.setAge(21);
		student.setHobby("看书");
		student.setSclass("三年二班");
		stuDao.addStudent(student);
		System.out.println("添加成功");
	}

}
