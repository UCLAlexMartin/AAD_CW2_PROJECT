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
import java.text.SimpleDateFormat;
import java.util.Date;

import RESTdataEntities.Charity;
import RESTdataEntities.*;
import java.text.DateFormat;
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
     
      public static void generateSchema(int CharityId) throws Exception{
	    	
   		String CharityName = "charity" + CharityId;
   		getSystemConn();
   		
   		try {
   			
   			CallableStatement statement = conn.prepareCall("{call spSchemaGeneration(?)}");
   			statement.setString("DB_Name", CharityName);
   	    	statement.executeQuery();
   	    	//GenerateConfig.execute(CharityName);
   	    	
   			}catch(Exception e){
   				e.printStackTrace();
   			}	
   	}
     
     public static Map<Integer,ArrayList<String>> readEvents() throws Exception{
     
    	 Map<Integer,ArrayList<String>> dataMap = new TreeMap<Integer,ArrayList<String>>();
    	 
    	 getCharityConn("Charity_Db_Test_Model");
    	 statement = conn.createStatement();
    	 
    	 resultSet = statement.executeQuery
    	("	select a.Event_Name,a.Event_Description,Event_Location,a.Event_Date,a.Event_Time,a.Event_Id " +
    		"from `Event` A " +
    		"where a.Event_Date > Now() ");
    	 
    	 while (resultSet.next()){
        
    		 ArrayList<String> datatypes = new ArrayList<String>();
    		 datatypes.add(resultSet.getString(4));
    		 datatypes.add(resultSet.getString(1));
    		 datatypes.add(resultSet.getString(3));
    		 datatypes.add(resultSet.getString(5));
    		 datatypes.add(resultSet.getString(2));
    		 
    		 dataMap.put(resultSet.getInt(6), datatypes);
         }
         
    	 
    	 
    	closeConn();
    	return dataMap; 
     }
     
     
     public static String readCharityDataV2() throws Exception {

    	 StringBuilder result = new StringBuilder();

    	 getCharityConn("Charity_Db_Test_Model");
    	 statement = conn.createStatement();

    	 resultSet = statement.executeQuery
    	 ("SELECT Username as u,COUNT(*) as c FROM ( SELECT Users.Username AS Username,Filled_Form.User_Id as UserID, count(Filled_Form.Record_Id) as TotalInputs FROM Filled_Form INNER JOIN Form_Fields ON Filled_Form.Field_Id = Form_Fields.Field_Id INNER JOIN Users ON Filled_Form.User_Id = Users.User_Id WHERE Users.isActive = 1 AND Filled_Form.isActive = 1 AND Form_Fields.isActive = 1 GROUP BY Filled_Form.Record_Id) AS TEMP GROUP BY UserID"); 

    	 result.append('[');
    	 while(resultSet.next())
    	 {
    	 result.append(String.format("[\"%s\",%d],", resultSet.getString("u"), resultSet.getInt("c")));
    	 }
    	 result.setCharAt(result.length()-1, ']');
    	 closeConn();

    	 return result.toString();
    }


     
     public static List<Charity> readCharityTable() throws Exception {
        Charity ch;
        List<Charity> chs = new ArrayList<Charity>();
        getCharityConn("System_Db_Test_Model");
        statement = conn.createStatement();

        resultSet = statement.executeQuery
       ("SELECT Charity_ID, Charity_Name, Charity_Description, Address_Line1, Address_Line2, Location, PostCode, Email, Phone, User_Id, Registration_No, Account_No, Connection_String, isVerified, isActive, Timestamp from Charity order by Charity_ID");	

        while(resultSet.next())
        {
               //result += String.format("['%s',%d],", resultSet.getString("u"), resultSet.getInt("c"));
            ch = new Charity();
            ch.setCharityID(resultSet.getInt("Charity_ID"));
            ch.setCharityName(resultSet.getString("Charity_Name"));
            ch.setCharityDescription(resultSet.getString("Charity_Description"));
            ch.setAddressLine1(resultSet.getString("Address_Line1"));
            ch.setAddressLine2(resultSet.getString("Address_Line2"));
            ch.setLocation(resultSet.getString("Location"));
            ch.setPostCode(resultSet.getString("PostCode"));
            ch.setEmail(resultSet.getString("Email"));
            ch.setPhone(resultSet.getString("Phone"));
            ch.setUserId(resultSet.getInt("User_Id"));
            ch.setRegistrationNo(resultSet.getString("Registration_No"));
            ch.setAccountNo(resultSet.getString("Account_No"));
            ch.setConnectionString(resultSet.getString("Connection_String"));
            ch.setIsVerified(resultSet.getBoolean("IsVerified"));
            ch.setIsActive(resultSet.getBoolean("IsActive"));
            ch.setTimestamp(resultSet.getDate("Timestamp"));  
            
            chs.add(ch);
        }

        closeConn();

        return chs;
     }
     public static ArrayList<String> readSelectionValues(int field_id) throws Exception{
    	 
    	 ArrayList<String> dropdownData = new ArrayList<String>();
    	 getCharityConn("Charity_Db_Test_Model");
    	 statement = conn.createStatement();
    	 resultSet = statement.executeQuery(
    			 "SELECT Field_Selection_Value " +
    			 "FROM Field_Selection " +
    			 "WHERE Field_Id = " + field_id);
    	 
    	 while(resultSet.next())
    	 {
    		 dropdownData.add(resultSet.getString(1));
    	 }
    	 
    	 return dropdownData; 
     }
     
     
     public static Map<Integer,ArrayList<String>> readFormData(int form_id) throws Exception {
    	 //String result = "";
    	 Map<Integer,ArrayList<String>> dataMap = new TreeMap<Integer,ArrayList<String>>();
    	 getCharityConn("Charity_Db_Test_Model");
    	 statement = conn.createStatement();
    	 resultSet = statement.executeQuery
    	        	("SELECT B.Field_Id, B.Field_Label,C.Field_Type, C.Field_DataType,C.Field_Description, B.isRequired" +
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
    		 datatypes.add(resultSet.getString(6));
    		 datatypes.add(resultSet.getString(1));
    		 
    		 dataMap.put(resultSet.getInt(1), datatypes);
    	 }
    	 closeConn();
     	 
    	 return dataMap;
     }
     
     /*** LU BEGIN ** 
     
     public static List<Charity> readCharityTable() throws Exception {
         Charity ch;
         List<Charity> chs = new ArrayList<Charity>();
         getCharityConn("System_Db_Test_Model");
         statement = conn.createStatement();

         resultSet = statement.executeQuery
        ("SELECT Charity_ID, Charity_Name, Charity_Description, Address_Line1, Address_Line2, Location, PostCode, Email, Phone, User_Id, Registration_No, Account_No, Connection_String, isVerified, isActive, Timestamp from Charity order by Charity_ID");	

         while(resultSet.next())
         {
                //result += String.format("['%s',%d],", resultSet.getString("u"), resultSet.getInt("c"));
             ch = new Charity();
             ch.setCharityID(resultSet.getInt("Charity_ID"));
             ch.setCharityName(resultSet.getString("Charity_Name"));
             ch.setCharityDescription(resultSet.getString("Charity_Description"));
             ch.setAddressLine1(resultSet.getString("Address_Line1"));
             ch.setAddressLine2(resultSet.getString("Address_Line2"));
             ch.setLocation(resultSet.getString("Location"));
             ch.setPostCode(resultSet.getString("PostCode"));
             ch.setEmail(resultSet.getString("Email"));
             ch.setPhone(resultSet.getString("Phone"));
             ch.setUserId(resultSet.getInt("UserId"));
             ch.setRegistrationNo(resultSet.getString("RegistrationNo"));
             ch.setAccountNo(resultSet.getString("AccountNo"));
             ch.setConnectionString(resultSet.getString("ConnectionString"));
             ch.setIsVerified(resultSet.getBoolean("IsVerified"));
             ch.setIsActive(resultSet.getBoolean("IsActive"));
             ch.setTimestamp(resultSet.getTime("Timestamp"));          
             
             chs.add(ch);
         }

         closeConn();

         return chs;
      }
     ** LU END ***/ 
     
      

     /*** CHEN CHEN 2013-01-27 BEGIN ***/
     public static List<feedbackEntity> readFeedbacks() throws Exception{
    	 
    	 List<feedbackEntity> fds = new ArrayList<feedbackEntity>();
         feedbackEntity fd;
         Users user;
    	 getSystemConn();
    	 statement = conn.createStatement();
    	 resultSet = statement.executeQuery(
    			 "SELECT Feedback_Id, Name, Email, Comment, User_Id, Username, ReviewedDate, isReviewed, feedback.Timestamp " +
    			 "FROM feedback left join users on feedback.ReviewedBy = users.User_Id");
    	 
    	 while(resultSet.next())
    	 {
             fd = new feedbackEntity(); 
             user = new Users();
             user.setUserId(resultSet.getInt("User_Id"));
             user.setUsername(resultSet.getString("Username"));
             fd.setReviewedBy(user);
             fd.setFeedbackId(resultSet.getInt("Feedback_Id"));   
             fd.setName(resultSet.getString("Name"));
             fd.setEmail(resultSet.getString("Email"));
             fd.setComment(resultSet.getString("Comment"));
             fd.setReviewedDate(resultSet.getDate("ReviewedDate"));
             fd.setTimestamp(resultSet.getDate("feedback.Timestamp"));
             fds.add(fd);
    	 }
    	 closeConn();
    	 return fds; 
     }     
     
     public static feedbackEntity readSingleFeedback(int feedbackid) throws Exception{
    	 
         feedbackEntity fd;
         Users user;
    	 getSystemConn();
    	 statement = conn.createStatement();
    	 resultSet = statement.executeQuery(
    			 "SELECT Feedback_Id, Name, Email, Comment, User_Id, Username, ReviewedDate, isReviewed, feedback.Timestamp " +
    			 "FROM feedback left join users on feedback.ReviewedBy = users.User_Id where feedback.Feedback_id = " + feedbackid);
    	 
    	 if(resultSet.next())
    	 {
             fd = new feedbackEntity(); 
             user = new Users();
             user.setUserId(resultSet.getInt("User_Id"));
             user.setUsername(resultSet.getString("Username"));
             fd.setReviewedBy(user);
             fd.setFeedbackId(resultSet.getInt("Feedback_Id"));   
             fd.setName(resultSet.getString("Name"));
             fd.setEmail(resultSet.getString("Email"));
             fd.setComment(resultSet.getString("Comment"));
             fd.setReviewedDate(resultSet.getDate("ReviewedDate"));
             fd.setTimestamp(resultSet.getDate("feedback.Timestamp"));
    	 }
    	 else
         {
             fd = null;
         }
         closeConn();
    	 return fd; 
     }          
    public static boolean delSingleFeedback(int feedbackid) throws Exception{
    	 
    	 getSystemConn();
    	 statement = conn.createStatement();
         boolean result;
    	 result = statement.execute(
    			 "Delete from feedback where feedback.Feedback_id = " + feedbackid);
    	 
    	
         closeConn();
    	 return result; 
     }  
    
     public static boolean updSingleFeedback(feedbackEntity fd) throws Exception{

    	 getSystemConn();
         boolean updSuccess;
    	 statement = conn.createStatement();
          DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

         
         String sql =  "update feedback set Name = '" + fd.getName() + "', " +
                         "Email = '" + fd.getEmail() + "'," +
                         "Comment = '" + fd.getComment() + "'," +
                         "ReviewedBy = " + fd.getReviewedBy().getUserId() + "," +
                         "ReviewedDate = '" + df.format(fd.getReviewedDate()) + "'," +
                         "isReviewed = " + fd.getIsReviewed() + 
                         " where feedback_id = " + fd.getFeedbackId();
    	 updSuccess = statement.execute(sql);
    			
         closeConn(); 
         return updSuccess;
     }              
     
     
     public static void addFeedback(feedbackEntity fd) throws Exception{

    	 getSystemConn();
         boolean addSuccess;
    	 statement = conn.createStatement();
    	 addSuccess = statement.execute(
    			 "Insert feedback (Name, Email, Comment) values('" +
                          fd.getName() + "','" + 
                          fd.getEmail()+ "','" +
                          fd.getComment() + "')");
         closeConn(); 
     }        
     
