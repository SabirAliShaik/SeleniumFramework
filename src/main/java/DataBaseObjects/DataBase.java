package DataBaseObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import Utilities.CommonHelpers;

public class DataBase {

	private static Connection connectDB = null;
	private static Statement statement = null;
	
	private static String getPathOfDBFile(String fileName) {
		
		String path = System.getProperty("user.dir") + "\\src\\test\\Resources\\DBQuery\\";
		path = path + fileName + ".txt";
		if(CommonHelpers.isFileExists(path)) {
			return path;
		}
		return null;
	}
	
	private static Connection createDBConnection() {
		
		Properties pro = CommonHelpers.readPropertiesFile("db.properties");
		String connectionString  = "jdbc:oracle:thin:@[tnsName]";
		connectionString.replace("[tnsName]", pro.getProperty(pro.getProperty("TNSName")));
		System.setProperty("oracle.net.tns_admin", "c:/Oracle11gR2/Product/11.2/client_1/network/admin");
		try {
			connectDB = DriverManager.getConnection(connectionString, pro.getProperty("user"), pro.getProperty("password"));
		} catch (SQLException e) {
			String errorMessage = e.getMessage();
			if(null != errorMessage) {
				System.out.println("Failed to open DB connection: "+ errorMessage + "\nUserId"+ pro.getProperty("user"));
			}
			try {
				connectDB.close();
			}
			catch(Exception e1) {
				
			}
			connectDB = null;
		}
		
		return connectDB;
		
	}
	
	private static Statement createStatement(Connection connect) {
		
		try {
			statement = connect.createStatement();
			statement.execute("Alter session set current_schema = I14_EDB");
		} catch (SQLException e) {
			String errorMessage = e.getMessage();
			System.out.println(e.getMessage());
			if(errorMessage != null) {
				System.out.println("Failed to create statement: "+errorMessage);
			}
			try {
				statement.close();
			}
			catch(Exception e1) {	
			}
			statement = null;
		}
		return statement;
	}
	
	private static void closeConnection(){
		if(connectDB != null) {
			try {
				connectDB.close();
			}
			catch(SQLException e) {
				System.out.println("Exception found: "+e.getMessage());
			}
		}
		
	}

	private static void closeStatementConnection() {
		if(statement != null) {
			try {
				statement.close();
			}
			catch(SQLException e) {
				System.out.println("Exception Found: "+e.getMessage());
			}
		}
	}

	private static List<Map<String, Object>> resultSetToList(ResultSet result) {
		
		ResultSetMetaData metaData = null;
		List<Map<String,Object>> rows = new ArrayList<Map<String,Object>>();
		try {
			int columns = metaData.getColumnCount();
			Map<String, Object> row = new HashMap<String, Object>(columns);
			while(result.next()) {
				for(int i = 1;i < columns; i++) {
					row.put(metaData.getColumnName(i), result.getObject(i));
				}
				rows.add(row);
			}
		}
		catch(SQLException e) {
			System.out.println("Exception found : "+e.getMessage());
		}
		return rows;
	}

	public static List<Map<String, Object>> executeQuery(String query) {
		
		ResultSet queryResult;
		List<Map<String,Object>> returnResult;
		try {
			queryResult = createStatement(createDBConnection()).executeQuery(query);
			returnResult = resultSetToList(queryResult);
			queryResult.close();
			closeStatementConnection();
			closeConnection();
			System.out.println("Result : "+returnResult);
		}
		catch(Exception e) {
			System.out.println("exception found: "+e.getMessage());
			return null;
		}
		return returnResult;
	}
}



