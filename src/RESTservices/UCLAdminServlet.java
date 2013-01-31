package RESTservices;

import hibernateEntities.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.PrintWriter;
import org.hibernate.Query;
import java.sql.*;

import com.mysql.jdbc.PreparedStatement;

import hibernateEntities.*;
import ConnectionManager.*;

/**
 * Servlet implementation class UCLAdminServlet
 */
@WebServlet("/UCLAdminServlet")
public class UCLAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static SessionFactory factory;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UCLAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			DatabaseManager.generateSchema(6);
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	private boolean generateSchema(int CharityId)
//	{
//		boolean isSuccessful;
//		
//		Session session = HibernateUtil.getSessionFactory().openSession();
//	    Transaction tx = null;
//	    
//	    String DBName = "Charity" + CharityId;
//	    
//	    try{
//	         tx = session.beginTransaction();
//	         
//	         session.createSQLQuery("call spSchemaGeneration(':DB_Name')").setParameter("DB_Name", DBName);
//	                  	         
//	         tx.commit();
//	         isSuccessful = true;
//	         System.out.print("Schema generated Successfully");
//
//	      }catch (HibernateException e) {
//	         if (tx!=null) tx.rollback();
//	         e.printStackTrace();
//	         isSuccessful = false;
//	         System.out.print("Cannot generate Schema");
//	         
//	      }finally {
//	         session.close();
//	      }
//	    
//	    return isSuccessful;	
//		
//	}
	
	
	
	
	

}
