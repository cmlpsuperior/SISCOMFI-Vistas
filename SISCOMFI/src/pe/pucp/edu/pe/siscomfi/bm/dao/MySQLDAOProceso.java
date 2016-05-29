package pe.pucp.edu.pe.siscomfi.bm.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
			/*
			//pstmt.setInt(1, p.getId());
			pstmt.setString(1, p.getDescripción());
			pstmt.setDate(2, p.getFechaProceso1Inicio());
			pstmt.setDate(3, p.getFechaProceso1Fin());			
			pstmt.setDate(4, p.getFechaProceso2Inicio());
			pstmt.setDate(5, p.getFechaProceso2Fin());
			pstmt.setInt(6, p.getCantidadMinAdherentes());
			pstmt.setInt(6, p.getIdTipoProceso()); */
			
			
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
	public Proceso queryById(int idProceso) {
		// TODO Auto-generated method stub
		return null;
	}

}
