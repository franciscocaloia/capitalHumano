package b1.capitalHumano.cuestionario;

import java.util.HashSet;
import java.util.Set;

import b1.capitalHumano.Pregunta;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name="FactorCuestionario")
@Table(name="factorcuestionario")
public class FactorCuestionario {
	@Id
	@Column(name="idFactor")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer idFactor;
	@Column(name="nombreFactor")
	String nombreFactor;
	@ManyToOne
	@JoinColumn(name = "idComp")
	CompetenciaCuestionario competenciaCuestionario;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = PreguntaCuestionario.class)
	@JoinColumn(name = "idFactor",updatable= false)
	Set<PreguntaCuestionario> preguntasCuestionario = new HashSet<PreguntaCuestionario>();
	
	public FactorCuestionario() {
		
	}
	
	public Integer getIdFactor() {
		return idFactor;
	}
	public void setIdFactor(Integer idFactor) {
		this.idFactor = idFactor;
	}
	public String getNombreFactor() {
		return nombreFactor;
	}
	public void setNombreFactor(String nombreFactor) {
		this.nombreFactor = nombreFactor;
	}
	public Set<PreguntaCuestionario> getPreguntasCuestionario() {
		return preguntasCuestionario;
	}
	public void setPreguntasCuestionario(Set<PreguntaCuestionario> preguntasCuestionario) {
		this.preguntasCuestionario = preguntasCuestionario;
	}
	public void addPreguntaCuestionario(PreguntaCuestionario preguntaCuestionario) {
		this.preguntasCuestionario.add(preguntaCuestionario);
	}
	public void setCompetenciaCuestionario(CompetenciaCuestionario competenciaCuestionario) {
		this.competenciaCuestionario=competenciaCuestionario;
	}
}
