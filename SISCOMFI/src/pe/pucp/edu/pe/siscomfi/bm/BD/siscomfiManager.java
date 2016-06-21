package pe.pucp.edu.pe.siscomfi.bm.BD;

import java.util.ArrayList;
import java.util.List;

import pe.pucp.edu.pe.siscomfi.model.Adherente;
import pe.pucp.edu.pe.siscomfi.model.Departamento;
import pe.pucp.edu.pe.siscomfi.model.Distrito;
import pe.pucp.edu.pe.siscomfi.model.PartidoPolitico;
import pe.pucp.edu.pe.siscomfi.model.Planillon;
import pe.pucp.edu.pe.siscomfi.model.Rol;
import pe.pucp.edu.pe.siscomfi.model.Usuario;
import pe.pucp.edu.pe.siscomfi.model.Proceso;
import pe.pucp.edu.pe.siscomfi.model.Provincia;
import pe.pucp.edu.pe.siscomfi.model.Reporte;
import pe.pucp.edu.pe.siscomfi.model.TipoProceso;

public class siscomfiManager {
	private static UsuarioDB usuarioDB = new UsuarioDB();
	private static RolDB rolDB = new RolDB();
	private static PartidoPoliticoDB partidoPoliticoDB = new PartidoPoliticoDB();
	private static ProcesoDB procesoDB = new ProcesoDB();
	private static TipoProcesoDB tipoProcesoDB = new TipoProcesoDB();
	private static UbicacionDB ubicacionDB = new UbicacionDB();
	private static AdherenteDB adherenteDB = new AdherenteDB();

	// Usuario
	public static void addUsuario(Usuario u) {
		usuarioDB.add(u);
	}

	public static ArrayList<Rol> queryAllRoles() {
		return rolDB.queryAll();
	}

	public static boolean queryByLogin(String nombreCorreo, String pass) {
		return usuarioDB.queryByLogin(nombreCorreo, pass);
	}

	public static String queryRecuperarContrasenia(String correo){
		return usuarioDB.queryRecuperarContrasenia(correo);
	}
	
	public static String queryByUsuario(String correo){
		return usuarioDB.queryByUsuario(correo);
	}	
	
	public static String queryByCorreo_RptaSecreta(String correo, String rptaSecreta){
		return usuarioDB.queryByCorreo_RptaSecreta(correo, rptaSecreta);
	}
	// devuelve true si el usuario y pass ingresado pertenece a un administrador
	public static boolean queryByLoginAdmin(String nombreCorreo, String pass) {
		return usuarioDB.queryByLoginAdmin(nombreCorreo, pass);
	}

	public static Usuario queryByCorreo(String correo){
		return usuarioDB.queryByCorreo(correo);
	}
	// PartidosPoliticos:
	public static ArrayList<PartidoPolitico> queryAllPartidosConObservados() {
		return partidoPoliticoDB.queryAllObservados();
	}

	public static ArrayList<PartidoPolitico> queryAllPartidos() {
		return partidoPoliticoDB.queryAll();
	}

	public static void addPartido(PartidoPolitico p) {
		partidoPoliticoDB.add(p);
	}

	public static void updatePartido(PartidoPolitico p) {
		partidoPoliticoDB.update(p);
	}

	public static void deletePartido(int idPartido) {
		partidoPoliticoDB.delete(idPartido);
	}

	public static PartidoPolitico queryPartidoById(int idPartido) {
		return partidoPoliticoDB.queryById(idPartido);
	}

	public static PartidoPolitico queryPartido_byRepresentante(String representante) {
		return partidoPoliticoDB.queryByRepresentante(representante);
	}

	public static PartidoPolitico queryPartido_byNombre(String nombre) {
		return partidoPoliticoDB.queryByNombre(nombre);
	}
	
	//Reporte:
	public static ArrayList<Reporte> queryReportePartidos (int idTipoProceso, int anio, int idfase, int estadoPartido)
    {
    	return partidoPoliticoDB.queryReporte(idTipoProceso, anio, idfase, estadoPartido);
    }

	// Ubicacion
	public static ArrayList<Distrito> queryDistritosByIdProvincia(int idProvincia) {
		return ubicacionDB.queryDistritosByIdProvincia(idProvincia);
	}

	public static ArrayList<Provincia> queryProvinciaByIdDepartamento(int idDepa) {
		return ubicacionDB.queryProvinciasByIdDepartamento(idDepa);
	}

	public static ArrayList<Departamento> queryAllDepartamento() {
		return ubicacionDB.queryAllDepartamento();
	}

	// Adherente:
	public static int addAdherente(Adherente a) {
		return adherenteDB.add(a);
	}

	public static void updateAdherente(Adherente u) {
		adherenteDB.update(u);
	}

	public static void deleteAdherente(int idAdherente) {
		adherenteDB.delete(idAdherente);
	}

	public static ArrayList<Adherente> queryAllAdherente() {
		return adherenteDB.queryAll();
	}

	public static Adherente queryAdherenteById(int idAdherente) {
		return adherenteDB.queryById(idAdherente);
	}

	public static List<Adherente> getPosiblesAdherentes(String dni) {
		return adherenteDB.getAdherentesPosibles(dni);
	}

	public static Adherente queryAdherenteByDni(String dni) {
		return adherenteDB.queryByDni(dni);
	}

	public static void updateEstadoAdherente(int idAdherente, String estado) {
		adherenteDB.updateEstadoAdherente(idAdherente, estado);
	}

	public static int verificarAdherenteRepetido(int idProceso, String dni) {
		return adherenteDB.verificarAdherenteRepetido(idProceso, dni);
	}

	// proceso
	public static ArrayList<Proceso> queryAllProcesos() {
		return procesoDB.queryAll();
	}

	public static Proceso getFase1Proceso() {
		return procesoDB.getFase1Proceso();
	}

	public static Proceso getFase2Proceso() {
		return procesoDB.getFase2Proceso();
	}

	public static int addPlanillon(Planillon p) {
		return procesoDB.addPlanillon(p);
	}

	public static void addAdherentexPlanillon(int idAdherente, int adPlanillon, int estado, double tProcesado,
			double pHuella, double pFirma, String huella, String firma) {
		procesoDB.addAdherentexPlanillon(idAdherente, adPlanillon, estado, tProcesado, pHuella, pFirma, huella, firma);
	}

	public static void addProceso(Proceso p) {
		procesoDB.add(p);
	}

	public static ArrayList<TipoProceso> queryAllTipoProcesos() {
		return tipoProcesoDB.queryAll();
	}

	public static void addPartidoxProceso(int idPartido, int idProceso, int idUsuario, double tiempoProcesado,
			int estado) {
		procesoDB.addPartidoxProceso(idPartido, idProceso, idUsuario, tiempoProcesado, estado);
	}
	
	public static int verificarPartidoProceso(int idPartido, int idProceso){
		return procesoDB.verificarPartidoProcesado(idPartido, idProceso);
	}
	
	public static void updateEstadoPartidoProceso(int idPartido,int idProceso, int estado){
		procesoDB.updateEstadoPartidoxProceso(idPartido, idProceso, estado);
	}
	
}