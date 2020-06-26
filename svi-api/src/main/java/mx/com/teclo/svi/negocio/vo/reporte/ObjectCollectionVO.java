package mx.com.teclo.svi.negocio.vo.reporte;

public class ObjectCollectionVO {

	private Object id;
	private Object description;

	public ObjectCollectionVO() {
	}

	public ObjectCollectionVO(Object id, Object description) {
		this.id = id;
		this.description = description;
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public Object getDescription() {
		return description;
	}

	public void setDescription(Object description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ObjectCollectionVO: [id:" + id + ", description:" + description + "]";
	}

}
