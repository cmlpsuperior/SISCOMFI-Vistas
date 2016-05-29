package pe.pucp.edu.pe.siscomfi.bm.dao;

public class MySQLDAOFactory extends DAOFactory{

	@Override
	public DAOUsuario getDAOUsuario() {
		return new MySQLDAOUsuario();
	}

	@Override
	public DAORol getDAORol() {
		// TODO Auto-generated method stub
		return new MySQLDAORol();
	}

	@Override
	public DAOPartidoPolitico getDAOPartidoPolitico() {
		// TODO Auto-generated method stub
		return new MySQLDAOPartidoPolitico ();
	}

	@Override
	public DAOUbicacion getDAOUbicacion() {
		// TODO Auto-generated method stub
		return new MySQLDAOUbicacion();
	}
	
	@Override
	public DAOProceso getDAOProceso() {
		// TODO Auto-generated method stub
		return new MySQLDAOProceso();
	}

	@Override
	public DAOTipoProceso getDAOTipoProceso() { //NO VAMOS A HACE MANTENIMIENTO AL TIPO DE PROCESO
		// TODO Auto-generated method stub
		return new MySQLDAOTipoProceso();
	}
	
}
