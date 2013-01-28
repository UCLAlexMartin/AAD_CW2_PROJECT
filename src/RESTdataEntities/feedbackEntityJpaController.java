/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTdataEntities;

import RESTdataEntities.exceptions.NonexistentEntityException;
import RESTdataEntities.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author cchen
 */
public class feedbackEntityJpaController implements Serializable {

    public feedbackEntityJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(feedbackEntity feedbackEntity) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Users reviewedBy = feedbackEntity.getReviewedBy();
            if (reviewedBy != null) {
                reviewedBy = em.getReference(reviewedBy.getClass(), reviewedBy.getUserId());
                feedbackEntity.setReviewedBy(reviewedBy);
            }
            em.persist(feedbackEntity);
            if (reviewedBy != null) {
                reviewedBy.getFeedbackEntityCollection().add(feedbackEntity);
                reviewedBy = em.merge(reviewedBy);
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

    public void edit(feedbackEntity feedbackEntity) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            feedbackEntity persistentfeedbackEntity = em.find(feedbackEntity.class, feedbackEntity.getFeedbackId());
            Users reviewedByOld = persistentfeedbackEntity.getReviewedBy();
            Users reviewedByNew = feedbackEntity.getReviewedBy();
            if (reviewedByNew != null) {
                reviewedByNew = em.getReference(reviewedByNew.getClass(), reviewedByNew.getUserId());
                feedbackEntity.setReviewedBy(reviewedByNew);
            }
            feedbackEntity = em.merge(feedbackEntity);
            if (reviewedByOld != null && !reviewedByOld.equals(reviewedByNew)) {
                reviewedByOld.getFeedbackEntityCollection().remove(feedbackEntity);
                reviewedByOld = em.merge(reviewedByOld);
            }
            if (reviewedByNew != null && !reviewedByNew.equals(reviewedByOld)) {
                reviewedByNew.getFeedbackEntityCollection().add(feedbackEntity);
                reviewedByNew = em.merge(reviewedByNew);
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
                Integer id = feedbackEntity.getFeedbackId();
                if (findfeedbackEntity(id) == null) {
                    throw new NonexistentEntityException("The feedbackEntity with id " + id + " no longer exists.");
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
            feedbackEntity feedbackEntity;
            try {
                feedbackEntity = em.getReference(feedbackEntity.class, id);
                feedbackEntity.getFeedbackId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The feedbackEntity with id " + id + " no longer exists.", enfe);
            }
            Users reviewedBy = feedbackEntity.getReviewedBy();
            if (reviewedBy != null) {
                reviewedBy.getFeedbackEntityCollection().remove(feedbackEntity);
                reviewedBy = em.merge(reviewedBy);
            }
            em.remove(feedbackEntity);
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

    public List<feedbackEntity> findfeedbackEntityEntities() {
        return findfeedbackEntityEntities(true, -1, -1);
    }

    public List<feedbackEntity> findfeedbackEntityEntities(int maxResults, int firstResult) {
        return findfeedbackEntityEntities(false, maxResults, firstResult);
    }

    private List<feedbackEntity> findfeedbackEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(feedbackEntity.class));
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

    public feedbackEntity findfeedbackEntity(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(feedbackEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getfeedbackEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<feedbackEntity> rt = cq.from(feedbackEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
