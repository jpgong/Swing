package jpgong.JDBCStudent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTable {
	public static void main(String[] args) {
		DbTools dbTools = new DbTools();
	    Connection connection = dbTools.getConn();
		String sqlString = "create table student( " // 表名
				+ "id int primary key not null, " // 用户名
				+ "name varchar(20), " // 用户姓名
				+ "sex varchar(4),"   //用户性别
				+ "age int,"   //用户年龄
				+ "hobby varchar(30000),"    //学生爱好
				+ "sclass varchar(20)"    //学生班级
				+ ")"; 
		try {
			PreparedStatement ps = connection.prepareStatement(sqlString);
			ps.execute();
			ps.close();
			System.out.println("数据表student创建成功");
		} catch (SQLException e1) {
			System.out.println("数据表已存在");
			e1.printStackTrace();
		}
		
	
	}

}
