package b1.capitalHumano.puesto;


import b1.capitalHumano.competencia.Competencia;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity(name="PonderacionNecesaria")
@Table(name="puesto_competencia")
@PrimaryKeyJoinColumn(name="idPuesto")
public class PonderacionNecesaria {
	@Column(name="ponderaciónNecesaria")
	Integer ponderacionNecesaria;
	@Id
	@ManyToOne
	@JoinColumn(name="idComp")
	Competencia competencia;
	
	public PonderacionNecesaria() {
		
	}
	public PonderacionNecesaria(Integer ponderacion,Competencia competencia) {
		this.ponderacionNecesaria = ponderacion;
		this.competencia = competencia;
	}
	public Integer getPonderacionNecesaria() {
		return this.ponderacionNecesaria;
	}
	public Competencia getCompetencia() {
		return this.competencia;
	}
	
	@Override
	public String toString() {
		return this.competencia.getNombreComp()+this.ponderacionNecesaria;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (!(obj instanceof PonderacionNecesaria))  {
	        return false;
	    }
	    PonderacionNecesaria other = (PonderacionNecesaria)obj;
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
	    return 31*competencia.getIdComp().hashCode()+ponderacionNecesaria.hashCode();
	}
}