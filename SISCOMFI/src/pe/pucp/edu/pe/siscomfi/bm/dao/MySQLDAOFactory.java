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
		return null;
	}
	
}
