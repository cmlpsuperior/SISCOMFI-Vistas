package pe.pucp.edu.pe.siscomfi.bm.dao;

import pe.pucp.edu.pe.siscomfi.model.Proceso;

public interface DAOProceso {
	void add (Proceso p);
	void update (Proceso p);
	void delete (int idProceso);
	//ArrayList<Usuario> queryAll();
	Proceso queryById(int idProceso);
	//ArrayList<Usuario> queryByFilter(String name);

}
