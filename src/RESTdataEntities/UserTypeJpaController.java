/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTdataEntities;

import RESTdataEntities.exceptions.IllegalOrphanException;
import RESTdataEntities.exceptions.NonexistentEntityException;
import RESTdataEntities.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author cchen
 */
public class UserTypeJpaController implements Serializable {

    public UserTypeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserType userType) throws RollbackFailureException, Exception {
        if (userType.getUsersCollection() == null) {
            userType.setUsersCollection(new ArrayList<Users>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Users> attachedUsersCollection = new ArrayList<Users>();
            for (Users usersCollectionUsersToAttach : userType.getUsersCollection()) {
                usersCollectionUsersToAttach = em.getReference(usersCollectionUsersToAttach.getClass(), usersCollectionUsersToAttach.getUserId());
                attachedUsersCollection.add(usersCollectionUsersToAttach);
            }
            userType.setUsersCollection(attachedUsersCollection);
            em.persist(userType);
            for (Users usersCollectionUsers : userType.getUsersCollection()) {
                UserType oldUserTypeIdOfUsersCollectionUsers = usersCollectionUsers.getUserTypeId();
                usersCollectionUsers.setUserTypeId(userType);
                usersCollectionUsers = em.merge(usersCollectionUsers);
                if (oldUserTypeIdOfUsersCollectionUsers != null) {
                    oldUserTypeIdOfUsersCollectionUsers.getUsersCollection().remove(usersCollectionUsers);
                    oldUserTypeIdOfUsersCollectionUsers = em.merge(oldUserTypeIdOfUsersCollectionUsers);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UserType userType) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            UserType persistentUserType = em.find(UserType.class, userType.getUserTypeId());
            Collection<Users> usersCollectionOld = persistentUserType.getUsersCollection();
            Collection<Users> usersCollectionNew = userType.getUsersCollection();
            List<String> illegalOrphanMessages = null;
            for (Users usersCollectionOldUsers : usersCollectionOld) {
                if (!usersCollectionNew.contains(usersCollectionOldUsers)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Users " + usersCollectionOldUsers + " since its userTypeId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Users> attachedUsersCollectionNew = new ArrayList<Users>();
            for (Users usersCollectionNewUsersToAttach : usersCollectionNew) {
                usersCollectionNewUsersToAttach = em.getReference(usersCollectionNewUsersToAttach.getClass(), usersCollectionNewUsersToAttach.getUserId());
                attachedUsersCollectionNew.add(usersCollectionNewUsersToAttach);
            }
            usersCollectionNew = attachedUsersCollectionNew;
            userType.setUsersCollection(usersCollectionNew);
            userType = em.merge(userType);
            for (Users usersCollectionNewUsers : usersCollectionNew) {
                if (!usersCollectionOld.contains(usersCollectionNewUsers)) {
                    UserType oldUserTypeIdOfUsersCollectionNewUsers = usersCollectionNewUsers.getUserTypeId();
                    usersCollectionNewUsers.setUserTypeId(userType);
                    usersCollectionNewUsers = em.merge(usersCollectionNewUsers);
                    if (oldUserTypeIdOfUsersCollectionNewUsers != null && !oldUserTypeIdOfUsersCollectionNewUsers.equals(userType)) {
                        oldUserTypeIdOfUsersCollectionNewUsers.getUsersCollection().remove(usersCollectionNewUsers);
                        oldUserTypeIdOfUsersCollectionNewUsers = em.merge(oldUserTypeIdOfUsersCollectionNewUsers);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = userType.getUserTypeId();
                if (findUserType(id) == null) {
                    throw new NonexistentEntityException("The userType with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            UserType userType;
            try {
                userType = em.getReference(UserType.class, id);
                userType.getUserTypeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userType with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Users> usersCollectionOrphanCheck = userType.getUsersCollection();
            for (Users usersCollectionOrphanCheckUsers : usersCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UserType (" + userType + ") cannot be destroyed since the Users " + usersCollectionOrphanCheckUsers + " in its usersCollection field has a non-nullable userTypeId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(userType);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UserType> findUserTypeEntities() {
        return findUserTypeEntities(true, -1, -1);
    }

    public List<UserType> findUserTypeEntities(int maxResults, int firstResult) {
        return findUserTypeEntities(false, maxResults, firstResult);
    }

    private List<UserType> findUserTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserType.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public UserType findUserType(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserType.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserType> rt = cq.from(UserType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
