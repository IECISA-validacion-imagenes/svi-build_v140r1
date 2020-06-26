package mx.com.teclo.svideskwsw.persistencia.hibernate.comun;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO <X extends Serializable>{
	public abstract void clear();
	public abstract void flush();
	public abstract void rollback();
	public abstract void commit();
	public X findOne(final Serializable id);
	public List<X> findAll();
	public Boolean update(final X entity);
	public Boolean save(final X entity);
	public Boolean delete(final X entity);
	public Boolean deleteById(final Serializable entity);
}
