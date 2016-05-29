package pe.pucp.edu.pe.siscomfi.bm.dao;

import java.util.ArrayList;
import pe.pucp.edu.pe.siscomfi.model.Rol;

public interface DAORol {
	
	ArrayList<Rol> queryAll();
	ArrayList<Rol> queryById(int idRol);
}
