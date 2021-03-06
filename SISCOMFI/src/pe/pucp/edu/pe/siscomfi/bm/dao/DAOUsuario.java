package pe.pucp.edu.pe.siscomfi.bm.dao;


import pe.pucp.edu.pe.siscomfi.model.Usuario;

public interface DAOUsuario {
	void add(Usuario u);
	void update(Usuario u);
	void delete (int idUsuario);
	//ArrayList<Usuario> queryAll();
	Usuario queryById(int idUsuario);
	Usuario queryByCorreo(String correo);
	boolean queryByLogin(String nombreCorreo, String pass);
	boolean queryByLoginAdmin(String nombreCorreo, String pass);
	//ArrayList<Usuario> queryByFilter(String name);
	String queryRecuperarContrasenia(String correo);
	String queryByUsuario(String correo);
	String queryByCorreo_RptaSecreta (String correo, String rptaSecreta);
}
