package pe.pucp.edu.pe.siscomfi.bm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Driver;

import pe.pucp.edu.pe.siscomfi.model.Adherente;
import pe.pucp.edu.pe.siscomfi.model.PartidoPolitico;
import pe.pucp.edu.pe.siscomfi.model.Rol;

public class MySQLDAOAdherente implements DAOAdherente {

	@Override
	public int add(Adherente a) {
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
			String sql = "INSERT INTO Adherente "
					+ "(Nombre, ApellidoPaterno, ApellidoMaterno, DNI, FechaNacimiento, idDistrito)"
					+ "VALUES (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			// pstmt.setInt(1, p.getId());
			pstmt.setString(1, a.getNombre());
			pstmt.setString(2, a.getAppPaterno());
			pstmt.setString(3, a.getAppMaterno());
			pstmt.setString(4, a.getDni());
			pstmt.setDate(5, new java.sql.Date(a.getFechaNacimiento().getTime()));
			pstmt.setInt(6, a.getIdDistrito());

			// Paso 4: Ejecutar la sentencia
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
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
		return id;
	}

	@Override
	public void update(Adherente a) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentencia
			String sql = "UPDATE Adherente "
					+ " SET Nombre =?, ApellidoPaterno=?, ApellidoMaterno=?, DNI = ? , FechaNacimiento = ?, idDistrito = ?"
					+ "WHERE idAdherente = ?";
			pstmt = conn.prepareStatement(sql);
			//
			pstmt.setString(1, a.getNombre());
			pstmt.setString(2, a.getAppPaterno());
			pstmt.setString(3, a.getAppMaterno());
			pstmt.setString(4, a.getDni());
			pstmt.setDate(5, new java.sql.Date(a.getFechaNacimiento().getTime()));
			pstmt.setInt(6, a.getIdDistrito());
			// Paso 4: Ejecutar la sentencia
			pstmt.executeUpdate();
			// Paso 5(opc.): Procesar los resultados
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
	}

	@Override
	public void delete(int idAdherente) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentencia (1=ACEPTADO, 0=RECHAZADO,
			// 2=OBSERVADO)
			String sql = "UPDATE AdherentexPlanillon SET EstadoValidez = '0' " + "WHERE idPartidoPolitico=?";
			pstmt = conn.prepareStatement(sql);
			//
			pstmt.setInt(1, idAdherente);
			// Paso 4: Ejecutar la sentencia
			pstmt.executeUpdate();
			// Paso 5(opc.): Procesar los resultados
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
			;
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			;
		}
	}

	@Override
	public void delete_OBS(int idAdherente) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentencia (1=ACEPTADO, 0=RECHAZADO,
			// 2=OBSERVADO)
			String sql = "UPDATE AdherentexPlanillon SET EstadoValidez = '2' " + "WHERE idPartidoPolitico=?";
			pstmt = conn.prepareStatement(sql);
			//
			pstmt.setInt(1, idAdherente);
			// Paso 4: Ejecutar la sentencia
			pstmt.executeUpdate();
			// Paso 5(opc.): Procesar los resultados
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
	}

	@Override
	public ArrayList<Adherente> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Adherente queryById(int idAdherente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Adherente> getPosibleAdherente(String dni) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Adherente> arr = new ArrayList<Adherente>();
		try {
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentenciad
			String sql = "SELECT * FROM RegistroElector WHERE compararDNI(?,DNI) >= 5;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dni);
			// Paso 4: Ejecutar la sentencia
			rs = pstmt.executeQuery();

			// Paso 5(opc.): Procesar los resultados
			while (rs.next()) {
				int id = rs.getInt("idElector");
				String nombre = rs.getString("Nombre");
				String apPaterno = rs.getString("ApellidoPaterno");
				String apMaterno = rs.getString("ApellidoMaterno");
				String dniL = rs.getString("DNI");
				int idDistrito = rs.getInt("idDistrito");
				Date fechaNacimiento = new Date(rs.getDate("FechaNacimiento").getTime());
				int huella = rs.getInt("Huella");
				String pathHuella = (huella < 10) ? ("00" + huella) : (huella < 100) ? ("0" + huella) : ("" + huella);
				String pathFirma = rs.getString("Firma");
				Adherente adherente = new Adherente(id, idDistrito, nombre, apPaterno, apMaterno, dniL, fechaNacimiento,
						pathHuella, pathFirma, "0");
				arr.add(adherente);
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
			;
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			;
		}
		return arr;
	}

	@Override
	public Adherente queryByDNI(String dni) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Adherente p = null;
		try {
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentencia
			String sql = "SELECT * FROM Adherente A, AdherentexPlanillon B "
					+ "WHERE A.DNI=? AND (A.idAdherente = B.idAdherente)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dni);
			// Paso 4: Ejecutar la sentencia
			rs = pstmt.executeQuery();
			// Paso 5(opc.): Procesar los resultados
			if (rs.next()) {
				int id = rs.getInt("idAdherente");
				String nombre = rs.getString("Nombre");
				String apPat = rs.getString("ApellidoPaterno");
				String apMat = rs.getString("ApellidoMaterno");
				String dniA = rs.getString("DNI");
				int distrito = rs.getInt("idDistrito");
				String rHuella = rs.getString("Huella");
				String rFirma = rs.getString("Firma");
				p = new Adherente(id, distrito, nombre, apPat, apMat, dniA, new Date(), "", "", "");
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
		return p;
	}

	@Override
	public void updateEstadoAdherente(int idAdherente, String estado) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentencia
			// (1=ACEPTADO,0=RECHAZADO,2=OBSERVADO)
			String sql = "UPDATE AdherentexPlanillon SET EstadoValidez=? WHERE idAdherente = ?";
			pstmt = conn.prepareStatement(sql);
			int nEstado = (estado.compareTo("ACEPTADO") == 0) ? 1 : 0;
			pstmt.setString(1, "" + nEstado);
			pstmt.setInt(2, idAdherente);
			// Paso 4: Ejecutar la sentencia
			pstmt.executeUpdate();
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
	public int verificarAdherenteRepetido(int idProceso, String dni) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentencia
			// (1=ACEPTADO,0=RECHAZADO,2=OBSERVADO)
			String sql = "SELECT * FROM Planillon A, AdherentexPlanillon B, Adherente C "
					+ "WHERE (A.idProceso = ?) AND (A.idPlanillon = B.idPlanillon) AND (C.DNI = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idProceso);
			pstmt.setString(2, dni);
			// Paso 4: Ejecutar la sentencia
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int idAdherente = rs.getInt("idAdherente");
				return idAdherente;
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
		return -1;
	}

}
