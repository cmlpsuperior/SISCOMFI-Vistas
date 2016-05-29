package pe.pucp.edu.pe.siscomfi.bm.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import com.mysql.jdbc.Driver;

import pe.pucp.edu.pe.siscomfi.model.Usuario;

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
					+ "(nombre, apellidoPaterno, apellidoMaterno,   correoElectronico, contrasenia, fechaRegistro,   dni, idRol)"
					+ "VALUES (?,?,?,  ?,'12345',Now(),  ?,?)";
			pstmt = conn.prepareStatement(sql);
			
			//pstmt.setInt(1, p.getId());
			pstmt.setString(1, u.getNombre());
			pstmt.setString(2, u.getApellidoPaterno());
			pstmt.setString(3, u.getApellidoPaterno());
			
			pstmt.setString(4, u.getCorreoElectronico());
						
			pstmt.setString(5, u.getDni());
			pstmt.setInt(6, u.getIdRol());
			
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

	@Override
	public void update(Usuario u) {
		// TODO Auto-generated method stub
		
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

}
