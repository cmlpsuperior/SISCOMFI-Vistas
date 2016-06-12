package pe.pucp.edu.pe.siscomfi.bm.BD;

import java.util.ArrayList;

import pe.pucp.edu.pe.siscomfi.bm.dao.DAOFactory;
import pe.pucp.edu.pe.siscomfi.bm.dao.DAORol;
import pe.pucp.edu.pe.siscomfi.bm.dao.DBConnection;
import pe.pucp.edu.pe.siscomfi.model.Rol;



public class RolDB { 
	DAOFactory daoFactory = DAOFactory.getDAOFactory(DBConnection.dbType);
    DAORol daoRol = daoFactory.getDAORol(); // POLIMORFISMO
    
    ArrayList<Rol> rolList= null;
    
    public ArrayList<Rol> queryAll()
    {
    	rolList = daoRol.queryAll();
    	return rolList;
    }
}
