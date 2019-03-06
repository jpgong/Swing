package jpgong.JDBCStudent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * �����������ݿ��ʵ���࣬�ڸ����ж��������ӡ�ɾ�����޸�ѧ����Ϣ�ȷ���
 * @author jpgong
 *
 */
public class StuDao {
	
	/**
	 * ���ѧ����Ϣ
	 * @param student
	 * @return
	 */
	public int addStudent(Student student) {
		int i = 0;
		DbTools dbTools = new DbTools();
		Connection connection = dbTools.getConn();
		
		try {
			//����һ��sql���
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
	 * �޸�ѧ����Ϣ
	 * @param student
	 * @return
	 */
	public int updateStudent(Student student, int id) {
		int i = 0;
		DbTools dbTools = new DbTools();
		Connection connection = dbTools.getConn();
		
		try {
			//����һ��sql���
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
	 * ��ѯ����ѧ������Ϣ
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
			//ִ�в�ѯ������ѯ�Ľ������ֵ��ResultSet����
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
	 * ��ѯһ��ѧ������Ϣ
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
			//ִ�в�ѯ������ѯ�Ľ������ֵ��ResultSet����
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
	 * ɾ��ĳ��ѧ������Ϣ
	 * @param id
	 */
	public void deleteStudent(int id) {
		DbTools dbTools = new DbTools();
		Connection connection = dbTools.getConn();
		
		try {
			String sql = "delete from student where id=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			//ɾ�����ݿ��е�����
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
