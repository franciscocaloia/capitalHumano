package b1.capitalHumano.competencia;


import java.util.Set;

import b1.capitalHumano.Factor;
import b1.capitalHumano.puesto.PonderacionNecesaria;
import b1.capitalHumano.puesto.Puesto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;

@Entity(name="Competencia")
@Table(name="competencia")
public class Competencia {
	@Id
	@Column(name="idComp" )
	Integer idComp;
	@Column(name="nombreComp")
	String nombreComp;
	@Column(name="descripción")
	String descripcion;
	@Column(name="eliminado")
	Boolean eliminado;
	

	

	//@OneToMany(cascade =  CascadeType.ALL ,fetch = FetchType.EAGER,orphanRemoval = true,targetEntity=Factor.class)
	//@JoinColumn(name = "idComp")
	//Set<Factor> Factores ;

//	ArrayList<Integer> idDactores

	public Competencia(){
	}
	 @Override
	    public String toString() {
	        return nombreComp;
	    }
	public Integer getIdComp() {
		return idComp;
	}
	public void setIdComp(Integer idComp) {
		this.idComp = idComp;
	}
	public String getNombreComp() {
		return nombreComp;
	}
	public void setNombreComp(String nombreComp) {
		this.nombreComp = nombreComp;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getEliminado() {
		return eliminado;
	}
	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}
}
