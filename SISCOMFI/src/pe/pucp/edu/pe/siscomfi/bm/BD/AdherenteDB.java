package pe.pucp.edu.pe.siscomfi.bm.BD;

import java.util.ArrayList;
import java.util.List;

import pe.pucp.edu.pe.siscomfi.bm.dao.DAOAdherente;
import pe.pucp.edu.pe.siscomfi.bm.dao.DAOFactory;
import pe.pucp.edu.pe.siscomfi.bm.dao.DBConnection;
import pe.pucp.edu.pe.siscomfi.model.Adherente;

public class AdherenteDB {
	DAOFactory daoFactory = DAOFactory.getDAOFactory(DBConnection.dbType);
	DAOAdherente daoAdherente = daoFactory.getDAOAdherente(); // POLIMORFISMO

	ArrayList<Adherente> listaAdherente = null;

	public int add(Adherente a) {
		return daoAdherente.add(a);
	}

	public void update(Adherente u) {
		daoAdherente.update(u);
	}

	public void delete(int idAdherente) {
		daoAdherente.delete(idAdherente);
	}

	public ArrayList<Adherente> queryAll() {
		return daoAdherente.queryAll();
	}

	public Adherente queryById(int idAdherente) {
		return daoAdherente.queryById(idAdherente);
	}

	public List<Adherente> getAdherentesPosibles(String dni) {
		return daoAdherente.getPosibleAdherente(dni);
	}

	public Adherente queryByDni(String dni) {
		return daoAdherente.queryByDNI(dni);
	}

	public void updateEstadoAdherente(int  idAdherente, String estado) {
		daoAdherente.updateEstadoAdherente(idAdherente, estado);
	}
	
	public int verificarAdherenteRepetido(int idProceso, String dni){
		return daoAdherente.verificarAdherenteRepetido(idProceso, dni);
	}
}
