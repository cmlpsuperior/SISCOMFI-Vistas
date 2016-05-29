package pe.pucp.edu.pe.siscomfi.bm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Driver;

import pe.pucp.edu.pe.siscomfi.model.PartidoPolitico;

public class MySQLDAOPartidoPolitico implements DAOPartidoPolitico {

	@Override
	public void add(PartidoPolitico p) {
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
			String sql =  "INSERT INTO PartidoPolitico "
					+ "(Nombre, Representante, CorreoRepresentante,    Direccion, Telefono, IdDistrito,    FechaRegistro, EstadoActivo) "
					+ "VALUES (?,?,?,  ?,?,?, ?, 'A')";
			pstmt = conn.prepareStatement(sql);
			
			//pstmt.setInt(1, p.getId());
			pstmt.setString(1, p.getNombrePartido());
			pstmt.setString(2, p.getRepresentante());
			pstmt.setString(3, p.getCorreo());
			
			pstmt.setString(4, p.getDireccion());						
			pstmt.setString(5, p.getTelefono());
			pstmt.setInt(6, p.getIdDistrito());
			
			pstmt.setDate(7, new java.sql.Date(p.getFechaRegistro().getTime()));
			
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
	public void update(PartidoPolitico p) {
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
			String sql = "UPDATE PartidoPolitico "
					+ " SET Representante=?, CorreoRepresentante=?, Direccion=?, Telefono = ? , EstadoActivo = 'M'"
					+ "WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			//
			pstmt.setString(1, p.getRepresentante());
			pstmt.setString(2, p.getCorreo());
			pstmt.setString(3, p.getDireccion());
			pstmt.setString(4, p.getTelefono());
			pstmt.setInt(5, p.getIdPartidoPolitco());
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
	public void delete(int idPartido) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<PartidoPolitico> queryAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PartidoPolitico> arr = new ArrayList<PartidoPolitico>();
		try {
			//Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			//Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL,
								DBConnection.user,
								DBConnection.password);
			//Paso 3: Preparar la sentencia
			String sql = "SELECT * FROM PartidoPolitico";
			pstmt = conn.prepareStatement(sql);
			//Paso 4: Ejecutar la sentencia
			rs = pstmt.executeQuery();
			//Paso 5(opc.): Procesar los resultados
			while (rs.next()){
				int id = rs.getInt("idPartidoPolitico");
				String nombre = rs.getString("Nombre");
				String rep = rs.getString("Representante");
				String correo = rs.getString("CorreoRepresentante");
				String direccion = rs.getString("Direccion");
				String telefono = rs.getString("Telefono");
				int idDistrito = rs.getInt("idDistrito");
				Date fechaRegistro = rs.getTimestamp("FechaRegistro");
				
				PartidoPolitico p = new PartidoPolitico();
				p.setIdPartidoPolitco(id);
				p.setNombrePartido(nombre);
				p.setRepresentante(rep);
				p.setCorreo(correo);
				p.setDireccion(direccion);
				p.setTelefono(telefono);
				p.setIdDistrito(idDistrito);
				p.setFechaRegistro(fechaRegistro);
				
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
	public PartidoPolitico queryById(int idPartido) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

