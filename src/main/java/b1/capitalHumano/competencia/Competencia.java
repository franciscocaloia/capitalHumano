package b1.capitalHumano.competencia;


import java.util.Set;

import b1.capitalHumano.puesto.PonderacionNecesaria;
import b1.capitalHumano.puesto.Puesto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;

@Entity(name="Competencia")
@Table(name="competencia")
public class Competencia {
	@Id
	@Column(name="idComp", nullable = false )
	Integer idComp;
	@Column(name="nombreComp")
	String nombreComp;
	@Column(name="descripción")
	String descripcion;
	@Column(name="eliminado")
	Boolean eliminado;
	

	//@OneToMany
	//@JoinColumn(name = "idComp")
	//Set<PonderacionNecesaria> listaPonderacion ;

//	ArrayList<Integer> idDactores
//	ArrayList<Factor> factores;
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
