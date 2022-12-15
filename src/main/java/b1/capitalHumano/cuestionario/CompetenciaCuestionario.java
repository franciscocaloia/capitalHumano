package b1.capitalHumano.cuestionario;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name="CompetenciaCuestionario")
@Table(name = "competenciacuestionario")
public class CompetenciaCuestionario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idComp")
	Integer idComp;
	@Column(name = "nombreComp")
	String nombreComp;
	@Column(name = "ponderacionNecesaria")
	Integer ponderacionNecesaria;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = FactorCuestionario.class)
	@JoinColumn(name = "idComp",updatable=false)
	Set<FactorCuestionario> factoresCuestionario = new HashSet<FactorCuestionario>();
	
	public CompetenciaCuestionario() {
		
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

	public Integer getPonderacionNecesaria() {
		return ponderacionNecesaria;
	}

	public void setPonderacionNecesaria(Integer ponderacionNecesaria) {
		this.ponderacionNecesaria = ponderacionNecesaria;
	}

	public Set<FactorCuestionario> getFactoresCuestionario() {
		return factoresCuestionario;
	}

	public void setFactoresCuestionario(Set<FactorCuestionario> factoresCuestionario) {
		this.factoresCuestionario = factoresCuestionario;
	}

	public void addFactorCuestionario(FactorCuestionario factorCuestionario) {
		this.factoresCuestionario.add(factorCuestionario);
	}
}
