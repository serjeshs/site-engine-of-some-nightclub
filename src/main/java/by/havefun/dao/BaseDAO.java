package by.havefun.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
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
    
//    @SuppressWarnings("unchecked")
//    public <T> List<T> getListEntity(Class<T> entityClass) {
//        String query = "SELECT entity FROM " + entityClass.getCanonicalName() + " entity";
//        return (List<T>) createQuery(query).list();
//    }
//    {
//    criteria.add(Restrictions.like(string, object));
//    criteria.add(Restrictions.like(string2, object2));
//    }

    
    
    /**
     *  
     * @param entityClass 
     * @param objects FIELD_0,Value_0,FIELD_1,VALUE_1.....FIELD_N,VALUE_N
     * @return
     */
    public <T> List<T> getListEntity(Class<T> entityClass, Object...objects) {
        //ArrayList<Criterion> criterions = new ArrayList<Criterion>((objects.length % 2) +1);
        Criterion [] criterions = new Criterion[(objects.length / 2) +1];
        try {
            
            for (int i = 0; i < objects.length;) {
                String field = String.valueOf(objects[i]);
                Object value = objects[i+1];
                criterions[i/2] = Restrictions.eq(field, value);
                i +=2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<T>();
        }
        return getListEntity(entityClass, criterions);
    }
    
//    public <T> List<T> getListEntity(Class<T> entityClass, ArrayList<Criterion> criterions) {
//        return getListEntity(entityClass, VurtatooUtil.getElementData(criterions));
//    }
    
    public <T> List<T> getListEntity(Class<T> entityClass, Criterion...criterions) {
        try {
            Criteria criteria = getCriteria(entityClass);
            for (Criterion criterion : criterions) {
                if (criterion != null) {
                    criteria.add(criterion);
                }
            }
            @SuppressWarnings("unchecked")
            List<T> listEntity = (List<T>) criteria.list();
            return listEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<T>();
        }
    }
    
    
    
    /****************************************************/
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
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
    
    public <T> T getEntity(Class<T> entityClass,Criterion...criterions) {
    	List<T> listEntities = getEntitys(entityClass, criterions);
		switch (listEntities.size()) {
			case 0:
				return null;
			case 1:
				return listEntities.get(0);
			default: {
				for (Criterion c : criterions) {
					logger.warn(c.toString());
				}
				for (T t : listEntities) {
					logger.warn(t.toString());
				}
				return listEntities.get(0);
			}
		}
    }

    public <T> T getOneValue(List<T> listEntity) {
        switch (listEntity.size()) {
            case 0:
                return null;
            case 1:
                return listEntity.get(0);
            default:
                logger.warn("SIZE > 1" + Arrays.toString(listEntity.toArray()));
                return listEntity.get(0);
        }
    }


}
