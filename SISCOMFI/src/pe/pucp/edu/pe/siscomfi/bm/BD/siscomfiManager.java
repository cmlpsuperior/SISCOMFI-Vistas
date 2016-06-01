package pe.pucp.edu.pe.siscomfi.bm.BD;

import java.util.ArrayList;

import pe.pucp.edu.pe.siscomfi.model.Departamento;
import pe.pucp.edu.pe.siscomfi.model.Distrito;
import pe.pucp.edu.pe.siscomfi.model.PartidoPolitico;
import pe.pucp.edu.pe.siscomfi.model.Rol;
import pe.pucp.edu.pe.siscomfi.model.Usuario;
import pe.pucp.edu.pe.siscomfi.model.Proceso;
import pe.pucp.edu.pe.siscomfi.model.Provincia;
import pe.pucp.edu.pe.siscomfi.model.TipoProceso;

public class siscomfiManager {
	private static UsuarioDB usuarioDB = new UsuarioDB();
	private static RolDB rolDB = new RolDB();
	private static PartidoPoliticoDB partidoPoliticoDB = new PartidoPoliticoDB();
	private static ProcesoDB procesoDB = new ProcesoDB();
	private static TipoProcesoDB tipoProcesoDB = new TipoProcesoDB();
	private static UbicacionDB ubicacionDB = new UbicacionDB();
	
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
	
	public static ArrayList<Proceso> queryAllProcesos()
    {
		return procesoDB.queryAll();
    }
	
	public static void addPartido(PartidoPolitico p)
    {
		partidoPoliticoDB.add(p);
    }
	
	public static void updatePartido(PartidoPolitico p)
    {
		partidoPoliticoDB.update(p);
    }
	
	public static void deletePartido(int idPartido)
    {
		partidoPoliticoDB.delete(idPartido);
    }
	
	public static PartidoPolitico queryPartidoById(int idPartido)
    {
		return partidoPoliticoDB.queryById(idPartido);
    }
	
	
	public static void addProceso(Proceso p)
    {
		procesoDB.add(p);
    }
	
	public static ArrayList<TipoProceso> queryAllTipoProcesos()
    {
		return tipoProcesoDB.queryAll();
    }	
	
	//Ubicacion
	public static ArrayList<Distrito> queryDistritosByIdProvincia(int idProvincia)
    {
		return ubicacionDB.queryDistritosByIdProvincia(idProvincia);
    }
	public static ArrayList<Provincia> queryProvinciaByIdDepartamento(int idDepa)
    {
		return ubicacionDB.queryProvinciasByIdDepartamento(idDepa);
    }
	public static ArrayList<Departamento> queryAllDepartamento()
    {
		return ubicacionDB.queryAllDepartamento();
    }
	
	
	//PartidosPoliticos:
	public static ArrayList<PartidoPolitico> queryAllPartidosConObservados()
    {
		return partidoPoliticoDB.queryAllObservados();
    }
	
}
