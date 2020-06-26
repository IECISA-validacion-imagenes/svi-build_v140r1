package mx.com.teclo.svi.negocio.utils.comun;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConnectionUtilBd implements Serializable{

//	@Value("${spring.datasource.jndi-name}")
//	private String jndiName;
	
	@Autowired
	private DataSource dataSource;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ConnectionUtilBd.class);
	
	/**
	 * Descripción: Método para abrir conexión a la 
	 * base de datos utilizando el jndi-name que se 
	 * encuentra en application.properties 
	 * @author Jorge Luis
	 * @return Connection
	 * @throws SQLException
	 */
	public Connection conectarBD() throws SQLException,NamingException{
		try {
//			Context initContext = new InitialContext();
//			DataSource ds=(DataSource)initContext.lookup(jndiName);
			Connection con=dataSource.getConnection();
			return con;
		} catch (SQLException e) {
			logger.info("Ocurrió un error al conectar a la Base de Datos -> Message: " + e.getMessage() + " -> Causa: " + e.getCause());
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Descripción: Método para cerrar la conexión 
	 * a la base de datos 
	 * @author Jorge Luis
	 * @param Connection
	 * @throws SQLException
	 */
   public void cerrarBD(Connection cn) throws SQLException{
       if (cn != null) {
           try {
               cn.close();
           } catch (SQLException ex) {
               logger.info("Ocurrió un error al cerrar la conexión de Base de Datos -> Message: " + ex.getMessage() + " -> Causa: " + ex.getCause());
               ex.printStackTrace();
           }
       }
   }
}
