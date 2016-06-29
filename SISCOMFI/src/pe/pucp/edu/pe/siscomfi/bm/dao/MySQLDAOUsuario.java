package pe.pucp.edu.pe.siscomfi.bm.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.rowset.serial.SerialBlob;

import com.mysql.jdbc.Driver;

import pe.pucp.edu.pe.siscomfi.model.Usuario;
import pe.pucp.edu.pe.siscomfi.model.UsuarioLogeado;

public class MySQLDAOUsuario implements DAOUsuario {

	@Override
	public void add(Usuario u) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;		
		try {
			//Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			
			//Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL,
								DBConnection.user,
								DBConnection.password);
			
			//Paso 3: Preparar la sentencia
			String sql =  "INSERT INTO Usuario "
					+ " (Nombre, ApellidoPaterno, ApellidoMaterno, CorreoElectronico, Contrasenia, fechaRegistro, DNI, idRol, "
					+ "PreguntaSecreta, RptaSecreta) "
					+ " VALUES (?,?,?,?,'12345',Now(), ?, ?, 'Pregunta por defecto', '12345') ";
			pstmt = conn.prepareStatement(sql);
			
			//pstmt.setInt(1, p.getId());
			pstmt.setString(1, u.getNombre());
			pstmt.setString(2, u.getApellidoPaterno());
			pstmt.setString(3, u.getApellidoMaterno());			
			pstmt.setString(4, u.getCorreoElectronico());
			/*contraseña ni fecha de registro se setean*/			
			pstmt.setString(5, u.getDni());
			pstmt.setInt(6, u.getIdRol());
			//pstmt.setString(7, u.getPreguntaSecreta());//nuevo
			//pstmt.setString(8, u.getRptaSecreta());//nuevo
			
