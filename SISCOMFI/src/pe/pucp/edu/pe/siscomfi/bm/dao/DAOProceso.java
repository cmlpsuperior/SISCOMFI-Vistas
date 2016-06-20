package pe.pucp.edu.pe.siscomfi.bm.dao;

import java.util.ArrayList;

import pe.pucp.edu.pe.siscomfi.model.Planillon;
import pe.pucp.edu.pe.siscomfi.model.Proceso;

public interface DAOProceso {
	void add (Proceso p);
	void update (Proceso p);
	void delete (int idProceso);
	//ArrayList<Usuario> queryAll();
	ArrayList<Proceso> queryAll();
	Proceso queryById(int idProceso);	
	//ArrayList<Usuario> queryByFilter(String name);
	Proceso getFase1Actual();
	Proceso getFase2Actual();
	int addPlanillon(Planillon p);
	void addAdherentexPlanillon(int idAdherente, int idPlanillon, int estado, double tProcesado, double pHuella, double pFirma,String huella, String firma);
	void addPartidoxProceso(int idPartido,int idProceso, int idUsuario, double TiempoProcesado,  int estado);
}
