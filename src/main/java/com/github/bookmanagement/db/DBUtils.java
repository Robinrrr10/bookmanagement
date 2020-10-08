package com.github.bookmanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

	String mysqlDriver = "com.mysql.cj.jdbc.Driver";
	String mysqlHost = "";
	String mysqlPort = "3306";
	String mysqlDatabase = "book";
	String mysqlUser = "apper";
	String mysqlPass = "app123";
	Connection connection = null;
	
	public DBUtils() {
		mysqlHost = System.getenv("mysqlhost");
		if(mysqlHost == null || mysqlHost.equals("")) {
			mysqlHost = "192.168.222.62";
		}
		try {
			Class.forName(mysqlDriver);
			String mysqlUrl = "jdbc:mysql://"+mysqlHost+":"+mysqlPort+"/"+mysqlDatabase+"?useSSL=false";
			System.out.println("Mysql url is:" + mysqlUrl);
			connection = DriverManager.getConnection(mysqlUrl, mysqlUser, mysqlPass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<String> getBooks() {
		List<String> booknames = new ArrayList<String>();
		String sqlQuery = "SELECT name FROM bookdetail;";
		ResultSet result = null;
		try {
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			 result = statement.executeQuery();
			 while(result.next()) {
					booknames.add(result.getString("name"));
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return booknames;
	}
	
	public List<String> addBook(String name, String description){
		List<String> booknames = new ArrayList<String>();
		String sqlQuery = " INSERT INTO bookdetail (name, description) VALUE (?, ?);";
		try {
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, name);
			statement.setString(2, description);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return booknames;
	}
}
