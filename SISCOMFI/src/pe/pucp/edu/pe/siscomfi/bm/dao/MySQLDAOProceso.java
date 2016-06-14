package pe.pucp.edu.pe.siscomfi.bm.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.rowset.serial.SerialBlob;

import com.mysql.jdbc.Driver;

import pe.pucp.edu.pe.siscomfi.model.Proceso;

public class MySQLDAOProceso implements DAOProceso{
	
	@Override
	public void add (Proceso p){
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
			String sql =  "INSERT INTO Proceso "
					+ "(descripcion, fechaProceso1Inicio, fechaProceso1Fin, fechaProceso2Inicio, fechaProceso2Fin, cantidadMinAdherentes, idTipoProceso)"
					+ "VALUES (?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			//pstmt.setInt(1, p.getId());
			pstmt.setString(1, p.getDescripción());
			pstmt.setDate(2, new java.sql.Date( p.getFechaProceso1Inicio().getTime()));
			pstmt.setDate(3, new java.sql.Date( p.getFechaProceso1Fin().getTime()));			
			pstmt.setDate(4, new java.sql.Date( p.getFechaProceso2Inicio().getTime()));
			pstmt.setDate(5, new java.sql.Date( p.getFechaProceso2Fin().getTime()));
			pstmt.setInt(6, p.getCantidadMinAdherentes());
			pstmt.setInt(7, p.getIdTipoProceso()); 
			
			
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
	public void update(Proceso p) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete(int idProceso) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public ArrayList<Proceso> queryAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Proceso> arr = new ArrayList<Proceso>();
		try {
			//Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			//Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL,
								DBConnection.user,
								DBConnection.password);
			//Paso 3: Preparar la sentencia
			String sql = "SELECT * FROM Proceso";
			pstmt = conn.prepareStatement(sql);
			//Paso 4: Ejecutar la sentencia
			rs = pstmt.executeQuery();
			//Paso 5(opc.): Procesar los resultados
			while (rs.next()){
				int id = rs.getInt("idProceso");
				String descripcion = rs.getString("Descripcion");
				Date fp1Inicio = rs.getTimestamp("FechaProceso1Inicio");
				Date fp1Fin = rs.getTimestamp("FechaProceso1Fin");
				Date fp2Inicio = rs.getTimestamp("FechaProceso2Inicio");
				Date fp2Fin = rs.getTimestamp("FechaProceso2Fin");
				int cantMinAdh = rs.getInt("CantidadMinAdherentes");
				int idTipoProceso = rs.getInt("idTipoProceso");
				
				Proceso p = new Proceso();
				p.setCantidadMinAdherentes(cantMinAdh);
				p.setDescripcion(descripcion);
				p.setFechaProceso1Inicio(fp1Inicio);
				p.setFechaProceso1Fin(fp1Fin);
				p.setFechaProceso2Inicio(fp2Inicio);
				p.setFechaProceso2Fin(fp2Fin);
				p.setIdProceso(id);
				p.setIdTipoProceso(idTipoProceso);				
				
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
	public Proceso queryById(int idProceso) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
