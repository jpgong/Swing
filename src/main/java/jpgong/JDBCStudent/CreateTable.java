package jpgong.JDBCStudent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTable {
	public static void main(String[] args) {
		DbTools dbTools = new DbTools();
	    Connection connection = dbTools.getConn();
		String sqlString = "create table student( " // ����
				+ "id int primary key not null, " // �û���
				+ "name varchar(20), " // �û�����
				+ "sex varchar(4),"   //�û��Ա�
				+ "age int,"   //�û�����
				+ "hobby varchar(30000),"    //ѧ������
				+ "sclass varchar(20)"    //ѧ���༶
				+ ")"; 
		try {
			PreparedStatement ps = connection.prepareStatement(sqlString);
			ps.execute();
			ps.close();
			System.out.println("���ݱ�student�����ɹ�");
		} catch (SQLException e1) {
			System.out.println("���ݱ��Ѵ���");
			e1.printStackTrace();
		}
		
	
	}

}
