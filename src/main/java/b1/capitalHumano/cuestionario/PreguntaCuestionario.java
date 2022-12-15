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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name="PreguntaCuestionario")
@Table(name="preguntacuestionario")
public class PreguntaCuestionario {
	@Id
	@Column(name="idPregunta")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer idPregunta;
	@Column(name="descripcion")
	String descripcion;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="opcionSeleccionada")
	OpcionCuestionario opcionSeleccionada;
	@ManyToOne
	@JoinColumn(name="idFactor")
	FactorCuestionario factor;
	@ManyToOne
	@JoinColumn(name="idBloque")
	Bloque bloque;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = OpcionCuestionario.class)
	@JoinColumn(name="idPregunta")
	Set<OpcionCuestionario> opcionesCuestionario = new HashSet<OpcionCuestionario>();
	public PreguntaCuestionario() {
		
	}
	
	public Integer getIdPregunta() {
		return idPregunta;
	}
	public void setIdPregunta(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public OpcionCuestionario getOpcionSeleccionada() {
		return opcionSeleccionada;
	}
	public void setOpcionSeleccionada(OpcionCuestionario opcionSeleccionada) {
		this.opcionSeleccionada = opcionSeleccionada;
	}
	public FactorCuestionario getFactor() {
		return factor;
	}
	public void setFactor(FactorCuestionario factor) {
		this.factor = factor;
	}
	public Bloque getBloque() {
		return bloque;
	}
	public void setBloque(Bloque bloque) {
		this.bloque = bloque;
	}

	public void addOpcion(OpcionCuestionario opcionCuestionario) {
		this.opcionesCuestionario.add(opcionCuestionario);
	}
}
