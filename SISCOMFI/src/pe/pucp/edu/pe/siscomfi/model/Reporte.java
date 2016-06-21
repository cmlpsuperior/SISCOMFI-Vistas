package pe.pucp.edu.pe.siscomfi.model;

import java.util.Date;

public class Reporte {
	private String partido;
	private int anio;
	private int idProceso;
	private String tipoProceso;
	private String Fase1;
	private String Fase2;
	private int numeroAdherentes;
	private String estadoFinal;
	
	public String getPartido() {
		return partido;
	}
	public void setPartido(String partido) {
		this.partido = partido;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public int getIdProceso() {
		return idProceso;
	}
	public void setIdProceso(int idProceso) {
		this.idProceso = idProceso;
	}
	public String getTipoProceso() {
		return tipoProceso;
	}
	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
	}
	public String getFase1() {
		return Fase1;
	}
	public void setFase1(String fase1) {
		Fase1 = fase1;
	}
	public String getFase2() {
		return Fase2;
	}
	public void setFase2(String fase2) {
		Fase2 = fase2;
	}
	public int getNumeroAdherentes() {
		return numeroAdherentes;
	}
	public void setNumeroAdherentes(int numeroAdherentes) {
		this.numeroAdherentes = numeroAdherentes;
	}
	public String getEstadoFinal() {
		return estadoFinal;
	}
	public void setEstadoFinal(String estadoFinal) {
		this.estadoFinal = estadoFinal;
	}
		
}
