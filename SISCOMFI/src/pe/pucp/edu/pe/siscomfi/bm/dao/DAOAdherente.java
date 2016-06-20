package pe.pucp.edu.pe.siscomfi.bm.dao;

import java.util.ArrayList;
import java.util.List;

import pe.pucp.edu.pe.siscomfi.model.Adherente;

public interface DAOAdherente {
	int add(Adherente a);
	void update(Adherente u);
	void delete (int idAdherente);
	ArrayList<Adherente> queryAll();
	Adherente queryById(int idAdherente);
	void delete_OBS(int idAdherente);
	List<Adherente> getPosibleAdherente(String dni);
	Adherente queryByDNI(String dni);
	void updateEstadoAdherente(int idAdherente,String estado);
	int verificarAdherenteRepetido(int idProceso, String dni);
}
