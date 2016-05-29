package pe.pucp.edu.pe.siscomfi.bm.BD;

import java.util.ArrayList;

import pe.pucp.edu.pe.siscomfi.bm.dao.DAOFactory;
import pe.pucp.edu.pe.siscomfi.bm.dao.DAOPartidoPolitico;
import pe.pucp.edu.pe.siscomfi.bm.dao.DBConnection;
import pe.pucp.edu.pe.siscomfi.model.PartidoPolitico;


public class PartidoPoliticoDB {
	DAOFactory daoFactory = DAOFactory.getDAOFactory(DBConnection.dbType);
    DAOPartidoPolitico daoPartidoPolitico = daoFactory.getDAOPartidoPolitico(); // POLIMORFISMO
    
    ArrayList<PartidoPolitico> listaPartido= null;
    
    public void add (PartidoPolitico p){
    	daoPartidoPolitico.add(p);
    }
    
    public ArrayList<PartidoPolitico> queryAll()
    {
    	listaPartido = daoPartidoPolitico.queryAll();
    	return listaPartido;
    }

}
