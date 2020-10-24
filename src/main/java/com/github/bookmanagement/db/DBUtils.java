package com.github.bookmanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

	String mysqlDriver = "com.mysql.cj.jdbc.Driver";
	String mysqlHost = "";
	String mysqlPort = "";
	String mysqlDatabase = "";
	String mysqlUser = "";
	String mysqlPass = "";
	static Connection connection = null;
	
	public DBUtils() {
		System.out.println("Give env values");
		mysqlHost = System.getenv("mysqlHost");
		if(mysqlHost == null || mysqlHost.equals("")) {
			mysqlHost = "192.168.222.62";
		}
		mysqlPort = System.getenv("mysqlPort");
		if(mysqlPort == null || mysqlPort.equals("")) {
			mysqlPort = "3306";
		}
		mysqlDatabase = System.getenv("mysqlDatabase");
		if(mysqlDatabase == null || mysqlDatabase.equals("")) {
			mysqlDatabase = "book";
		}
		mysqlUser = System.getenv("mysqlUser");
		if(mysqlUser == null || mysqlUser.equals("")) {
			mysqlUser = "apper";
		}
		mysqlPass = System.getenv("mysqlPass");
		if(mysqlPass == null || mysqlPass.equals("")) {
			mysqlPass = "app123";
		}
		try {
			Class.forName(mysqlDriver);
			String mysqlUrl = "jdbc:mysql://"+mysqlHost+":"+mysqlPort+"/"+mysqlDatabase+"?allowPublicKeyRetrieval=true&useSSL=false";
			System.out.println("Mysql url is:" + mysqlUrl);
			connection = DriverManager.getConnection(mysqlUrl, mysqlUser, mysqlPass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.modifyDb(connection);
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
	
	private void modifyDb(Connection connection) {
		String createBookDetailTable = "CREATE TABLE IF NOT EXISTS `bookdetail` (\r\n" + 
				"  `id` int NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `name` varchar(50) DEFAULT NULL,\r\n" + 
				"  `description` varchar(200) DEFAULT NULL,\r\n" + 
				"  PRIMARY KEY (`id`)\r\n" + 
				");";
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(createBookDetailTable);	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
