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

import pe.pucp.edu.pe.siscomfi.model.Planillon;
import pe.pucp.edu.pe.siscomfi.model.Proceso;

public class MySQLDAOProceso implements DAOProceso {

	@Override
	public void add(Proceso p) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentencia
			String sql = "INSERT INTO Proceso "
					+ "(descripcion, fechaProceso1Inicio, fechaProceso1Fin, fechaProceso2Inicio, fechaProceso2Fin, cantidadMinAdherentes, idTipoProceso)"
					+ "VALUES (?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			// pstmt.setInt(1, p.getId());
			pstmt.setString(1, p.getDescripcion());
			pstmt.setDate(2, new java.sql.Date(p.getFechaProceso1Inicio().getTime()));
			pstmt.setDate(3, new java.sql.Date(p.getFechaProceso1Fin().getTime()));
			pstmt.setDate(4, new java.sql.Date(p.getFechaProceso2Inicio().getTime()));
			pstmt.setDate(5, new java.sql.Date(p.getFechaProceso2Fin().getTime()));
			pstmt.setInt(6, p.getCantidadMinAdherentes());
			pstmt.setInt(7, p.getIdTipoProceso());
			// Paso 4: Ejecutar la sentencia
			pstmt.executeUpdate();
			// Paso 5(opc.): Procesar los resultados
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Paso 6(OJO): Cerrar la conexión
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
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentencia
			String sql = "SELECT * FROM Proceso";
			pstmt = conn.prepareStatement(sql);
			// Paso 4: Ejecutar la sentencia
			rs = pstmt.executeQuery();
			// Paso 5(opc.): Procesar los resultados
			while (rs.next()) {
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
			// Paso 6(OJO): Cerrar la conexión
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
		return arr;
	}

	@Override
	public Proceso queryById(int idProceso) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Proceso getFase1Actual() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Proceso proceso = null;
		try {
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentencia
			String sql = "SELECT p.* FROM Proceso p WHERE (Now() between p.FechaProceso1Inicio and p.FechaProceso1Fin)";
			pstmt = conn.prepareStatement(sql);
			// Paso 4: Ejecutar la sentencia
			rs = pstmt.executeQuery();
			// Paso 5(opc.): Procesar los resultados
			if (rs.next()) {
				int id = rs.getInt("idProceso");
				String descripcion = rs.getString("Descripcion");
				Date fp1Inicio = rs.getTimestamp("FechaProceso1Inicio");
				Date fp1Fin = rs.getTimestamp("FechaProceso1Fin");
				Date fp2Inicio = rs.getTimestamp("FechaProceso2Inicio");
				Date fp2Fin = rs.getTimestamp("FechaProceso2Fin");
				int cantMinAdh = rs.getInt("CantidadMinAdherentes");
				int idTipoProceso = rs.getInt("idTipoProceso");

				proceso = new Proceso();
				proceso.setCantidadMinAdherentes(cantMinAdh);
				proceso.setDescripcion(descripcion);
				proceso.setFechaProceso1Inicio(fp1Inicio);
				proceso.setFechaProceso1Fin(fp1Fin);
				proceso.setFechaProceso2Inicio(fp2Inicio);
				proceso.setFechaProceso2Fin(fp2Fin);
				proceso.setIdProceso(id);
				proceso.setIdTipoProceso(idTipoProceso);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Paso 6(OJO): Cerrar la conexión
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
		return proceso;
	}

	@Override
	public Proceso getFase2Actual() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Proceso proceso = null;
		try {
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentencia
			String sql = "SELECT p.* FROM Proceso p WHERE (Now() between p.FechaProceso2Inicio and p.FechaProceso2Fin)";
			pstmt = conn.prepareStatement(sql);
			// Paso 4: Ejecutar la sentencia
			rs = pstmt.executeQuery();
			// Paso 5(opc.): Procesar los resultados
			if (rs.next()) {
				int id = rs.getInt("idProceso");
				String descripcion = rs.getString("Descripcion");
				Date fp1Inicio = rs.getTimestamp("FechaProceso1Inicio");
				Date fp1Fin = rs.getTimestamp("FechaProceso1Fin");
				Date fp2Inicio = rs.getTimestamp("FechaProceso2Inicio");
				Date fp2Fin = rs.getTimestamp("FechaProceso2Fin");
				int cantMinAdh = rs.getInt("CantidadMinAdherentes");
				int idTipoProceso = rs.getInt("idTipoProceso");

				proceso = new Proceso();
				proceso.setCantidadMinAdherentes(cantMinAdh);
				proceso.setDescripcion(descripcion);
				proceso.setFechaProceso1Inicio(fp1Inicio);
				proceso.setFechaProceso1Fin(fp1Fin);
				proceso.setFechaProceso2Inicio(fp2Inicio);
				proceso.setFechaProceso2Fin(fp2Fin);
				proceso.setIdProceso(id);
				proceso.setIdTipoProceso(idTipoProceso);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Paso 6(OJO): Cerrar la conexión
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
		return proceso;
	}

	@Override
	public int addPlanillon(Planillon p) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int id = 0;
		try {
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentencia
			String sql = "INSERT INTO Planillon " + "(TiempoProcesado, idPartidoPolitico, idProceso)"
					+ "VALUES (?,?,?)";
			pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, p.getIdPartido());
			pstmt.setInt(3, p.getIdProceso());
			// Paso 4: Ejecutar la sentencia
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Paso 6(OJO): Cerrar la conexión
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
		return id;
	}

	@Override
	public void addAdherentexPlanillon(int idAdherente, int idPlanillon, int estado, double tProcesado, double pHuella,
			double pFirma, String huella, String firma) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentencia
			String sql = "INSERT INTO AdherentexPlanillon "
					+ "(idPlanillon, idAdherente, EstadoValidez, TiempoProcesado, PorcentajeHuella, PorcentajeFirma, Huella,Firma)"
					+ "VALUES (?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idPlanillon);
			pstmt.setInt(2, idAdherente);
			pstmt.setString(3, ""+estado);
			pstmt.setDouble(4, tProcesado);
			pstmt.setDouble(5, pHuella);
			pstmt.setDouble(6, pFirma);
			pstmt.setString(7, huella);
			pstmt.setString(8, firma);
			// Paso 4: Ejecutar la sentencia
			pstmt.executeUpdate();
			// Paso 5(opc.): Procesar los resultados
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Paso 6(OJO): Cerrar la conexión
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
	public void addPartidoxProceso(int idPartido, int idProceso, int idUsuario, double tiempoProcesado, int estado) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentencia
			String sql = "INSERT INTO PartidoPoliticoxProceso "
					+ "(idPartidoPolitico, idProceso, idUsuario, TiempoProcesado, EstadoPartido)"
					+ "VALUES (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idPartido);
			pstmt.setInt(2, idProceso);
			pstmt.setInt(3, idUsuario);
			pstmt.setDouble(4, tiempoProcesado);
			pstmt.setString(5, ""+estado);
			// Paso 4: Ejecutar la sentencia
			pstmt.executeUpdate();
			// Paso 5(opc.): Procesar los resultados
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Paso 6(OJO): Cerrar la conexión
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

}
