package pe.pucp.edu.pe.siscomfi.model;

import java.util.Date;

public class PartidoPolitico {
	private int idPartidoPolitco;
	private String nombrePartido;
	private String representante;
	private String correo;
	private String direccion;
	private String telefono;
	
	private int idDistrito;
	
	private Date fechaRegistro;
	private String estadoActivo;
	
	
	public String getEstadoActivo() {
		return estadoActivo;
	}
	public void setEstadoActivo(String estadoActivo) {
		this.estadoActivo = estadoActivo;
	}
	public int getIdPartidoPolitico() {
		return idPartidoPolitco;
	}
	public void setIdPartidoPolitco(int idPartidoPolitco) {
		this.idPartidoPolitco = idPartidoPolitco;
	}
	public String getNombrePartido() {
		return nombrePartido;
	}
	public void setNombrePartido(String nombrePartido) {
		this.nombrePartido = nombrePartido;
	}
	public String getRepresentante() {
		return representante;
	}
	public void setRepresentante(String representante) {
		this.representante = representante;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public int getIdDistrito() {
		return idDistrito;
	}
	public void setIdDistrito(int idDistrito) {
		this.idDistrito = idDistrito;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	

}
