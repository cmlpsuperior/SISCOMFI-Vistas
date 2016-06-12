package pe.pucp.edu.pe.siscomfi.model;

import java.util.Date;

public class Adherente {
	int idAdherente;
	int idDistrito;
	String nombre;
	String appPaterno;
	String appMaterno;
	String dni;
	Date fechaNacimiento;
	String rHuella;
	String rFirma;
	
	public String getrHuella() {
		return rHuella;
	}
	public void setrHuella(String rHuella) {
		this.rHuella = rHuella;
	}
	public String getrFirma() {
		return rFirma;
	}
	public void setrFirma(String rFirma) {
		this.rFirma = rFirma;
	}
	public int getIdAdherente() {
		return idAdherente;
	}
	public void setIdAdherente(int idAdherente) {
		this.idAdherente = idAdherente;
	}
	public int getIdDistrito() {
		return idDistrito;
	}
	public void setIdDistrito(int idDistrito) {
		this.idDistrito = idDistrito;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAppPaterno() {
		return appPaterno;
	}
	public void setAppPaterno(String appPaterno) {
		this.appPaterno = appPaterno;
	}
	public String getAppMaterno() {
		return appMaterno;
	}
	public void setAppMaterno(String appMaterno) {
		this.appMaterno = appMaterno;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}
