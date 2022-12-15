package b1.capitalHumano.puesto;

import b1.capitalHumano.competencia.Competencia;
import b1.capitalHumano.evaluacion.Evaluacion;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "PonderacionNecesaria")
@Table(name = "puesto_competencia")

public class PonderacionNecesaria {
	@Id
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Competencia.class)
	@JoinColumn(name = "idComp")
	Competencia competencia;

	// no se si es bidireccional
	@Id
	@ManyToOne( fetch = FetchType.EAGER, targetEntity = Puesto.class)
	@JoinColumn(name = "idPuesto" )
	Puesto puesto;

	@Column(name = "ponderacionNecesaria", nullable = true)
	Integer ponderacionNecesaria;
	
	public PonderacionNecesaria() {

	}

	public PonderacionNecesaria(Puesto puesto, Integer ponderacion, Competencia competencia) {
		this.competencia = competencia;
		this.puesto = puesto;
		this.ponderacionNecesaria = ponderacion;
	}

	public Puesto getPuesto() {
		return puesto;
	}
	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}

	public Integer getPonderacionNecesaria() {
		return this.ponderacionNecesaria;
	}

	public Competencia getCompetencia() {
		return this.competencia;
	}

	@Override
	public String toString() {
		return this.competencia.getNombreComp() + this.ponderacionNecesaria;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PonderacionNecesaria)) {
			return false;
		}
		PonderacionNecesaria other = (PonderacionNecesaria) obj;
		return (competencia.getIdComp().equals(other.competencia.getIdComp()));
	}

	public void setPonderacionNecesaria(Integer ponderacionNecesaria) {
		this.ponderacionNecesaria = ponderacionNecesaria;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}

	@Override
	public int hashCode() {
	//	//ELIMINAR
	//System.out.println(competencia + " " +puesto + " " + ponderacionNecesaria);
		//
		return 31 * competencia.getIdComp().hashCode() + ponderacionNecesaria.hashCode();
	}
}