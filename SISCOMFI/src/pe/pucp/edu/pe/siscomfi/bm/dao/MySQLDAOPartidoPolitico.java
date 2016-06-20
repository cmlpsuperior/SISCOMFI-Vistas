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
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentencia
			String sql = "INSERT INTO PartidoPolitico "
					+ "(Nombre, Representante, CorreoRepresentante,    Direccion, Telefono, IdDistrito,    FechaRegistro, EstadoActivo) "
					+ "VALUES (?,?,?,  ?,?,?, ?, 'A')";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, p.getNombrePartido());
			pstmt.setString(2, p.getRepresentante());
			pstmt.setString(3, p.getCorreo());
			pstmt.setString(4, p.getDireccion());
			pstmt.setString(5, p.getTelefono());
			pstmt.setInt(6, p.getIdDistrito());
			pstmt.setDate(7, new java.sql.Date(p.getFechaRegistro().getTime()));
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
	public void update(PartidoPolitico p) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentencia
			String sql = "UPDATE PartidoPolitico "
					+ " SET Representante=?, CorreoRepresentante=?, Direccion=?, Telefono = ? , EstadoActivo = 'M'"
					+ "WHERE idPartidoPolitico=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getRepresentante());
			pstmt.setString(2, p.getCorreo());
			pstmt.setString(3, p.getDireccion());
			pstmt.setString(4, p.getTelefono());
			pstmt.setInt(5, p.getIdPartidoPolitico());
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
	public void delete(int idPartido) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentencia
			String sql = "UPDATE PartidoPolitico SET EstadoActivo = 'E' " + "WHERE idPartidoPolitico=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idPartido);
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
	public ArrayList<PartidoPolitico> queryAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PartidoPolitico> arr = new ArrayList<PartidoPolitico>();
		try {
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentencia
			String sql = "SELECT * FROM PartidoPolitico WHERE EstadoActivo <> 'E' ORDER BY 2";
			pstmt = conn.prepareStatement(sql);
			// Paso 4: Ejecutar la sentencia
			rs = pstmt.executeQuery();
			// Paso 5(opc.): Procesar los resultados
			while (rs.next()) {
				int id = rs.getInt("idPartidoPolitico");
				String nombre = rs.getString("Nombre");
				String rep = rs.getString("Representante");
				String correo = rs.getString("CorreoRepresentante");
				String direccion = rs.getString("Direccion");
				String telefono = rs.getString("Telefono");
				int idDistrito = rs.getInt("idDistrito");
				Date fechaRegistro = rs.getTimestamp("FechaRegistro");
				String estadoActivo = rs.getString("EstadoActivo");

				PartidoPolitico p = new PartidoPolitico();
				p.setIdPartidoPolitco(id);
				p.setNombrePartido(nombre);
				p.setRepresentante(rep);
				p.setCorreo(correo);
				p.setDireccion(direccion);
				p.setTelefono(telefono);
				p.setIdDistrito(idDistrito);
				p.setFechaRegistro(fechaRegistro);
				p.setEstadoActivo(estadoActivo);

				arr.add(p);
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
		return arr;
	}

	@Override
	public PartidoPolitico queryById(int idPartido) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PartidoPolitico p = null;
		try {
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentencia
			String sql = "SELECT * FROM PartidoPolitico " + "WHERE idPartidoPolitico=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idPartido);
			// Paso 4: Ejecutar la sentencia
			rs = pstmt.executeQuery();
			// Paso 5(opc.): Procesar los resultados
			if (rs.next()) {
				int id = rs.getInt("idPartidoPolitico");
				String nombre = rs.getString("Nombre");
				String rep = rs.getString("Representante");
				String correo = rs.getString("CorreoRepresentante");
				String direccion = rs.getString("Direccion");
				String telefono = rs.getString("Telefono");
				int idDistrito = rs.getInt("idDistrito");
				Date fechaRegistro = rs.getTimestamp("FechaRegistro");

				p = new PartidoPolitico();
				p.setIdPartidoPolitco(id);
				p.setNombrePartido(nombre);
				p.setRepresentante(rep);
				p.setCorreo(correo);
				p.setDireccion(direccion);
				p.setTelefono(telefono);
				p.setIdDistrito(idDistrito);
				p.setFechaRegistro(fechaRegistro);
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
	public PartidoPolitico queryByRepresentante(String representante) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PartidoPolitico p = null;
		try {
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentencia
			String sql = "SELECT * FROM PartidoPolitico " + "WHERE Representante=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, representante);
			// Paso 4: Ejecutar la sentencia
			rs = pstmt.executeQuery();
			// Paso 5(opc.): Procesar los resultados
			if (rs.next()) {
				int id = rs.getInt("idPartidoPolitico");
				String nombre = rs.getString("Nombre");
				String rep = rs.getString("Representante");
				String correo = rs.getString("CorreoRepresentante");
				String direccion = rs.getString("Direccion");
				String telefono = rs.getString("Telefono");
				int idDistrito = rs.getInt("idDistrito");
				Date fechaRegistro = rs.getTimestamp("FechaRegistro");

				p = new PartidoPolitico();
				p.setIdPartidoPolitco(id);
				p.setNombrePartido(nombre);
				p.setRepresentante(rep);
				p.setCorreo(correo);
				p.setDireccion(direccion);
				p.setTelefono(telefono);
				p.setIdDistrito(idDistrito);
				p.setFechaRegistro(fechaRegistro);
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
	public PartidoPolitico queryByNombre(String nombreP) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PartidoPolitico p = null;
		try {
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentencia
			String sql = "SELECT * FROM PartidoPolitico " + "WHERE Nombre=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nombreP);
			// Paso 4: Ejecutar la sentencia
			rs = pstmt.executeQuery();
			// Paso 5(opc.): Procesar los resultados
			if (rs.next()) {
				int id = rs.getInt("idPartidoPolitico");
				String nombre = rs.getString("Nombre");
				String rep = rs.getString("Representante");
				String correo = rs.getString("CorreoRepresentante");
				String direccion = rs.getString("Direccion");
				String telefono = rs.getString("Telefono");
				int idDistrito = rs.getInt("idDistrito");
				Date fechaRegistro = rs.getTimestamp("FechaRegistro");

				p = new PartidoPolitico();
				p.setIdPartidoPolitco(id);
				p.setNombrePartido(nombre);
				p.setRepresentante(rep);
				p.setCorreo(correo);
				p.setDireccion(direccion);
				p.setTelefono(telefono);
				p.setIdDistrito(idDistrito);
				p.setFechaRegistro(fechaRegistro);
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

	// Esta listando todos los partidos que estan actualmente en un proceso
	@Override
	public ArrayList<PartidoPolitico> queryAllObservados() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PartidoPolitico> arr = new ArrayList<PartidoPolitico>();
		try {
			// Paso 1: Registrar el Driver
			DriverManager.registerDriver(new Driver());
			// Paso 2: Obtener la conexión
			conn = DriverManager.getConnection(DBConnection.URL_JDBC_MySQL, DBConnection.user, DBConnection.password);
			// Paso 3: Preparar la sentencia
			String sql = " select pa.*    "
					+ " from PartidoPoliticoxProceso pp join Proceso p on pp.idProceso =  p.idProceso  									"
					+ " 								  join PartidoPolitico pa on pa.idPartidoPolitico = pp.idPartidoPolitico			"
					+ " where (Now() between p.FechaProceso1Inicio and p.FechaProceso1Fin) or 											"
					+ "		(Now() between p.FechaProceso2Inicio and p.FechaProceso2Fin)  and (pp.EstadoPartido = '3') 			 									";

			pstmt = conn.prepareStatement(sql);
			// Paso 4: Ejecutar la sentencia
			rs = pstmt.executeQuery();
			// Paso 5(opc.): Procesar los resultados
			while (rs.next()) {
				int id = rs.getInt("idPartidoPolitico");
				String nombre = rs.getString("Nombre");
				String rep = rs.getString("Representante");
				String correo = rs.getString("CorreoRepresentante");
				String direccion = rs.getString("Direccion");
				String telefono = rs.getString("Telefono");
				int idDistrito = rs.getInt("idDistrito");
				Date fechaRegistro = rs.getTimestamp("FechaRegistro");
				String estadoActivo = rs.getString("EstadoActivo");

				PartidoPolitico p = new PartidoPolitico();
				p.setIdPartidoPolitco(id);
				p.setNombrePartido(nombre);
				p.setRepresentante(rep);
				p.setCorreo(correo);
				p.setDireccion(direccion);
				p.setTelefono(telefono);
				p.setIdDistrito(idDistrito);
				p.setFechaRegistro(fechaRegistro);
				p.setEstadoActivo(estadoActivo);
				arr.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
}
