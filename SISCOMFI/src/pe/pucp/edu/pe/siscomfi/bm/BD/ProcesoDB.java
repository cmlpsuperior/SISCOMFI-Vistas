package pe.pucp.edu.pe.siscomfi.bm.BD;

import pe.pucp.edu.pe.siscomfi.bm.dao.DAOFactory;
import pe.pucp.edu.pe.siscomfi.bm.dao.DAOProceso;
import pe.pucp.edu.pe.siscomfi.bm.dao.DBConnection;
import pe.pucp.edu.pe.siscomfi.model.Proceso;

public class ProcesoDB {
	
	DAOFactory daoFactory = DAOFactory.getDAOFactory(DBConnection.dbType);
	DAOProceso daoProceso = daoFactory.getDAOProceso(); // POLIMORFISMO

	public ProcesoDB(){
		//queryAll();
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
}
