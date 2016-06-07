package pe.pucp.edu.pe.siscomfi.bm.BD;

import java.util.ArrayList;

import pe.pucp.edu.pe.siscomfi.bm.dao.DAOAdherente;
import pe.pucp.edu.pe.siscomfi.bm.dao.DAOFactory;
import pe.pucp.edu.pe.siscomfi.bm.dao.DBConnection;
import pe.pucp.edu.pe.siscomfi.model.Adherente;

public class AdherenteDB {
	DAOFactory daoFactory = DAOFactory.getDAOFactory(DBConnection.dbType);
    DAOAdherente daoAdherente = daoFactory.getDAOAdherente(); // POLIMORFISMO
    
    ArrayList<Adherente> listaAdherente= null;
    
    public void add(Adherente a){
		daoAdherente.add(a);
	}
	public void update(Adherente u){
		daoAdherente.update(u);
	}
	public void delete (int idAdherente){
		daoAdherente.delete(idAdherente);
	}
	public ArrayList<Adherente> queryAll(){
		return daoAdherente.queryAll();
	}
	public Adherente queryById(int idAdherente){
		return daoAdherente.queryById(idAdherente);
	}
}
