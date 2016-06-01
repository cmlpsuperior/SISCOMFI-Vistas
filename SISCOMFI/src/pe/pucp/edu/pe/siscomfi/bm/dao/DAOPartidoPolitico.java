package pe.pucp.edu.pe.siscomfi.bm.dao;

import java.util.ArrayList;
import pe.pucp.edu.pe.siscomfi.model.PartidoPolitico;

public interface DAOPartidoPolitico {
	void add(PartidoPolitico u);
	void update(PartidoPolitico u);
	void delete (int idPartido);
	ArrayList<PartidoPolitico> queryAll();
	PartidoPolitico queryById(int idPartido);
	ArrayList<PartidoPolitico> queryAllObservados();
}
