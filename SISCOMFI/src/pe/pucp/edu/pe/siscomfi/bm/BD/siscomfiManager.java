package pe.pucp.edu.pe.siscomfi.bm.BD;

import java.util.ArrayList;

import pe.pucp.edu.pe.siscomfi.model.PartidoPolitico;
import pe.pucp.edu.pe.siscomfi.model.Rol;
import pe.pucp.edu.pe.siscomfi.model.Usuario;

public class siscomfiManager {
	private static UsuarioDB usuarioDB = new UsuarioDB();
	private static RolDB rolDB = new RolDB();
	private static PartidoPoliticoDB partidoPoliticoDB = new PartidoPoliticoDB();
	
	public static void addUsuario(Usuario u)
    {
		usuarioDB.add(u);
    }
	
	public static ArrayList<Rol> queryAllRoles()
    {
		return rolDB.queryAll();
    }
	
	
	
	public static ArrayList<PartidoPolitico> queryAllPartidos()
    {
		return partidoPoliticoDB.queryAll();
    }
	public static void addPartido(PartidoPolitico p)
    {
		partidoPoliticoDB.add(p);;
    }
}
