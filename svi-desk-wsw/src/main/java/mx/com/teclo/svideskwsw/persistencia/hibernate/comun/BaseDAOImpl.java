package mx.com.teclo.svideskwsw.persistencia.hibernate.comun;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings({"rawtypes", "unchecked", "finally"})
public class BaseDAOImpl <X extends Serializable> implements BaseDAO<X>{

protected Class<? extends X> clss;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public BaseDAOImpl(){
		Type x = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) x;
		clss = (Class) pt.getActualTypeArguments()[0];
	}

	@Override
	public void clear() {
		getCurrentSession().clear();
	}

	@Override
	public void flush() {
		getCurrentSession().flush();
	}

	@Override
	public void rollback() {
		getCurrentSession().getTransaction().rollback();
	}

	@Override
	public void commit() {
		getCurrentSession().getTransaction().commit();
	}

	@Override
	public X findOne(Serializable id) {
		return (X) getCurrentSession().get(clss, id);
	}

	@Override
	public List findAll() {
		return getCurrentSession().createSQLQuery("FROM "+clss.getName()).list(); 
	}

	@Override
	public Boolean update(Serializable entity) {
		Boolean isDone = false;
		try{
			getCurrentSession().saveOrUpdate(entity);
			isDone=true;
		}catch(Exception e){ 
			e.printStackTrace();
		}finally{
			return isDone; 
		}
	}

	@Override
	public Boolean save(Serializable entity) {
		Boolean isDone = false;
		try{
			getCurrentSession().save(entity);
			isDone=true;
		}catch(Exception e){ 
			e.printStackTrace();
		}finally{
			return isDone; 
		}
	}

	@Override
	public Boolean delete(Serializable entity) {
		Boolean isDone = false;
		try{
			getCurrentSession().delete(entity);
			isDone=true;
		}catch(Exception e){ 
			e.printStackTrace();
		}finally{
			return isDone; 
		}
	}

	@Override
	public Boolean deleteById(Serializable entity) {
		Boolean isDone = false;
		try{
			X x= getCurrentSession().get(clss, entity);
			getCurrentSession().delete(x);
			isDone=true;
		}catch(Exception e){ 
			e.printStackTrace();
		}finally{
			return isDone; 
		}
	}
	
	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
}
