package pe.pucp.edu.pe.siscomfi.bm.dao;

import java.util.ArrayList;
import java.util.List;

import pe.pucp.edu.pe.siscomfi.model.Planillon;
import pe.pucp.edu.pe.siscomfi.model.Proceso;
import pe.pucp.edu.pe.siscomfi.model.RegistroElector;

public interface DAOProceso {
	void add (Proceso p);
	void update (Proceso p);
	void delete (int idProceso);
	//ArrayList<Usuario> queryAll();
	ArrayList<Proceso> queryAll();
	ArrayList<Proceso> queryDisponibles(); //agregado :)
	Proceso queryById(int idProceso);	
	//ArrayList<Usuario> queryByFilter(String name);
	Proceso getFase1Actual(int idTipoPartido);
	Proceso getFase2Actual(int idTipoPartido);
	int addPlanillon(Planillon p);
	void addAdherentexPlanillon(int idAdherente, int idPlanillon, int estado, double tProcesado, double pHuella, double pFirma,String huella, String firma,int numFase);
	void addPartidoxProceso(int idPartido,int idProceso, int idUsuario, double TiempoProcesado,  int estado);
	int verificarPartidoProcesado(int idPartido, int idProceso);
	void updateEstadoPartidoxProceso(int idPartido, int idProceso, double tProcesado, int estado);
	void updateEstadoPartidoxProceso(int idPartido, int idProceso, int estado);
	void agregarRegistroElectorRNV(RegistroElector registros);
}
