package b1.capitalHumano.puesto;

import java.util.ArrayList;

import b1.capitalHumano.Empresa;
import b1.capitalHumano.PonderacionNecesaria;

public class Puesto {

	Integer idPuesto;
 String nombrePuesto;
 String descripcionPuesto;
 Empresa empresa;
 Boolean eliminado;
 ArrayList<PonderacionNecesaria> caracteristicas;

public Puesto(Integer idPuesto, String nombrePuesto, String descripcion, Empresa empresa) {
	this.idPuesto = idPuesto;
	this.nombrePuesto = nombrePuesto;
	this.descripcionPuesto = descripcion;
	this.empresa = empresa;
	this.eliminado = false;
	}

 public Integer getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

	public String getNombrePuesto() {
		return nombrePuesto;
	}

	public void setNombrePuesto(String nombrePuesto) {
		this.nombrePuesto = nombrePuesto;
	}

	public String getDescripcionPuesto() {
		return descripcionPuesto;
	}

	public void setDescripcion(String descripcion) {
		this.descripcionPuesto = descripcion;
	}
	
	public String getNombreEmpresa() {
		return this.empresa.getNombreEmpresa();
	}

	public Boolean getEliminado() {
		return this.eliminado;
	}
}