package pe.pucp.edu.pe.siscomfi.bm.dao;

import java.util.ArrayList;

import pe.pucp.edu.pe.siscomfi.model.Proceso;
import pe.pucp.edu.pe.siscomfi.model.TipoProceso;

public interface DAOTipoProceso {
	void add (TipoProceso tp);
	void update (TipoProceso tp);
	void delete (int idTipoProceso);
	ArrayList<TipoProceso> queryAll();
	Proceso queryById(int idTipoProceso);
	//ArrayList<Usuario> queryByFilter(String name);

}
