package pe.pucp.edu.pe.siscomfi.model;

public class Planillon {
	private int idPlanillon;
	private double tiempoProcesado;
	private int idPartido;
	private int idProceso;
	
	public Planillon(int idPlanillon, double tiempoProcesado, int idPartido, int idProceso) {
		this.idPlanillon = idPlanillon;
		this.tiempoProcesado = tiempoProcesado;
		this.idPartido = idPartido;
		this.idProceso = idProceso;
	}
	public int getIdPlanillon() {
		return idPlanillon;
	}
	public void setIdPlanillon(int idPlanillon) {
		this.idPlanillon = idPlanillon;
	}
	public double getTiempoProcesado() {
		return tiempoProcesado;
	}
	public void setTiempoProcesado(double tiempoProcesado) {
		this.tiempoProcesado = tiempoProcesado;
	}
	public int getIdPartido() {
		return idPartido;
	}
	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}
	public int getIdProceso() {
		return idProceso;
	}
	public void setIdProceso(int idProceso) {
		this.idProceso = idProceso;
	}
	
}
