package Connection;

import java.sql.*;

public class Connecteur {
	
	private static String url = "jdbc:mysql://localhost:3306/pharma";
	
	private static String user = "root";
	
	private static String pass = "ansufati10";
	
	private static Connection connect;
	
	
	static {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection disponible!!!");
		} catch (Exception e) {
			System.out.println("pas de connection!");
			System.err.println(e);
		}
	}

	public static Connection Connect() {
		return connect;
	}

}
