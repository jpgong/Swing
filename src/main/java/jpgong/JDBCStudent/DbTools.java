package jpgong.JDBCStudent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ���ݿ�������
 * @author jpgong
 *
 */
public class DbTools {
	/**
	 * ���ֳ�����JDBC_URL����ʽ
	 * 1������Oracle���ݿ������    jdbc:oracle:thin:@localhost:1521:sid
	 * 2������SQLserver���ݿ������  jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=STOREDB
	 * 3������MySQL���ݿ������  jdbc:mysql://localhost:3306/STOREDB
	 * 4��java�Դ���Derby���ݿ������ 
	 */
	
	/**
	 * �����ļ��ظ����ݿ����������
	 * 1������SQLserver���ݿ�    "com.microsoft.jdbc.sqlserver.SQLServerDriver"
	 * 2������Oracle���ݿ�   "oracle.jdbc.driver.OracleDriver"
	 * 3������MySQL���ݿ�     "com.mysql.jdbc.Driver"
	 * 4��java�Դ���Derby���ݿ�  "org.apache.derby.jdbc.EmbeddedDriver"
	 */
	
	private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static String dbName = "Student";
	private static String connectionURL = "jdbc:derby:" + dbName + ";create=true";

	/**
	 * �������ݿ�
	 * @return  �����ݿ����Ӻú�Ӧ�÷���һ��Connection����
	 */
	public Connection getConn() {
		Connection connection = null;
		
		try {
			//����MySQL����������
			Class.forName(driver);
			System.out.println("JDBC dirver�Ѽ���");
			//�������ݿ��ļ�����־
			DriverManager.setLogWriter(new PrintWriter(new File("���ݿ���־�ļ�.txt")));
			connection = DriverManager.getConnection(connectionURL);
		} catch (ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
			System.out.println("\n    >>> Please check your CLASSPATH variable   <<<\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}
