package charityAdminServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import hibernateEntities.Form;
import hibernateEntities.FormFields;
import hibernateEntities.HibernateUtil;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


/**
 * Servlet implementation class FormStructureServlet
 */
@WebServlet("/FormStructureServlet")
public class FormStructureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormStructureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String formId = request.getParameter("q");
		List<FormFields> ff = getFormFields(formId);
		ObjectMapper mapper = new ObjectMapper();
		String jsonFF = mapper.writeValueAsString(ff);
		response.getWriter().print(jsonFF);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	private List<FormFields> getFormFields(String formId)
	{
		List<FormFields> ret = new ArrayList<FormFields>();
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         List tForm = session.createQuery("FROM FormFields as ff WHERE ff.form_id="+formId).list();
	         for(Object o : tForm)
	         {
	        	 ret.add((FormFields)o);
	         }
	         tx.commit();
	        
	         
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return ret;		
	}
	

}
