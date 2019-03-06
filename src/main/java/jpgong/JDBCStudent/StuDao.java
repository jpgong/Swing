package jpgong.JDBCStudent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建操作数据库的实现类，在该类中定义了增加、删除、修改学生信息等方法
 * @author jpgong
 *
 */
public class StuDao {
	
	/**
	 * 添加学生信息
	 * @param student
	 * @return
	 */
	public int addStudent(Student student) {
		int i = 0;
		DbTools dbTools = new DbTools();
		Connection connection = dbTools.getConn();
		
		try {
			//生成一条sql语句
			String sql = "insert into student(id,name,sex,age,hobby,sclass) values(?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, student.getId());
			ps.setString(2, student.getName());
			ps.setString(3, student.getSex());
			ps.setInt(4, student.getAge());
			ps.setString(5, student.getHobby());
			ps.setString(6, student.getSclass());
			i = ps.executeUpdate();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * 修改学生信息
	 * @param student
	 * @return
	 */
	public int updateStudent(Student student, int id) {
		int i = 0;
		DbTools dbTools = new DbTools();
		Connection connection = dbTools.getConn();
		
		try {
			//生成一条sql语句
			String sql = "update student set name=?,sex=?,age=?,hobby=?,sclass=? where id=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setString(2, student.getSex());
			ps.setInt(3, student.getAge());
			ps.setString(4, student.getHobby());
			ps.setString(5, student.getSclass());
			ps.setInt(6, id);
			i = ps.executeUpdate();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * 查询所有学生的信息
	 * @return
	 */
	public List<Student> listAllStudent(){
		DbTools dbTools = new DbTools();
		Connection connection = dbTools.getConn();
		String sql = "select * from student";
		ResultSet rs = null;
		List<Student> list = new ArrayList<>();
		
		try {
			Statement st = connection.createStatement();
			//执行查询，将查询的结果集赋值给ResultSet对象
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setSex(rs.getString("sex"));
				student.setAge(rs.getInt("age"));
				student.setHobby(rs.getString("hobby"));
				student.setSclass(rs.getString("sclass"));
				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 查询一个学生的信息
	 * @param id
	 * @return
	 */
	public Student getOne(int id) {
		DbTools dbTools = new DbTools();
		Connection connection = dbTools.getConn();
		
		String sql = "select * from student where id=?";
		Student student = null;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = null;
			Statement st = connection.createStatement();
			//执行查询，将查询的结果集赋值给ResultSet对象
			rs = st.executeQuery(sql);
			while(rs.next()) {
				student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setSex(rs.getString("sex"));
				student.setAge(rs.getInt("age"));
				student.setHobby(rs.getString("hobby"));
				student.setSclass(rs.getString("sclass"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
	
	/**
	 * 删除某个学生的信息
	 * @param id
	 */
	public void deleteStudent(int id) {
		DbTools dbTools = new DbTools();
		Connection connection = dbTools.getConn();
		
		try {
			String sql = "delete from student where id=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			//删除数据库中的数据
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
