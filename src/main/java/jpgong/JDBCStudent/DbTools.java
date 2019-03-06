package jpgong.JDBCStudent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接类
 * @author jpgong
 *
 */
public class DbTools {
	/**
	 * 几种常见的JDBC_URL的形式
	 * 1、对于Oracle数据库的连接    jdbc:oracle:thin:@localhost:1521:sid
	 * 2、对于SQLserver数据库的连接  jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=STOREDB
	 * 3、对于MySQL数据库的连接  jdbc:mysql://localhost:3306/STOREDB
	 * 4、java自带的Derby数据库的连接 
	 */
	
	/**
	 * 常见的加载各数据库的驱动器类
	 * 1、加载SQLserver数据库    "com.microsoft.jdbc.sqlserver.SQLServerDriver"
	 * 2、加载Oracle数据库   "oracle.jdbc.driver.OracleDriver"
	 * 3、加载MySQL数据库     "com.mysql.jdbc.Driver"
	 * 4、java自带的Derby数据库  "org.apache.derby.jdbc.EmbeddedDriver"
	 */
	
	private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static String dbName = "Student";
	private static String connectionURL = "jdbc:derby:" + dbName + ";create=true";

	/**
	 * 连接数据库
	 * @return  和数据库连接好后应该返回一个Connection对象
	 */
	public Connection getConn() {
		Connection connection = null;
		
		try {
			//加载MySQL的驱动器类
			Class.forName(driver);
			System.out.println("JDBC dirver已加载");
			//设置数据库文件的日志
			DriverManager.setLogWriter(new PrintWriter(new File("数据库日志文件.txt")));
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
