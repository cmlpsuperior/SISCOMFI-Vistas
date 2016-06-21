package pe.pucp.edu.pe.siscomfi.model;

import java.util.Date;

public class Reporte {
	private int idReporte;
	private String nombre;
	private String representante;
	private int estadoPartido_enProceso;
	private float porcentaje;
	
	public int getIdReporte() {
		return idReporte;
	}
	public void setIdReporte(int idReporte) {
		this.idReporte = idReporte;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRepresentante() {
		return representante;
	}
	public void setRepresentante(String representante) {
		this.representante = representante;
	}
	public int getEstadoPartido_enProceso() {
		return estadoPartido_enProceso;
	}
	public void setEstadoPartido_enProceso(int estadoPartido_enProceso) {
		this.estadoPartido_enProceso = estadoPartido_enProceso;
	}
	public float getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}
	
}
