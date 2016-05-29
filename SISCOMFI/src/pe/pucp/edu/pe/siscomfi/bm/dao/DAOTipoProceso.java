package pe.pucp.edu.pe.siscomfi.bm.dao;

import java.util.ArrayList;

import pe.pucp.edu.pe.siscomfi.model.Proceso;
import pe.pucp.edu.pe.siscomfi.model.Rol;
import pe.pucp.edu.pe.siscomfi.model.TipoProceso;

public interface DAOTipoProceso {
	ArrayList<TipoProceso> queryAll();
	ArrayList<TipoProceso> queryById(int idTipoProceso);

}
