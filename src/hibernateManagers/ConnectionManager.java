package hibernateManagers;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ConnectionManager {
	private static SessionFactory factory;
		
	public static List<?> charityGetTable(String table){
		Session session = getCharitySession();
		Query query  = session.createQuery("from "+table);
		List<?> result = query.list();
		closeSession(session);
		return result;
	}
	
	public static Object charityGet(Class arg0,Serializable serial){
		Session session = getCharitySession();
		Object result = session.get(arg0, serial);
		closeSession(session);
	    return result;
	}
	
	public static Serializable charityTransaction(String method,Object obj){
		Session session = ConnectionManager.getCharitySession();
		Transaction tx = null;
		Serializable serial = null;
		try{
	    	tx = session.beginTransaction();
	    	serial = (Serializable)Session.class.getMethod(method,Object.class).invoke(session,obj);
	    	tx.commit();
	    }catch(HibernateException hx) {
	    	if (tx!=null) {
	    		tx.rollback();
	    	}
	    	hx.printStackTrace();
	    }catch(IllegalAccessException e){
	    	e.printStackTrace();
	    }catch(IllegalArgumentException e){
	    	e.printStackTrace();
	    }catch(InvocationTargetException e){
	    	e.printStackTrace();
	    }catch(NoSuchMethodException e){
	    	e.printStackTrace();
	    }catch(SecurityException e){
	    	e.printStackTrace();
	    }finally{
	    	closeSession(session);
	    }
		return serial;
	}
	
	
	public static List<?> systemGetTable(String table){
		System.out.println("systemGetTable started");
		Session session = getSystemDBSession();
		System.out.println("Session established");
		Query query  = session.createQuery("from "+table);
		System.out.println("query run");
		List<?> result = query.list();
		System.out.println("query result list populated");
		closeSession(session);
		System.out.println("session closed");
		return result;
	}
	
	public static Object systemGet(Class arg0,Serializable serial){
		Session session = getSystemDBSession();
		Object result = session.get(arg0, serial);
		closeSession(session);
	    return result;
	}
	
	public static Serializable systemTransaction(String method,Object obj){
		Session session = ConnectionManager.getSystemDBSession();
		Transaction tx = null;
		Serializable serial = null;
		try{
	    	tx = session.beginTransaction();
	    	serial = (Serializable)Session.class.getMethod(method,Object.class).invoke(session,obj);
	    	tx.commit();
	    }catch(HibernateException hx) {
	    	if (tx!=null) {
	    		tx.rollback();
	    	}
	    	hx.printStackTrace();
	    }catch(IllegalAccessException e){
	    	e.printStackTrace();
	    }catch(IllegalArgumentException e){
	    	e.printStackTrace();
	    }catch(InvocationTargetException e){
	    	e.printStackTrace();
	    }catch(NoSuchMethodException e){
	    	e.printStackTrace();
	    }catch(SecurityException e){
	    	e.printStackTrace();
	    }finally{
	    	closeSession(session);
	    }
		return serial;
	}
	
	
	private static Session getCharitySession(){
		if (factory ==null){
			Configuration conf = new Configuration();
			conf.configure("/hibernateEntities/hibernate.cfg.xml");
			factory = conf.buildSessionFactory();
			return factory.openSession();
		}
		Session result;
		try{
			result = factory.getCurrentSession();
		}catch(org.hibernate.HibernateException e){
			result = factory.openSession();
		}
		return result;
	}
	
	private static Session getSystemDBSession(){
		System.out.println("systemGetTable begin");
		if (factory ==null){
			System.out.println("factory==null");
			Configuration conf = new Configuration();
			conf.configure("/systemDBHibernateEntities/hibernate.cfg.xml");
			System.out.println("configuration path set");
			factory = conf.buildSessionFactory();
			System.out.println("built session factory");
			return factory.openSession();
		}
		Session result;
		try{
			result = factory.getCurrentSession();
			System.out.println("get current session");
		}catch(org.hibernate.HibernateException e){
			result = factory.openSession();
			System.out.println("open a session");
		}
		return result;
	}
	
	private static void closeSession(Session session){
		//session.close();
	}

}
