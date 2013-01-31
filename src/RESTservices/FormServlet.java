package RESTservices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import hibernateEntities.FieldType;
import hibernateEntities.FilledForm;
import hibernateEntities.Form;
import hibernateEntities.FormType;
import hibernateEntities.HibernateUtil;
import hibernateEntities.FormFields;
import hibernateEntities.FieldSelection;
/**
 * Servlet implementation class FormDataServlet
 */
@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {
	private final String REQ_STRUCTURE = "structure";
	private final String REQ_RECORDS = "records";
	private final String REQ_DELETE = "delete";
	private final String REQ_CREATE = "create";
	private class Record {
	private HashMap<String,String> colVals = new HashMap<String,String>();
	
	public HashMap<String,String> getColVals() {
		return colVals;
	}
	public void add(String key,String val) {
		colVals.put(key, val);
	}

	}
	
	private class RecordSet {
		private HashSet<String> columns = new HashSet<String>();
		private List<Record> records = new ArrayList<Record>();
		
		public HashSet<String> getColumns() {
			return columns;
		}
		public List<Record> getRecords() {
			return records;
		}
		
		public void addColumns(String... columns) {
			for(String col : columns)
				this.columns.add(col);
		}
		public void addRecords(Record... records) {
			for(Record rec : records)
				this.records.add(rec);
		}
	}
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object result = null;
		String what = request.getParameter("req");
		String formId = request.getParameter("q");
		if(what.equalsIgnoreCase(REQ_STRUCTURE))
		{
			result = getFormFields(formId);
		}
		else if(what.equalsIgnoreCase(REQ_RECORDS)) //records = data in DB
		{
			List<FormFields> fields = getFormFields(formId);
			result = processRows(getDataRows(fields),fields);
			
		}
		else if(what.equalsIgnoreCase(REQ_DELETE))
		{
			boolean b = deleteForm(formId);
			if(b) 
			{
				result = "Successfully deleted table!";
			}
			else 
				result = "An error occured while deleting!";
		}
		else if(what.equals(REQ_CREATE))
		{
			insertForm(request);
			response.sendRedirect("/CharityWare/CharityAdminServlet");
		}
		
		else result = "Error, no such request!";
	    ObjectMapper mapper = new ObjectMapper();
	    response.getWriter().write(mapper.writeValueAsString(result));
		
		
	}
	
	private void insertForm(HttpServletRequest req)
	{
		int argc = Integer.parseInt(req.getParameter("argc"));
		Form f = new Form();
		f.setFormName(req.getParameter("formname"));
		f.setIsActive(true);
	    f.setUrl("http://whatever");
	    long secondsAtInsert = Calendar.getInstance().getTimeInMillis();
	    f.setDateCreated(new java.sql.Date(secondsAtInsert));
	    f.setTimestamp(new java.sql.Timestamp(secondsAtInsert));
	    Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = null;
	      try{
	    	 f.setFormTypeId((FormType)session.get(FormType.class, new Integer(1)));
	    	 tx = session.beginTransaction();  
	    	 session.save(f);
	    	 Pattern d = Pattern.compile("(\\d+)(.*)");
	    	 	    	 for(int i = 0; i < argc; i++)
	    	 {
	    		 FormFields ff = new FormFields();
	    		 ff.setForm_id(f);
	    		 String typeInfo = req.getParameter("type_"+i);
	    		 Matcher m = d.matcher(typeInfo);
	    		 if(m.matches())
	    		 {
	    			 FieldType theType = (FieldType)session.get(FieldType.class,new Integer(m.group(1)));
	    		 	 ff.setField_type_id(theType);
	    		 	 
	    		 }
	    		 ff.setDate_created(new java.sql.Date(secondsAtInsert));
	    		 ff.setTimestamp(new java.sql.Timestamp(secondsAtInsert));
	    		 ff.setField_label(req.getParameter("name_"+i));
	    		 ff.setIsActive(true);
	    		 ff.setIsRequired(req.getParameter("isReq_"+i) != null);
	    		 
	    		 session.save(ff);
					if(m.group(2) != null && !m.group(2).isEmpty()) //there's more than just the id, there are also the values for the selection
					{
						String json = m.group(2);
						ObjectMapper mapper = new ObjectMapper();
						String[] selections = mapper.readValue(json, String[].class);
						for(int j = 0; j < selections.length; j++)
						{
							FieldSelection selection = new FieldSelection();
							selection.setField_selection_value(selections[i]);
							selection.setFormField(ff);
							selection.setTimestamp(new java.sql.Timestamp(secondsAtInsert));
							session.save(selection);
						}
					}
					

				}

				tx.commit();
			}

	      catch (HibernateException e) {
	    	  
	    	 tx.rollback();
	         e.printStackTrace(); 
	      }
	      catch (Exception e) {
	    	  tx.rollback();
	    	  e.printStackTrace();
	      }
	      finally {
	         session.close(); 
	      }
	}
	
	private boolean deleteForm(String formId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean ret = false;
		Transaction tx = null;
	      try{
	    	 tx = session.beginTransaction();  
	    	 Form killMe = (Form ) session.createCriteria(Form.class)
	                    .add(Restrictions.idEq( new Integer(formId))).uniqueResult();
	    	 session.delete(killMe);
	    	 //tx.rollback();
	    	 tx.commit();
	    	 ret = true;
	      }catch (HibernateException e) {
	    	  
	    	 tx.rollback();
	         e.printStackTrace(); 
	         ret = false;
	      }finally {
	         session.close(); 
	      }
	      return ret;
	}

	private RecordSet processRows(List<FilledForm> dataRows, List<FormFields> allFields) {
		// TODO Auto-generated method stub
		RecordSet rs = new RecordSet();
		HashSet<Integer> uniqueRecordIds = new HashSet<Integer>();
		
		if(dataRows == null || dataRows.isEmpty() || allFields == null || allFields.isEmpty())
			return rs;
		
		for(FilledForm iRow : dataRows)
		{
			uniqueRecordIds.add(iRow.getRecord_id());
		}
		for(FormFields iField : allFields)
		{
			rs.addColumns(iField.getField_label());
		}
		int i = 0;
		Record[] recordsAdd = new Record[uniqueRecordIds.size()];
		
		for(Integer iRec : uniqueRecordIds)
		{
			recordsAdd[i] = new Record();
			for(String iCol : rs.getColumns())
			{
				for(FilledForm iDataRow : dataRows)
				{
					if(iDataRow.getForm_field_id().getField_label().equalsIgnoreCase(iCol))
					{
						if(iDataRow.getRecord_id() == iRec)
						{
							recordsAdd[i].add(iCol,iDataRow.getValue());
							break;
						}
					}					
						
				}
				if(!recordsAdd[i].getColVals().containsKey(iCol))
					recordsAdd[i].getColVals().put(iCol,null);
			}
			
			i++;
		}
		
		rs.addRecords(recordsAdd);
		return rs;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		
	}
	
	private List getDataRows(List<FormFields> fields)
	{
	    List<FilledForm> records = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
	      try{
	    	 records = (List<FilledForm>)session.createCriteria(FilledForm.class).add(Restrictions.in("form_field_id", fields)).list(); 
	      }catch (HibernateException e) {
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return records;
	}
	
	private List<FormFields> getFormFields(String formId)
	{
		List<FormFields> ret = new ArrayList<FormFields>();
		Session session = HibernateUtil.getSessionFactory().openSession();
	      try{
	         Form theForm = (Form)session.get(Form.class, new Integer(formId));
	         List tForm = session.createCriteria(FormFields.class).add(Restrictions.eq("form_id", theForm)).list();	         
	         for(Object o : tForm)
	         {
	        	 ret.add((FormFields)o);
	         }  
	         
	      }catch (HibernateException e) {
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return ret;		
	}

}
