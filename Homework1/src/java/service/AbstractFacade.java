/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import model.entities.Consola;
import model.entities.Joc;
import model.entities.Topic;

/**
 *
 * @author deim
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
    public T findByUsername(String username) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.where(cb.equal(root.get("nomUsuari"), username));
        TypedQuery<T> query = em.createQuery(cq);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public List<Joc> findByTypeOrConsole(String type, String console) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Joc> cq = cb.createQuery(Joc.class);
        Root<Joc> jocsRoot = cq.from(Joc.class);

        if (type != null && console == null) {
            Join<Joc, Topic> topicJoin = jocsRoot.join("topic", JoinType.INNER);
            Predicate condition = cb.equal(topicJoin.get("name"), type);
            cq.where(condition);
            
        } else if (type == null && console != null) {
            Join<Joc, Consola> ConsoleJoin = jocsRoot.join("consola", JoinType.INNER);
            Predicate condition = cb.equal(ConsoleJoin.get("name"), console);
            cq.where(condition);
            
        } else if (type != null && console != null) {
            Join<Joc, Topic> topicJoin = jocsRoot.join("topic", JoinType.INNER);
            Join<Joc, Consola> ConsoleJoin = jocsRoot.join("consola", JoinType.INNER);

            Predicate condition1 = cb.equal(topicJoin.get("name"), type);
            Predicate condition2 = cb.equal(ConsoleJoin.get("name"), console);

            Predicate conditionFinal = cb.and(condition1, condition2);
            cq.where(conditionFinal);
        }

        cq.orderBy(cb.asc(jocsRoot.get("nom")));
        TypedQuery<Joc> query = getEntityManager().createQuery(cq);

        return query.getResultList();
    }

     
    public List<T> findAll() {
        jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        jakarta.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
    

    public int count() {
        jakarta.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        jakarta.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        jakarta.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
