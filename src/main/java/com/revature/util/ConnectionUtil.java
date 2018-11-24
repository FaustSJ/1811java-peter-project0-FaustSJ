package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@this-db-instance-is-fly.cqo7p2gsckmz.us-east-1.rds.amazonaws.com:1521:ORCL";
		String user = "Banker";
		String password = "p4ssw0rd";
		
		return DriverManager.getConnection(url, user, password);
	}
	
}
