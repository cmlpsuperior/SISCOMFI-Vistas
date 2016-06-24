package pe.pucp.edu.pe.siscomfi.bm.dao;

import java.util.ArrayList;
import pe.pucp.edu.pe.siscomfi.model.PartidoPolitico;
import pe.pucp.edu.pe.siscomfi.model.Reporte;

public interface DAOPartidoPolitico {
	void add(PartidoPolitico u);
	void update(PartidoPolitico u);
	void delete (int idPartido);
	ArrayList<PartidoPolitico> queryAll();
	PartidoPolitico queryByRepresentante(String representante);
	PartidoPolitico queryById(int idPartido);
	PartidoPolitico queryByNombre(String nombre);
	ArrayList<PartidoPolitico> queryAllObservados();
	int contarAdherenteAceptado(int idPartido);
	int cantidadMinAdherentesAceptados(int idPartido);
	//por el momento anio esta hardcodeado con el valor = 2016
	ArrayList<Reporte> queryReporte(int idTipoProceso, int anio, int idfase, int estadoPartido_enProceso);
}
