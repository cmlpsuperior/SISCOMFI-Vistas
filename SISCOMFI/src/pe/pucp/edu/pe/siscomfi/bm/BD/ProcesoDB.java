package pe.pucp.edu.pe.siscomfi.bm.BD;

import java.util.ArrayList;

import pe.pucp.edu.pe.siscomfi.bm.dao.DAOFactory;
import pe.pucp.edu.pe.siscomfi.bm.dao.DAOProceso;
import pe.pucp.edu.pe.siscomfi.bm.dao.DBConnection;
import pe.pucp.edu.pe.siscomfi.model.PartidoPolitico;
import pe.pucp.edu.pe.siscomfi.model.Planillon;
import pe.pucp.edu.pe.siscomfi.model.Proceso;

public class ProcesoDB {

	DAOFactory daoFactory = DAOFactory.getDAOFactory(DBConnection.dbType);
	DAOProceso daoProceso = daoFactory.getDAOProceso(); // POLIMORFISMO

	ArrayList<Proceso> listaProceso = null;

	public ProcesoDB() {
		// queryAll();
	}

	public void add(Proceso p) {
		daoProceso.add(p);
	}

	public void update(Proceso p) {
		daoProceso.update(p);
	}

	public void delete(int idProceso) {
		daoProceso.delete(idProceso);
	}

	public Proceso queryById(int idProceso) {
		return daoProceso.queryById(idProceso);
	}

	public ArrayList<Proceso> queryAll() {
		listaProceso = daoProceso.queryAll();
		return listaProceso;
	}

	public Proceso getFase1Proceso(int idTipoProceso) {
		return daoProceso.getFase1Actual(idTipoProceso);
	}

	public Proceso getFase2Proceso(int idTipoProceso) {
		return daoProceso.getFase2Actual(idTipoProceso);
	}

	public int addPlanillon(Planillon p) {
		return daoProceso.addPlanillon(p);
	}

	public void addAdherentexPlanillon(int idAdherente, int adPlanillon, int estado, double tProcesado, double pHuella,
			double pFirma, String huella, String firma,int numFase) {
		daoProceso.addAdherentexPlanillon(idAdherente, adPlanillon, estado, tProcesado, pHuella, pFirma, huella, firma,numFase);
	}

	public void addPartidoxProceso(int idPartido, int idProceso, int idUsuario, double tiempoProcesado, int estado) {
		daoProceso.addPartidoxProceso(idPartido, idProceso, idUsuario, tiempoProcesado, estado);
	}
	
	public int verificarPartidoProcesado(int idPartido, int idProceso){
		return daoProceso.verificarPartidoProcesado(idPartido, idProceso);
	}
	
	public void updateEstadoPartidoxProceso(int idPartido, int idProceso, int estado){
		daoProceso.updateEstadoPartidoxProceso(idPartido, idProceso, estado);
	}
}
