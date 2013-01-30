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
		
	public static List<?> getTable(String table){
		Session session = getSession();
		Query query  = session.createQuery("from "+table);
		List<?> result = query.list();
		closeSession(session);
		return result;
	}
	
	public static Object get(Class arg0,Serializable serial){
		Session session = getSession();
		Object result = session.get(arg0, serial);
		closeSession(session);
	    return result;
	}
	
	public static Serializable transaction(String method,Object obj){
		Session session = ConnectionManager.getSession();
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
	
	private static Session getSession(){
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
	
	private static void closeSession(Session session){
		//session.close();
	}

}
