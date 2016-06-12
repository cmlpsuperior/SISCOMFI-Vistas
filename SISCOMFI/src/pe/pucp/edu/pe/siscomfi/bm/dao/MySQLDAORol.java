package pe.pucp.edu.pe.siscomfi.bm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Driver;

import pe.pucp.edu.pe.siscomfi.model.Rol;
import pe.pucp.edu.pe.siscomfi.model.Usuario;

public class MySQLDAORol implements DAORol{

	@Override
	public ArrayList<Rol> queryAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Rol> arr = new ArrayList<Rol>();
		try {
			//Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			//Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL,
								DBConnection.user,
								DBConnection.password);
			//Paso 3: Preparar la sentencia
			String sql = "SELECT * FROM RolUsuario";
			pstmt = conn.prepareStatement(sql);
			
			//Paso 4: Ejecutar la sentencia
			rs = pstmt.executeQuery();
			
			//Paso 5(opc.): Procesar los resultados
			while (rs.next()){
				int id = rs.getInt("idRol");
				String nombre = rs.getString("Nombre");
				//String vista = rs.getString("Vista");
				
				Rol u = new Rol();
				u.setIdRol(id);
				u.setNombre(nombre);
				arr.add(u);
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
		return arr;
	}

	@Override
	public ArrayList<Rol> queryById(int idRol) {
		// TODO Auto-generated method stub
		return null;
	}

}
