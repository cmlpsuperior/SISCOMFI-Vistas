package pe.pucp.edu.pe.siscomfi.bm.BD;

import java.util.ArrayList;

import pe.pucp.edu.pe.siscomfi.bm.dao.DAOFactory;
import pe.pucp.edu.pe.siscomfi.bm.dao.DAOPartidoPolitico;
import pe.pucp.edu.pe.siscomfi.bm.dao.DBConnection;
import pe.pucp.edu.pe.siscomfi.model.PartidoPolitico;
import pe.pucp.edu.pe.siscomfi.model.Reporte;


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
    
    public void update(PartidoPolitico p)
    {
    	daoPartidoPolitico.update(p);
    }
    
    public void delete(int idPartido)
    {
    	daoPartidoPolitico.delete(idPartido);
    }
    
    public PartidoPolitico queryById(int idPartido)
    {
    	return daoPartidoPolitico.queryById(idPartido);
    	
    }
    
    public PartidoPolitico queryByRepresentante(String representante)
    {
    	return daoPartidoPolitico.queryByRepresentante(representante);
    	
    }
    
    public PartidoPolitico queryByNombre(String nombre)
    {
    	return daoPartidoPolitico.queryByNombre(nombre);
    	
    }
    
    public int contarAdherentesAceptados(int idPartido){
    	return daoPartidoPolitico.contarAdherenteAceptado(idPartido);
    }
    //Observados
    public ArrayList<PartidoPolitico> queryAllObservados()
    {
    	return daoPartidoPolitico.queryAllObservados();
    	
    }
    
    //REPORTE:
    public ArrayList<Reporte> queryReporte(int idTipoProceso, int anio, int idfase, int estadoPartido)
    {
    	return daoPartidoPolitico.queryReporte(idTipoProceso, anio, idfase, estadoPartido);
    }
}
