package RESTservices;

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

import charityDBHibernateEntities.*;


/**
 * Servlet implementation class UCLAdminServlet
 */
@WebServlet("/UCLAdminServlet")
public class UCLAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			generateSchema(1);
			
		} 
		catch (Exception e) {
				
		}
	}
	
	private boolean generateSchema(int CharityId)
	{
		boolean isSuccessful;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = null;
	    
	    String DBName = "Charity" + CharityId;
	    
	    try{
	         tx = session.beginTransaction();
	         
	         session.createSQLQuery("call spSchemaGeneration(':DB_Name')").setParameter("DB_Name", DBName);
	                  	         
	         tx.commit();
	         isSuccessful = true;
	         System.out.print("Schema generated Successfully");

	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace();
	         isSuccessful = false;
	         System.out.print("Cannot generate Schema");
	         
	      }finally {
	         session.close();
	      }
	    
	    return isSuccessful;	
		
	}
	
	
	
	
	

}
