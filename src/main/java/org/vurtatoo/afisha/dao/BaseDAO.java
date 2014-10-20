package org.vurtatoo.afisha.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BaseDAO {

	@Autowired
    private SessionFactory sessionFactory;
	
    private Logger logger = LoggerFactory.getLogger(getClass());
	

    public Object save(Object entity) {
        sessionFactory.getCurrentSession().save(entity);
        return entity;
    }

    public Object saveOrUpdate(Object entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public boolean delete(Object object) {
    	try {
    		sessionFactory.getCurrentSession().delete(object);
    		return true;
    	} catch (Exception ex) {
    		logger.error(ex.getLocalizedMessage());
    		return false;
    	}   
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getEntity(Class<T> entityClass, int id) {
        return (T) sessionFactory.getCurrentSession().get(entityClass, id);
    }
    
    @SuppressWarnings("unchecked")
    public <T> List<T> getListEntity(Class<T> entityClass) {
        String query = "SELECT entity FROM " + entityClass.getCanonicalName() + " entity";
        return (List<T>) createQuery(query).list();
    }
    
    public <T> Criteria getCriteria(Class<T> entityClass) {
		return sessionFactory.getCurrentSession().createCriteria(entityClass);
	}

	public SQLQuery createSQLQuery(String queryString) {
		return sessionFactory.getCurrentSession().createSQLQuery(queryString);
	}

	public Query createQuery(String queryString) {
		return sessionFactory.getCurrentSession().createQuery(queryString);
	}
	
    @SuppressWarnings("unchecked")
    public <T> List<T> getEntitys(Class<T> entityClass) {
    	Criteria criteria = getCriteria(entityClass);
        return (List<T>) criteria.list();
    }
    
    @SuppressWarnings("unchecked")
    public <T> List<T> getEntitys(Class<T> entityClass,Criterion...criterions) {
    	Criteria criteria = getCriteria(entityClass);
    	for (Criterion criterion : criterions) {
    		criteria.add(criterion);
        }
        return (List<T>) criteria.list();
    }



}
