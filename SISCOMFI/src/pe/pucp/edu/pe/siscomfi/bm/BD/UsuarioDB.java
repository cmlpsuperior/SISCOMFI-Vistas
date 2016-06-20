package pe.pucp.edu.pe.siscomfi.bm.BD;


import pe.pucp.edu.pe.siscomfi.bm.dao.DAOFactory;
import pe.pucp.edu.pe.siscomfi.bm.dao.DAOUsuario;
import pe.pucp.edu.pe.siscomfi.bm.dao.DBConnection;
import pe.pucp.edu.pe.siscomfi.model.Usuario;


public class UsuarioDB {
	
    DAOFactory daoFactory = DAOFactory.getDAOFactory(DBConnection.dbType);
    DAOUsuario daoUsuario = daoFactory.getDAOUsuario(); // POLIMORFISMO
    
    public UsuarioDB (){
    	//queryAll();
    }
    
    public void add(Usuario p) {
    	daoUsuario.add(p);
    	
    }
    public void update(Usuario p) {
    	daoUsuario.update(p);
    	
    }
    public void delete(int idCompany) {
    	daoUsuario.delete(idCompany);
    	
    }
    
    public Usuario queryByCorreo(String  usuarioId) {
    	return daoUsuario.queryByCorreo(usuarioId);
    }
    
    public boolean queryByLogin(String nombreCorreo, String pass) {
    	return daoUsuario.queryByLogin(nombreCorreo, pass);
    }
    
    
    public boolean queryByLoginAdmin(String nombreCorreo, String pass) {
    	return daoUsuario.queryByLoginAdmin(nombreCorreo, pass);
    }
    
    public String queryRecuperarContrasenia(String usuario){
    	return daoUsuario.queryRecuperarContrasenia(usuario);
    }
    
}
