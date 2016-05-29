package pe.pucp.edu.pe.siscomfi.bm.BD;

import java.util.ArrayList;

import pe.pucp.edu.pe.siscomfi.model.PartidoPolitico;
import pe.pucp.edu.pe.siscomfi.model.Rol;
import pe.pucp.edu.pe.siscomfi.model.Usuario;
import pe.pucp.edu.pe.siscomfi.model.Proceso;
import pe.pucp.edu.pe.siscomfi.model.TipoProceso;

public class siscomfiManager {
	private static UsuarioDB usuarioDB = new UsuarioDB();
	private static RolDB rolDB = new RolDB();
	private static PartidoPoliticoDB partidoPoliticoDB = new PartidoPoliticoDB();
	private static ProcesoDB procesoDB = new ProcesoDB();
	private static TipoProcesoDB tipoProcesoDB = new TipoProcesoDB();
	
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
		partidoPoliticoDB.add(p);
    }
	
	public static void addProceso(Proceso p)
    {
		procesoDB.add(p);
    }
	
	public static ArrayList<TipoProceso> queryAllTipoProcesos()
    {
		return tipoProcesoDB.queryAll();
    }	
	
	
}
