package pe.pucp.edu.pe.siscomfi.model;

import java.util.Date;

public class Usuario {
	private int idUsuario;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String correoElectronico;
	private String contrasenia;
	private Date fechaRegistro; //debe tener el formato YYYY-MM-DD
	private String dni;
	private String PreguntaSecreta; //nuevo
	private String RptaSecreta; //nuevo
	
	public Usuario(){
		
	}
	
	public Usuario(int idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno,
			String correoElectronico, String contrasenia, Date fechaRegistro, String dni, int idRol) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.correoElectronico = correoElectronico;
		this.contrasenia = contrasenia;
		this.fechaRegistro = fechaRegistro;
		this.dni = dni;
		this.idRol = idRol;
		this.PreguntaSecreta=PreguntaSecreta;
		this.RptaSecreta=RptaSecreta;
	}
	public String getPreguntaSecreta() {
		return PreguntaSecreta;
	}
	public void setPreguntaSecreta(String preguntaSecreta) {
		PreguntaSecreta = preguntaSecreta;
	}
	public String getRptaSecreta() {
		return RptaSecreta;
	}
	public void setRptaSecreta(String rptaSecreta) {
		RptaSecreta = rptaSecreta;
	}	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	private int idRol;
	
	public int getIdRol() {
		return idRol;
	}
	public void setIdRol(int rol) {
		this.idRol = rol;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
		
}
