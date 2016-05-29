package pe.pucp.edu.pe.siscomfi.bm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Driver;

import pe.pucp.edu.pe.siscomfi.model.Departamento;
import pe.pucp.edu.pe.siscomfi.model.Distrito;
import pe.pucp.edu.pe.siscomfi.model.Provincia;

public class MySQLDAOUbicacion implements DAOUbicacion {

	@Override
	public ArrayList<Provincia> queryProvinciasByIdDepartamento(int idDepartamento) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Provincia> arr = new ArrayList<Provincia>();
		try {
			//Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			//Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL,
								DBConnection.user,
								DBConnection.password);
			//Paso 3: Preparar la sentencia
			String sql = "SELECT * FROM Provincia WHERE idDepartamento = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idDepartamento);
			//Paso 4: Ejecutar la sentencia
			rs = pstmt.executeQuery();
			//Paso 5(opc.): Procesar los resultados
			while (rs.next()){
				int id = rs.getInt("idProvincia");
				String nombre = rs.getString("Descripcion");
				
				Provincia p = new Provincia();
				p.setIdProvincia(id);
				p.setNombre(nombre);				
				
				arr.add(p);
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
	public ArrayList<Departamento> queryAllDepartamento() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Departamento> arr = new ArrayList<Departamento>();
		try {
			//Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			//Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL,
								DBConnection.user,
								DBConnection.password);
			//Paso 3: Preparar la sentencia
			String sql = "SELECT * FROM Departamento";
			pstmt = conn.prepareStatement(sql);
			//Paso 4: Ejecutar la sentencia
			rs = pstmt.executeQuery();
			//Paso 5(opc.): Procesar los resultados
			while (rs.next()){
				int id = rs.getInt("idDepartamento");
				String nombre = rs.getString("Descripcion");
				
				Departamento d = new Departamento();
				d.setIdDepartamento(id);
				d.setNombre(nombre);				
				
				arr.add(d);
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
	public ArrayList<Distrito> queryDistritosByIdProvincia(int idProvincia) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Distrito> arr = new ArrayList<Distrito>();
		try {
			//Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			//Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL,
								DBConnection.user,
								DBConnection.password);
			//Paso 3: Preparar la sentencia
			String sql = "SELECT * FROM Distrito WHERE idProvincia = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idProvincia);
			//Paso 4: Ejecutar la sentencia
			rs = pstmt.executeQuery();
			//Paso 5(opc.): Procesar los resultados
			while (rs.next()){
				int id = rs.getInt("idDistrito");
				String nombre = rs.getString("Descripcion");
				
				Distrito p = new Distrito();
				p.setIdDistrito(id);
				p.setNombre(nombre);				
				
				arr.add(p);
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
	

}
