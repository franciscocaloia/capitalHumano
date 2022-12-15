package b1.capitalHumano.cuestionario;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name="Bloque")
@Table(name="bloque")
public class Bloque {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idBloque")
	Integer idBloque;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = PreguntaCuestionario.class)
	@JoinColumn(name="idBloque",updatable=false)
	Set<PreguntaCuestionario> preguntasCuestionario = new HashSet<PreguntaCuestionario>();
	@Enumerated(EnumType.STRING)
	EstadoBloque estado;
	public Bloque() {
		
	}
	
	public Integer getIdBloque() {
		return idBloque;
	}

	public void setIdBloque(Integer idBloque) {
		this.idBloque = idBloque;
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

	public void setEstado(EstadoBloque estado) {
		this.estado=estado;
	}

}
