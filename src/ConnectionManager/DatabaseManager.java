package ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import staticResources.Configuration;

public class DatabaseManager {

	 private static Connection conn = null;
     private static Statement statement = null;
     //private static PreparedStatement preparedStatement = null;
     private static ResultSet resultSet = null;
     private static String driver = Configuration.MySQLdriver;
     private static String dbURL = Configuration.MySQLConUrl;

     public static void getSystemConn () throws Exception{
    
    	String connString = dbURL + "System_DB_Test_Model";
    	  
        try 
        {
        	Class.forName(driver).newInstance();
		    conn  = DriverManager.getConnection(connString, Configuration.MySQLrootUser, Configuration.MySQLrootPassword);
        } 
        catch (Exception e) 
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        
     }
     
     public static void getCharityConn (String dbName) throws Exception{
     
    	String connString = dbURL + dbName;
        
        try 
        {
        	Class.forName(driver).newInstance();
		    conn  = DriverManager.getConnection(connString, Configuration.MySQLrootUser, Configuration.MySQLrootPassword);
		    
        } 
        catch (Exception e) 
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        
     }
     
     public static void closeConn() throws Exception{
    	 try
    	 {
    		 conn.close();
    	 }
    	 catch (Exception e) 
         {
             System.err.println("Got an exception! ");
             System.err.println(e.getMessage());
         }
     }
     
     public static String Test()
     {
    	 String result = "";
    	 try {
    	 getCharityConn("Charity_Db_Test_Model");
    	 statement = conn.createStatement();
    	 
    	 resultSet = statement.executeQuery("SELECT Username FROM Users");
    	 
			while(resultSet.next())
			 {
				result +=  resultSet.getString("Username");
				 
			 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	 return result;
     }
     
     public static String readCharityDataV2() throws Exception {
         
    	 String result = "";
    	 
        	 getCharityConn("Charity_Db_Test_Model");
        	 statement = conn.createStatement();
        	 
        	 resultSet = statement.executeQuery
        	("SELECT Username as u,COUNT(*) as c FROM( SELECT Users.Username AS Username,Filled_Form.User_Id as UserID, count(Filled_Form.Filled_Form_Id) as TotalInputs FROM 	Filled_Form INNER JOIN Form ON Filled_Form.Form_Id = Form.Form_Id INNER JOIN Form_Fields ON Filled_Form.Field_Id = Form_Fields.Field_Id	INNER JOIN Users ON Filled_Form.User_Id = Users.User_Id WHERE	Users.isActive = 1  AND Filled_Form.isActive = 1 AND Form_Fields.isActive = 1 AND Form.isActive = 1 GROUP BY Filled_Form.`Timestamp`) AS TEMP GROUP BY UserID");	
           
        	   
        	 while(resultSet.next())
        	 {
        		result += String.format("['%s',%d],", resultSet.getString("u"), resultSet.getInt("c"));
        	 }
        	          
        	 closeConn();
             	 
    	 return result;
     }
     
     public static Map<Integer,ArrayList<String>> readFormData(int form_id) throws Exception {
    	 //String result = "";
    	 Map<Integer,ArrayList<String>> dataMap = new TreeMap<Integer,ArrayList<String>>();
    	 getCharityConn("Charity_Db_Test_Model");
    	 statement = conn.createStatement();
    	 resultSet = statement.executeQuery
    	        	("select B.Field_Id, Field_Label,Field_Type, Field_DataType, isRequired" +
    	        			" FROM Form A INNER JOIN Form_Fields B ON A.Form_Id = B.Form_Id " +
    	        			" INNER JOIN Field_Type C ON B.Field_Type_Id = C.Field_Type_Id " +
    	        			"WHERE A.isActive = 1 AND B.isActive = 1 AND C.isActive = 1 AND A.Form_Id =" +form_id);
    	 while(resultSet.next())
    	 {
    		//result += String.format("['%s',%d],", resultSet.getString("u"), resultSet.getInt("c"));
    		 
    		 ArrayList<String> datatypes = new ArrayList<String>();
    		 datatypes.add(resultSet.getString(2));
    		 datatypes.add(resultSet.getString(3));
    		 datatypes.add(resultSet.getString(4));
    		 datatypes.add(resultSet.getString(5));
    		 
    		 dataMap.put(resultSet.getInt(1), datatypes);
    	 }
    	 closeConn();
     	 
    	 return dataMap;
     }
         
}