/*** CHEN CHEN 2013-01-27 END ***/
     
    public static Charity getCharity(int charityid) throws Exception {
        Charity ch;
        getCharityConn("System_Db_Test_Model");
        statement = conn.createStatement();

        resultSet = statement.executeQuery
       ("SELECT Charity_ID, Charity_Name, Charity_Description, Address_Line1, " + 
                "Address_Line2, Location, PostCode, Email, Phone, User_Id, Registration_No," + 
                "Account_No, Connection_String, isVerified, isActive, Timestamp from Charity where charity_id ="
                + charityid);	
       ch = new Charity();
        if(resultSet.next())
        {
               //result += String.format("['%s',%d],", resultSet.getString("u"), resultSet.getInt("c"));

            ch.setCharityID(resultSet.getInt("Charity_ID"));
            ch.setCharityName(resultSet.getString("Charity_Name"));
            ch.setCharityDescription(resultSet.getString("Charity_Description"));
            ch.setAddressLine1(resultSet.getString("Address_Line1"));
            ch.setAddressLine2(resultSet.getString("Address_Line2"));
            ch.setLocation(resultSet.getString("Location"));
            ch.setPostCode(resultSet.getString("PostCode"));
            ch.setEmail(resultSet.getString("Email"));
            ch.setPhone(resultSet.getString("Phone"));
            ch.setUserId(resultSet.getInt("User_Id"));
            ch.setRegistrationNo(resultSet.getString("Registration_No"));
            ch.setAccountNo(resultSet.getString("Account_No"));
            ch.setConnectionString(resultSet.getString("Connection_String"));
            ch.setIsVerified(resultSet.getBoolean("IsVerified"));
            ch.setIsActive(resultSet.getBoolean("IsActive"));
            ch.setTimestamp(resultSet.getDate("Timestamp"));          
            
        }

        closeConn();

        return ch;
    }

     public static void addCharity(Charity ch) throws Exception{

    	 getSystemConn();
         boolean addSuccess;
    	 statement = conn.createStatement();
    	 addSuccess = statement.execute(
    			 "Insert charity (Charity_name, Charity_Description, Address_Line1, Address_Line2, Location, PostCode, Email, Phone, User_Id, Registration_No, Account_No, Connection_String, isVerified, isActive, Timestamp) values('" +
                          ch.getCharityName() + "','" + 
                          ch.getCharityDescription()+ "','" +
                          ch.getAddressLine1() + "','" +
                          ch.getAddressLine2() + "','" +
                          ch.getLocation() + "','" +
                          ch.getPostCode() + "','" +
                          ch.getEmail() + "','" +
                          ch.getPhone() + "','" +
                          ch.getUserId() + "," +
                          ch.getRegistrationNo() + "','" +
                          ch.getAccountNo() + "','" +
                          ch.getConnectionString() + "','" + 
                          ch.getIsVerified() + "," +
                          ch.getIsActive() + "," +
                          ch.getTimestamp() +
                          "')");
         closeConn(); 
     }      
     /**
      *  By Kede Bei
      * 	Last Updated: 30/01/2012 3:08am
      * **/

     
     public static List<String> searchtitle()
     {
     	List<String> list=new ArrayList<String>();
        	 try {
 			 getCharityConn("Charity_Db_Test_Model");
 			 Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE); 
 			 resultSet=statement.executeQuery("select * from form_fields");
// 	       	 ResultSetMetaData rsmd = resultSet.getMetaData() ; 
// 	       	 int columnCount = rsmd.getColumnCount();
// 	       	 for (int i=1;i<=columnCount;i++)
// 	       	 {
// 	       		 list.add(rsmd.getColumnName(i));
// 	       	  
// 	       	 }
 	       	 while(resultSet.next())
 	       	 {
 	       		list.add(resultSet.getString("Field_Label"));
// 	       	 ResultSetMetaData rsmd = resultSet.getMetaData() ; 
// 	       	 for(int i=1;i<rsmd.getColumnCount();i++)
// 	       	 {
// 	       		 list.add(String.valueOf(resultSet.getString("Field_Label")));
// 	       	 }
 	       	 }
 	     
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			
 			System.out.print("can");
 		}
			return list;
        	

     	
     }
