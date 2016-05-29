package pe.pucp.edu.pe.siscomfi.bm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Driver;

import pe.pucp.edu.pe.siscomfi.model.TipoProceso;
import pe.pucp.edu.pe.siscomfi.model.Proceso;

public class MySQLDAOTipoProceso implements DAOTipoProceso{

	@Override
	public ArrayList<TipoProceso> queryAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<TipoProceso> arr = new ArrayList<TipoProceso>();
		try {
			//Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			//Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL,
								DBConnection.user,
								DBConnection.password);
			//Paso 3: Preparar la sentencia
			String sql = "SELECT * FROM TipoProceso";
			pstmt = conn.prepareStatement(sql);
			
			//Paso 4: Ejecutar la sentencia
			rs = pstmt.executeQuery();
			
			//Paso 5(opc.): Procesar los resultados
			while (rs.next()){
				int id = rs.getInt("idTipoProceso");
				String nombre = rs.getString("Nombre");
				
				TipoProceso tp = new TipoProceso();
				tp.setIdTipoProceso(id);
				tp.setNombre(nombre);
				arr.add(tp);
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
	public ArrayList<TipoProceso> queryById(int idTipoProceso) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
