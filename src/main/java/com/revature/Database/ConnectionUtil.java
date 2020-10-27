package com.revature.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static String url;
    private static String username;
    private static String password;
	private static Connection connection;
	
	
	public static synchronized Connection getConnection() throws SQLException {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(url, username, password);
		}
		return connection;
	}

	public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        ConnectionUtil.password = password;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String Username) {
        ConnectionUtil.username = Username;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        ConnectionUtil.url = url;
    }
}
