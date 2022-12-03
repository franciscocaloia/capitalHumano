package b1.capitalHumano.puesto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import b1.capitalHumano.empresa.Empresa;
import b1.capitalHumano.evaluacion.Evaluacion;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name="Puesto")
@Table(name="puesto")
public class Puesto {

@Id
@Column(name="idPuesto")
Integer idPuesto;
@Column(name="nombrePuesto")
 String nombrePuesto;
@Column(name="descripción")
 String descripcionPuesto;
@ManyToOne
@JoinColumn(name = "idEmpresa")
 Empresa empresa;
@Column(name="eliminado")
 Boolean eliminado;

@OneToMany(cascade =  CascadeType.ALL ,fetch = FetchType.EAGER,orphanRemoval = true,targetEntity=PonderacionNecesaria.class)
@JoinColumn(name="idPuesto")
Set<PonderacionNecesaria> caracteristicas;

@OneToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER,orphanRemoval = false,targetEntity=Evaluacion.class)
@JoinColumn(name="idPuesto", insertable = false,updatable = false)
Set<Evaluacion> evaluaciones ;

public Puesto(Integer idPuesto, String nombrePuesto, String descripcion, Empresa empresa,Set<PonderacionNecesaria> caracteristicas) {
	this.idPuesto = idPuesto;
	this.nombrePuesto = nombrePuesto;
	this.descripcionPuesto = descripcion;
	this.empresa = empresa;
	this.eliminado = false;
	this.caracteristicas = caracteristicas;
	}
 public Puesto() {
	 this.eliminado=false;
}

public Set<Evaluacion> getEvaluaciones() {
	return evaluaciones;
}

public void setEvaluaciones(Set<Evaluacion> evaluaciones) {
	this.evaluaciones = evaluaciones;
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
	

	public Boolean getEliminado() {
		return this.eliminado;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa=empresa;
	}
	public Empresa getEmpresa() {
		return this.empresa;
	}
	public Set<PonderacionNecesaria> getCaracteristicas(){
		return this.caracteristicas;
	}
	
	public void setCaracteristicas(Set<PonderacionNecesaria> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public void setEliminado(boolean eliminado) {
		// TODO Auto-generated method stub
		this.eliminado = eliminado;
	}
}