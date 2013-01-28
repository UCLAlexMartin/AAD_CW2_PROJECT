/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTdataEntities;

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
public class UsersJpaController implements Serializable {

    public UsersJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Users users) throws RollbackFailureException, Exception {
        if (users.getFeedbackEntityCollection() == null) {
            users.setFeedbackEntityCollection(new ArrayList<feedbackEntity>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            UserType userTypeId = users.getUserTypeId();
            if (userTypeId != null) {
                userTypeId = em.getReference(userTypeId.getClass(), userTypeId.getUserTypeId());
                users.setUserTypeId(userTypeId);
            }
            Collection<feedbackEntity> attachedFeedbackEntityCollection = new ArrayList<feedbackEntity>();
            for (feedbackEntity feedbackEntityCollectionfeedbackEntityToAttach : users.getFeedbackEntityCollection()) {
                feedbackEntityCollectionfeedbackEntityToAttach = em.getReference(feedbackEntityCollectionfeedbackEntityToAttach.getClass(), feedbackEntityCollectionfeedbackEntityToAttach.getFeedbackId());
                attachedFeedbackEntityCollection.add(feedbackEntityCollectionfeedbackEntityToAttach);
            }
            users.setFeedbackEntityCollection(attachedFeedbackEntityCollection);
            em.persist(users);
            if (userTypeId != null) {
                userTypeId.getUsersCollection().add(users);
                userTypeId = em.merge(userTypeId);
            }
            for (feedbackEntity feedbackEntityCollectionfeedbackEntity : users.getFeedbackEntityCollection()) {
                Users oldReviewedByOfFeedbackEntityCollectionfeedbackEntity = feedbackEntityCollectionfeedbackEntity.getReviewedBy();
                feedbackEntityCollectionfeedbackEntity.setReviewedBy(users);
                feedbackEntityCollectionfeedbackEntity = em.merge(feedbackEntityCollectionfeedbackEntity);
                if (oldReviewedByOfFeedbackEntityCollectionfeedbackEntity != null) {
                    oldReviewedByOfFeedbackEntityCollectionfeedbackEntity.getFeedbackEntityCollection().remove(feedbackEntityCollectionfeedbackEntity);
                    oldReviewedByOfFeedbackEntityCollectionfeedbackEntity = em.merge(oldReviewedByOfFeedbackEntityCollectionfeedbackEntity);
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

    public void edit(Users users) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Users persistentUsers = em.find(Users.class, users.getUserId());
            UserType userTypeIdOld = persistentUsers.getUserTypeId();
            UserType userTypeIdNew = users.getUserTypeId();
            Collection<feedbackEntity> feedbackEntityCollectionOld = persistentUsers.getFeedbackEntityCollection();
            Collection<feedbackEntity> feedbackEntityCollectionNew = users.getFeedbackEntityCollection();
            if (userTypeIdNew != null) {
                userTypeIdNew = em.getReference(userTypeIdNew.getClass(), userTypeIdNew.getUserTypeId());
                users.setUserTypeId(userTypeIdNew);
            }
            Collection<feedbackEntity> attachedFeedbackEntityCollectionNew = new ArrayList<feedbackEntity>();
            for (feedbackEntity feedbackEntityCollectionNewfeedbackEntityToAttach : feedbackEntityCollectionNew) {
                feedbackEntityCollectionNewfeedbackEntityToAttach = em.getReference(feedbackEntityCollectionNewfeedbackEntityToAttach.getClass(), feedbackEntityCollectionNewfeedbackEntityToAttach.getFeedbackId());
                attachedFeedbackEntityCollectionNew.add(feedbackEntityCollectionNewfeedbackEntityToAttach);
            }
            feedbackEntityCollectionNew = attachedFeedbackEntityCollectionNew;
            users.setFeedbackEntityCollection(feedbackEntityCollectionNew);
            users = em.merge(users);
            if (userTypeIdOld != null && !userTypeIdOld.equals(userTypeIdNew)) {
                userTypeIdOld.getUsersCollection().remove(users);
                userTypeIdOld = em.merge(userTypeIdOld);
            }
            if (userTypeIdNew != null && !userTypeIdNew.equals(userTypeIdOld)) {
                userTypeIdNew.getUsersCollection().add(users);
                userTypeIdNew = em.merge(userTypeIdNew);
            }
            for (feedbackEntity feedbackEntityCollectionOldfeedbackEntity : feedbackEntityCollectionOld) {
                if (!feedbackEntityCollectionNew.contains(feedbackEntityCollectionOldfeedbackEntity)) {
                    feedbackEntityCollectionOldfeedbackEntity.setReviewedBy(null);
                    feedbackEntityCollectionOldfeedbackEntity = em.merge(feedbackEntityCollectionOldfeedbackEntity);
                }
            }
            for (feedbackEntity feedbackEntityCollectionNewfeedbackEntity : feedbackEntityCollectionNew) {
                if (!feedbackEntityCollectionOld.contains(feedbackEntityCollectionNewfeedbackEntity)) {
                    Users oldReviewedByOfFeedbackEntityCollectionNewfeedbackEntity = feedbackEntityCollectionNewfeedbackEntity.getReviewedBy();
                    feedbackEntityCollectionNewfeedbackEntity.setReviewedBy(users);
                    feedbackEntityCollectionNewfeedbackEntity = em.merge(feedbackEntityCollectionNewfeedbackEntity);
                    if (oldReviewedByOfFeedbackEntityCollectionNewfeedbackEntity != null && !oldReviewedByOfFeedbackEntityCollectionNewfeedbackEntity.equals(users)) {
                        oldReviewedByOfFeedbackEntityCollectionNewfeedbackEntity.getFeedbackEntityCollection().remove(feedbackEntityCollectionNewfeedbackEntity);
                        oldReviewedByOfFeedbackEntityCollectionNewfeedbackEntity = em.merge(oldReviewedByOfFeedbackEntityCollectionNewfeedbackEntity);
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
                Integer id = users.getUserId();
                if (findUsers(id) == null) {
                    throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Users users;
            try {
                users = em.getReference(Users.class, id);
                users.getUserId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
            }
            UserType userTypeId = users.getUserTypeId();
            if (userTypeId != null) {
                userTypeId.getUsersCollection().remove(users);
                userTypeId = em.merge(userTypeId);
            }
            Collection<feedbackEntity> feedbackEntityCollection = users.getFeedbackEntityCollection();
            for (feedbackEntity feedbackEntityCollectionfeedbackEntity : feedbackEntityCollection) {
                feedbackEntityCollectionfeedbackEntity.setReviewedBy(null);
                feedbackEntityCollectionfeedbackEntity = em.merge(feedbackEntityCollectionfeedbackEntity);
            }
            em.remove(users);
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

    public List<Users> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<Users> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
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

    public Users findUsers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Users> rt = cq.from(Users.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
