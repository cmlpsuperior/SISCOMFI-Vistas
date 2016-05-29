package pe.pucp.edu.pe.siscomfi.bm.BD;

import java.util.ArrayList;

import pe.pucp.edu.pe.siscomfi.bm.dao.DAOFactory;
import pe.pucp.edu.pe.siscomfi.bm.dao.DAOTipoProceso;
import pe.pucp.edu.pe.siscomfi.bm.dao.DBConnection;
import pe.pucp.edu.pe.siscomfi.model.TipoProceso;

public class TipoProcesoDB {
	DAOFactory daoFactory = DAOFactory.getDAOFactory(DBConnection.dbType);
    DAOTipoProceso daoTipoProceso = daoFactory.getDAOTipoProceso(); // POLIMORFISMO
    
    ArrayList<TipoProceso> tipoProcesoList = null;
    
    public ArrayList<TipoProceso> queryAll()
    {
    	tipoProcesoList = daoTipoProceso.queryAll();
    	return tipoProcesoList;
    }

}
