package pe.pucp.edu.pe.siscomfi.model;

import java.util.Date;

public class Proceso {
	private int idProceso;
	private String descripcion;
	private Date fechaProceso1Inicio; //debe tener el formato YYYY-MM-DD
	private Date fechaProceso1Fin; //debe tener el formato YYYY-MM-DD
	private Date fechaProceso2Inicio; //debe tener el formato YYYY-MM-DD
	private Date fechaProceso2Fin;//debe tener el formato YYYY-MM-DD
	private int cantidadMinAdherentes;
	private int idTipoProceso;
	
	public int getIdProceso() {
		return idProceso;
	}
	public void setIdProceso(int idProceso) {
		this.idProceso = idProceso;
	}
	public String getDescripción() {
		return descripcion;
	}
	public void setDescripción(String descripción) {
		this.descripcion = descripción;
	}
	public Date getFechaProceso1Inicio() {
		return fechaProceso1Inicio;
	}
	public void setFechaProceso1Inicio(Date fechaProceso1Inicio) {
		this.fechaProceso1Inicio = fechaProceso1Inicio;
	}
	public Date getFechaProceso1Fin() {
		return fechaProceso1Fin;
	}
	public void setFechaProceso1Fin(Date fechaProceso1Fin) {
		this.fechaProceso1Fin = fechaProceso1Fin;
	}
	public Date getFechaProceso2Inicio() {
		return fechaProceso2Inicio;
	}
	public void setFechaProceso2Inicio(Date fechaProceso2Inicio) {
		this.fechaProceso2Inicio = fechaProceso2Inicio;
	}
	public Date getFechaProceso2Fin() {
		return fechaProceso2Fin;
	}
	public void setFechaProceso2Fin(Date fechaProceso2Fin) {
		this.fechaProceso2Fin = fechaProceso2Fin;
	}
	public int getCantidadMinAdherentes() {
		return cantidadMinAdherentes;
	}
	public void setCantidadMinAdherentes(int cantidadMinAdherentes) {
		this.cantidadMinAdherentes = cantidadMinAdherentes;
	}
	public int getIdTipoProceso() {
		return idTipoProceso;
	}
	public void setIdTipoProceso(int idTipoProceso) {
		this.idTipoProceso = idTipoProceso;
	}
	
	
	
}
