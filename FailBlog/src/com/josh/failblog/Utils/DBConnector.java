package com.josh.failblog.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "fubar";
	private static final String SERVER = "jdbc:mysql://localhost:3306/FailBlog";

	public static void main(String[] args) {
		try {
			if (DBConnector.connect() != null) {
				System.out.println("Success");
			} else {
				System.out.println("Fail");
			}
		} catch (Exception e) {
			System.out.println("Error");
		}
	}

	public static Connection connect() throws ClassNotFoundException,
			SQLException {

		Class.forName(DRIVER);
		return DriverManager.getConnection(SERVER, USERNAME, PASSWORD);

	}
}