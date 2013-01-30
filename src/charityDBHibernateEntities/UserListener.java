package charityDBHibernateEntities;

import org.hibernate.envers.RevisionListener;
import org.jboss.seam.security.Identity;
import org.jboss.seam.Component;



public class UserListener implements RevisionListener {
	      @Override
	      public void newRevision(Object revisionEntity) {
	    	   UserRevision user = (UserRevision) revisionEntity;
	           Identity identity = (Identity) Component.getInstance("org.jboss.seam.security.identity");
	           user.setUsername(identity.getUsername());
	      }

}
