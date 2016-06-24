package pe.pucp.edu.pe.siscomfi.model;

public class RegistroElector {
	String nombre;
	String apellidoPaterno;
	String apellidoMaterno;
	String DNI;
	int ubigeo;
	String rHuella;
	String rFirma;
	int habilitado;

	public RegistroElector(String nombre, String apellidoPaterno, String apellidoMaterno, String dNI, int ubigeo,
			String rHuella, String rFirma, int habilitado) {
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		DNI = dNI;
		this.ubigeo = ubigeo;
		this.rHuella = rHuella;
		this.rFirma = rFirma;
		this.habilitado = habilitado;
	}

	public RegistroElector() {

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

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public int getUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(int ubigeo) {
		this.ubigeo = ubigeo;
	}

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

	public int getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(int habilitado) {
		this.habilitado = habilitado;
	}

	@Override
	public String toString() {
		return this.apellidoPaterno + " " + this.apellidoMaterno + " " + this.nombre + " " + this.DNI + " "
				+ this.ubigeo + " " + this.rHuella + " " + this.rFirma + " " + this.habilitado;
	}
}