public static ArrayList<String> FormNames() throws Exception{
    	 
    	 ArrayList<String> forms = new ArrayList<String>();
    	 getCharityConn("Charity_Db_Test_Model");
    	 statement = conn.createStatement();
    	 resultSet = statement.executeQuery(
    			 "SELECT Form_Name from `form`");
    	 
    	 while(resultSet.next())
    	 {
    		 forms.add(resultSet.getString(1));
    	 }
    	 
    	 return forms; 
     }
    
 public static ArrayList<String> UserType() throws Exception{
	 
	 ArrayList<String> utype = new ArrayList<String>();
	 getCharityConn("Charity_Db_Test_Model");
	 statement = conn.createStatement();
	 resultSet = statement.executeQuery(
			 " SELECT User_Type from `user_type`;");
	 
	 while(resultSet.next())
	 {
		 utype.add(resultSet.getString(1));
	 }
	 
	 return utype; 
 }
 public static Map<Integer,ArrayList<String>> readUsers() throws Exception {
	 //String result = "";
	 Map<Integer,ArrayList<String>> users = new TreeMap<Integer,ArrayList<String>>();
	 getCharityConn("Charity_Db_Test_Model");
	 statement = conn.createStatement();
	 resultSet = statement.executeQuery
			 	("SELECT Users.Username, User_Type.User_Type, Users.User_Email, GROUP_CONCAT(Form_Name) AS permissions, Users.User_Id " +
	"FROM User_Type INNER JOIN Users ON User_Type.User_Type_Id = Users.User_Type_Id "+
	"INNER JOIN Form_Permissions ON Form_Permissions.User_Type_Id = User_Type.User_Type_Id "+
	"INNER JOIN Form ON Form.Form_Id = Form_Permissions.Form_Id "+
			 	"GROUP BY User_Id ");

	 while(resultSet.next())
	 {
		 ArrayList<String> userdata = new ArrayList<String>();
		 userdata.add(resultSet.getString(1));
		 userdata.add(resultSet.getString(2));
		 userdata.add(resultSet.getString(3));
		 userdata.add(resultSet.getString(4));
		
		 
		 users.put(resultSet.getInt(5), userdata);
	 }
	 	return users;
	}
 
 public static ArrayList<String> MailingList() throws Exception{
	 
	 ArrayList<String> mlist = new ArrayList<String>();
	 getCharityConn("Charity_Db_Test_Model");
	 statement = conn.createStatement();
	 resultSet = statement.executeQuery(
			 " SELECT Mailing_Group from `mailing_group`;");
	 
	 while(resultSet.next())
	 {
		 mlist.add(resultSet.getString(1));
	 }
	 
	 return mlist; 
 }
 

 