			//Paso 4: Ejecutar la sentencia
			pstmt.executeUpdate();
			//Paso 5(opc.): Procesar los resultados			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//Paso 6(OJO): Cerrar la conexión
			try { if (pstmt!= null) pstmt.close();} 
				catch (Exception e){e.printStackTrace();};
			try { if (conn!= null) conn.close();} 
				catch (Exception e){e.printStackTrace();};						
		}
		
	}
	
	//@Override
		public boolean queryByLogin(String nombreCorreo, String pass) {
						
			Connection conn = null;
			PreparedStatement pstmt = null;		
			ResultSet rs = null;
			try {
				//Paso 1: Registrar el Driver
				DriverManager.registerDriver(new Driver());
				
				//Paso 2: Obtener la conexión
				conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL,
									DBConnection.user,
									DBConnection.password);
				
				//Paso 3: Preparar la sentencia
				String sql =  "SELECT * FROM Usuario WHERE correoElectronico = ? AND contrasenia = ? ";
				pstmt = conn.prepareStatement(sql);
				
				//pstmt.setInt(1, p.getId());
				pstmt.setString(1, nombreCorreo);
				pstmt.setString(2, pass);
				
				//Paso 4: Ejecutar la sentencia
				rs = pstmt.executeQuery();
				//Paso 5(opc.): Procesar los resultados		
				if (rs.next() == true)   
					return true;
				else 
					return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				//Paso 6(OJO): Cerrar la conexión
				try { if (pstmt!= null) pstmt.close();} 
					catch (Exception e){e.printStackTrace();};
				try { if (conn!= null) conn.close();} 
					catch (Exception e){e.printStackTrace();};						
			}
			return false;
		}
	
		public boolean queryByLoginAdmin(String nombreCorreo, String pass) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;		
			ResultSet rs = null;
			try {
				//Paso 1: Registrar el Driver
				DriverManager.registerDriver(new Driver());
				
				//Paso 2: Obtener la conexión
				conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL,
									DBConnection.user,
									DBConnection.password);
				
				//Paso 3: Preparar la sentencia
				String sql =  "SELECT * FROM Usuario WHERE correoElectronico = ? AND contrasenia = ? AND idRol = 1";
				pstmt = conn.prepareStatement(sql);
				
				//pstmt.setInt(1, p.getId());
				pstmt.setString(1, nombreCorreo);
				pstmt.setString(2, pass);
				
				//Paso 4: Ejecutar la sentencia
				rs = pstmt.executeQuery();
				//Paso 5(opc.): Procesar los resultados		
				if (rs.next() == true)   
					return true;
				else 
					return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				//Paso 6(OJO): Cerrar la conexión
				try { if (pstmt!= null) pstmt.close();} 
					catch (Exception e){e.printStackTrace();};
				try { if (conn!= null) conn.close();} 
					catch (Exception e){e.printStackTrace();};						
			}
			return false;
		}

	@Override
	public void update(Usuario u) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexiï¿½n
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentencia
			String sql = "UPDATE Usuario "
					+ " SET Contrasenia = ?, preguntaSecreta = ?, RptaSecreta = ?"
					+ "WHERE CorreoElectronico = ? and Contrasenia =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getContrasenia());
			pstmt.setString(2, u.getPreguntaSecreta());
			pstmt.setString(3, u.getRptaSecreta());
			pstmt.setString(4, u.getCorreoElectronico());
			pstmt.setString(5, u.getAntContrasenia());
			// Paso 4: Ejecutar la sentencia
			pstmt.executeUpdate();
			// Paso 5(opc.): Procesar los resultados
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Paso 6(OJO): Cerrar la conexiï¿½n
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void delete(int idUsuario) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Usuario queryById(int idUsuario) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String queryByUsuario(String correo) {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		ResultSet rs = null;
		String pass = "";
		try {
			//Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			
			//Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL,
								DBConnection.user,
								DBConnection.password);
			
			//Paso 3: Preparar la sentencia
			String sql =  "SELECT * FROM Usuario WHERE correoElectronico = ?";
			pstmt = conn.prepareStatement(sql);
			
			//pstmt.setInt(1, p.getId());
			pstmt.setString(1, correo);			
			//Paso 4: Ejecutar la sentencia
			rs = pstmt.executeQuery();
			//Paso 5(opc.): Procesar los resultados		
			if (rs.next() == true)  {
				pass = rs.getString("contrasenia");
				return pass;
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//Paso 6(OJO): Cerrar la conexión
			try { if (pstmt!= null) pstmt.close();} 
				catch (Exception e){e.printStackTrace();};
			try { if (conn!= null) conn.close();} 
				catch (Exception e){e.printStackTrace();};						
		}
		return pass;
	}
	
	@Override
	public String queryByCorreo_RptaSecreta(String correo, String rptaSecreta) {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		ResultSet rs = null;
		String pass = "";
		try {
			//Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			
			//Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL,
								DBConnection.user,
								DBConnection.password);
			
			//Paso 3: Preparar la sentencia
			String sql =  "SELECT * FROM Usuario WHERE correoElectronico = ? AND rptaSecreta= ?";
			pstmt = conn.prepareStatement(sql);
			
			//pstmt.setInt(1, p.getId());
			pstmt.setString(1, correo);
			pstmt.setString(2, rptaSecreta);			
			//Paso 4: Ejecutar la sentencia
			rs = pstmt.executeQuery();
			//Paso 5(opc.): Procesar los resultados		
			if (rs.next() == true)  {
				pass = rs.getString("contrasenia");
				return pass;
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//Paso 6(OJO): Cerrar la conexión
			try { if (pstmt!= null) pstmt.close();} 
				catch (Exception e){e.printStackTrace();};
			try { if (conn!= null) conn.close();} 
				catch (Exception e){e.printStackTrace();};						
		}
		return pass;
	}

	@Override
	public Usuario queryByCorreo(String correo) {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		ResultSet rs = null;
		Usuario usuario = null;
		try {
			//Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			
			//Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL,
								DBConnection.user,
								DBConnection.password);
			
			//Paso 3: Preparar la sentencia
			String sql =  "SELECT * FROM Usuario WHERE correoElectronico = ?";
			pstmt = conn.prepareStatement(sql);
			
			//pstmt.setInt(1, p.getId());
			pstmt.setString(1, correo);			
			//Paso 4: Ejecutar la sentencia
			rs = pstmt.executeQuery();
			//Paso 5(opc.): Procesar los resultados		
			if (rs.next() == true)  {
				int id = rs.getInt("idUsuario");
				String login = rs.getString("CorreoElectronico");
				String pass = rs.getString("contrasenia");
				int rol = rs.getInt("idRol");
				Usuario usr = new Usuario(id,"","","",login,pass,new Date(),"",rol);
				
				return usr;
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//Paso 6(OJO): Cerrar la conexión
			try { if (pstmt!= null) pstmt.close();} 
				catch (Exception e){e.printStackTrace();};
			try { if (conn!= null) conn.close();} 
				catch (Exception e){e.printStackTrace();};						
		}
		return usuario;
	}

	@Override
	public String queryRecuperarContrasenia(String correo) {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		ResultSet rs = null;
		String pass = "";
		try {
			//Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			
			//Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL,
								DBConnection.user,
								DBConnection.password);
			
			//Paso 3: Preparar la sentencia
			String sql =  "SELECT * FROM Usuario WHERE correoElectronico = ?";
			pstmt = conn.prepareStatement(sql);
			
			//pstmt.setInt(1, p.getId());
			pstmt.setString(1, correo);			
			//Paso 4: Ejecutar la sentencia
			rs = pstmt.executeQuery();
			//Paso 5(opc.): Procesar los resultados		
			if (rs.next() == true)  {
				pass = rs.getString("contrasenia");
				return pass;
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//Paso 6(OJO): Cerrar la conexión
			try { if (pstmt!= null) pstmt.close();} 
				catch (Exception e){e.printStackTrace();};
			try { if (conn!= null) conn.close();} 
				catch (Exception e){e.printStackTrace();};						
		}
		return pass;
	}
	

}
