package pe.pucp.edu.pe.siscomfi.bm.dao;

import java.util.ArrayList;

import pe.pucp.edu.pe.siscomfi.model.Departamento;
import pe.pucp.edu.pe.siscomfi.model.Distrito;
import pe.pucp.edu.pe.siscomfi.model.Provincia;


public interface DAOUbicacion {	
	ArrayList<Distrito> queryDistritosByIdProvincia(int idProvincia);
	ArrayList<Departamento> queryAllDepartamento();
	ArrayList<Provincia> queryProvinciasByIdDepartamento(int idDepartamento);
	
}
