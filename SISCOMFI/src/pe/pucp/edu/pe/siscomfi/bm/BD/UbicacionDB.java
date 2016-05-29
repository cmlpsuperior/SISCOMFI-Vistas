package pe.pucp.edu.pe.siscomfi.bm.BD;

import java.util.ArrayList;

import pe.pucp.edu.pe.siscomfi.bm.dao.DAOFactory;
import pe.pucp.edu.pe.siscomfi.bm.dao.DAOUbicacion;
import pe.pucp.edu.pe.siscomfi.bm.dao.DBConnection;
import pe.pucp.edu.pe.siscomfi.model.Departamento;
import pe.pucp.edu.pe.siscomfi.model.Provincia;


public class UbicacionDB {
	DAOFactory daoFactory = DAOFactory.getDAOFactory(DBConnection.dbType);
    DAOUbicacion daoUbicacion = daoFactory.getDAOUbicacion(); // POLIMORFISMO
    
    ArrayList<Provincia> listaProvincia= null;
	ArrayList<Departamento> listaDepartamento= null;
	
    public ArrayList<Provincia> queryAllProvincia()
    {
    	listaProvincia = daoUbicacion.queryAllProvincia();
    	return listaProvincia;
    }
    
    public ArrayList<Departamento> queryAllDepartamento()
    {
    	listaDepartamento = daoUbicacion.queryAllDepartamento();
    	return listaDepartamento;
    }
}
