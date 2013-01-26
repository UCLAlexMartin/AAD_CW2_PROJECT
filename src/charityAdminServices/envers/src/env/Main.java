package env;





import java.util.ArrayList; 
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;

import env.Entities.FilledForm;
import env.Entities.User;
import env.Entities.UserType;

 
public class Main {

	private static SessionFactory factory;


		public static void main(String[] args) {
			Configuration conf = new Configuration();
			conf.configure();
			factory = conf.buildSessionFactory();
			Main m = new Main();
//			Integer id = m.addUserSample("Zahariel","El'Zore");
//			m.updateUserPassword(id, "El'Zurias");
//			System.out.println(id);
		    Session session = factory.openSession();
			AuditReader reader = AuditReaderFactory.get(session);

/*		   Get all users at revision 3
		   List<?> users =reader.createQuery()
				    .forEntitiesAtRevision(User.class, 3)
				    .addOrder(AuditEntity.property("userName").desc())
				    .getResultList();
		   */
			
			
			// Get all the user records modified in all revisions after revision 1
			/*
			List<?> list = reader.createQuery()
				    .forRevisionsOfEntity(User.class, false, true)
				    .add(AuditEntity.revisionType().eq(RevisionType.MOD))
				    .add(AuditEntity.revisionNumber().gt(1))
				    .getResultList()
				    ;
			*/
			
			/*
			 * Get the date of a given revision
			 */
			Date date = reader.getRevisionDate(5);
			System.out.println(date.toString());
			
			
			/*
			List<Number> revisionNumbers = reader.getRevisions(UserRevision.class, id);
			ArrayList<?> revisionUsernames = new ArrayList();
			for (Number number : revisionNumbers) {
			     UserRevision revision = reader.find(UserRevision.class, 8, 4);
			     PectopahRevisionEntity pre = auditReader.findRevision(PectopahRevisionEntity.class, number);
			     revisionUsernames.add(pre.getUsername());
			 }*/

			Integer id=m.addUserSample("George", "Gregory");
			System.out.println("I just created a user with userId"+id);
			m.updateUserPassword(id, "Corswain");
		}
		
		
	
		
		public Integer addUserSample (String name,String pass) {
		    Session session = factory.openSession();
		    Transaction tx = null;
		    Integer userId = null;
		    try{
		    tx = session.beginTransaction();
		    User user = new User (name,pass);
		    userId = (Integer) session.save(user);
		    tx.commit();
		    }catch(HibernateException hx) {
		    	if (tx!=null) {
		    		tx.rollback();
		    	}
		    	hx.printStackTrace();
		    }finally{
		    	session.close();
		    }
			
			return userId;
		}
		
		public void updateUserPassword (Integer userId,String userPassword ) {
			Session session = factory.openSession();
			Transaction tx = null;
			try {
				tx=session.beginTransaction();
				User user = (User) session.get(User.class, userId);
				user.setUserPassword(userPassword);
				session.update(user);
				tx.commit();
			}catch(HibernateException hx) {
				if (tx!=null) {
					tx.rollback();
				}
				hx.printStackTrace();
			}finally{
				session.close();
			}
		}
		
}