public static ArrayList<String> MailingListOldUsers() throws Exception{
	 
	 ArrayList<String> mlist = new ArrayList<String>();
	 getCharityConn("Charity_Db_Test_Model");
	 statement = conn.createStatement();
	 resultSet = statement.executeQuery(
			 " SELECT User_Id, Mailing_Group_ID from `mailing_list` where Mailing_Group_ID = 1 ");
	 while(resultSet.next())
	 {
		 mlist.add(resultSet.getString(1));
	 }
	 
	 return mlist; 
 }
     /*** MENGYENG BEGIN ***/
     
     // return Active and Disable Account For UCLAdmin
 	public static String readSystemActiveAccount() throws Exception {
 		Integer countActive = 0, countDisable = 0;
 		String result = "";
 		getCharityConn("System_DB_Test_Model");
 		statement = conn.createStatement();
 		String sqlActive = "select count(*) as count from Users where isActive=1";
 		resultSet = statement.executeQuery(sqlActive);
 		if (resultSet.next()) {
 			countActive = resultSet.getInt("count");
 		}
 		String sqlDisable = "select count(*) as count from Users where isActive=0";
 		resultSet = statement.executeQuery(sqlDisable);
 		if (resultSet.next()) {
 			countDisable = resultSet.getInt("count");
 		}
 		closeConn();
 		result = String.format("['%s',%d],", "Active Account", countActive)
 				+ String.format("['%s',%d],", "Disable Account", countDisable);
 		return result;
 	}
 	
 	// return Verified and Unverified Charity For UCLdmin
 	public static String readSystemVerificationCharity() throws Exception {
 		Integer countActive = 0, countDisable = 0;
 		String result = "";
 		getCharityConn("System_DB_Test_Model");
 		statement = conn.createStatement();
 		String sqlActive = "select count(*) as count from Charity where isVerified=1";
 		resultSet = statement.executeQuery(sqlActive);
 		if (resultSet.next()) {
 			countActive = resultSet.getInt("count");
 		}
 		String sqlDisable = "select count(*) as count from Charity where isVerified=0";
 		resultSet = statement.executeQuery(sqlDisable);
 		if (resultSet.next()) {
 			countDisable = resultSet.getInt("count");
 		}
 		closeConn();
 		result = String.format("['%s',%d],", "Verified Account", countActive)
 				+ String.format("['%s',%d],", "Unverified Account", countDisable);
 		return result;
 	}
 	
 	// return the date user created the account for UCLAdmin
 	public static String readSystemAccountDuration() throws Exception {
 		Integer justCreated = 0, oneDay = 0, oneWeek = 0, oneMonth = 0, others = 0;
 		String result = "";
 		getCharityConn("System_DB_Test_Model");
 		statement = conn.createStatement();
 		String sql = "Select Date_Created from Users";
 		resultSet = statement.executeQuery(sql);
 		while (resultSet.next()) {

 			long lDuration = (System.currentTimeMillis() - resultSet
 					.getTimestamp(1).getTime()) / 100000;
 			if (lDuration > 36 && lDuration <= 864) {
 				oneDay++;
 			} else if (lDuration <= 36) {
 				justCreated++;
 			} else if (lDuration > 864 && lDuration <= 6048) {
 				oneWeek++;
 			} else if (lDuration > 6048 && lDuration <= 25920) {
 				oneMonth++;
 			} else {
 				others++;
 			}
 		}
 		closeConn();
 		result = String.format("['%s',%d],", "Just Created", justCreated)
 				+ String.format("['%s',%d],", "Within One Day", oneDay)
 				+ String.format("['%s',%d],", "Within One Week", oneWeek)
 				+ String.format("['%s',%d],", "Within One Month", oneMonth)
 				+ String.format("['%s',%d],", "Else", others);
 		return result;
 	}
 	
 	// return Active and Disable Account For CharityAdmin
 	public static String readCharityActiveAccount() throws Exception {
 		Integer countActive = 0, countDisable = 0;
 		String result = "";
 		getCharityConn("Charity_Db_Test_Model");
 		statement = conn.createStatement();
 		String sqlActive = "select count(*) as count from Users where isActive=1";
 		resultSet = statement.executeQuery(sqlActive);
 		if (resultSet.next()) {
 			countActive = resultSet.getInt("count");
 		}
 		String sqlDisable = "select count(*) as count from Users where isActive=0";
 		resultSet = statement.executeQuery(sqlDisable);
 		if (resultSet.next()) {
 			countDisable = resultSet.getInt("count");
 		}
 		closeConn();
 		result = String.format("['%s',%d],", "Active Account", countActive)
 				+ String.format("['%s',%d],", "Disable Account", countDisable);
 		return result;
 	}

 	// return form duration for CharityAdmin
 	public static String readCharityFormDuration() throws Exception {
 		Integer justCreated = 0, oneDay = 0, oneWeek = 0, oneMonth = 0, others = 0;
 		String result = "";
 		getCharityConn("Charity_Db_Test_Model");
 		statement = conn.createStatement();
 		String sql = "Select Date_Created from Form";
 		resultSet = statement.executeQuery(sql);
 		while (resultSet.next()) {

 			long lDuration = (System.currentTimeMillis() - resultSet
 					.getTimestamp(1).getTime()) / 100000;
 			if (lDuration > 36 && lDuration <= 864) {
 				oneDay++;
 			} else if (lDuration <= 36) {
 				justCreated++;
 			} else if (lDuration > 864 && lDuration <= 6048) {
 				oneWeek++;
 			} else if (lDuration > 6048 && lDuration <= 25920) {
 				oneMonth++;
 			} else {
 				others++;
 			}
 		}
 		closeConn();
 		result = String.format("['%s',%d],", "Just Created", justCreated)
 				+ String.format("['%s',%d],", "Within One Day", oneDay)
 				+ String.format("['%s',%d],", "Within One Week", oneWeek)
 				+ String.format("['%s',%d],", "Within One Month", oneMonth)
 				+ String.format("['%s',%d],", "Else", others);
 		return result;
 	}

 	// return the date user created the account for CharityAdmin
 	public static String readCharityAccountDuration() throws Exception {
 		Integer justCreated = 0, oneDay = 0, oneWeek = 0, oneMonth = 0, others = 0;
 		String result = "";
 		getCharityConn("Charity_Db_Test_Model");
 		statement = conn.createStatement();
 		String sql = "Select Date_Created from Users";
 		resultSet = statement.executeQuery(sql);
 		while (resultSet.next()) {

 			long lDuration = (System.currentTimeMillis() - resultSet
 					.getTimestamp(1).getTime()) / 100000;
 			if (lDuration > 36 && lDuration <= 864) {
 				oneDay++;
 			} else if (lDuration <= 36) {
 				justCreated++;
 			} else if (lDuration > 864 && lDuration <= 6048) {
 				oneWeek++;
 			} else if (lDuration > 6048 && lDuration <= 25920) {
 				oneMonth++;
 			} else {
 				others++;
 			}
 		}
 		closeConn();
 		result = String.format("['%s',%d],", "Just Created", justCreated)
 				+ String.format("['%s',%d],", "Within One Day", oneDay)
 				+ String.format("['%s',%d],", "Within One Week", oneWeek)
 				+ String.format("['%s',%d],", "Within One Month", oneMonth)
 				+ String.format("['%s',%d],", "Else", others);
 		return result;
 	}
 	
     
 	/*** MENGYENG END ***/ 
}

