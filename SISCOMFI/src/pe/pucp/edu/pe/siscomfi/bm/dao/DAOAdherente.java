package pe.pucp.edu.pe.siscomfi.bm.dao;

import java.util.ArrayList;

import pe.pucp.edu.pe.siscomfi.model.Adherente;

public interface DAOAdherente {
	void add(Adherente a);
	void update(Adherente u);
	void delete (int idAdherente);
	ArrayList<Adherente> queryAll();
	Adherente queryById(int idAdherente);
}
