package b1.capitalHumano;

import java.util.ArrayList;
import java.util.Set;

import b1.capitalHumano.puesto.PonderacionNecesaria;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "Pregunta")
@Table(name = "pregunta")
public class Pregunta {
	@Id
	@Column(name = "idPregunta")
	Integer idPregunta;
	@Column(name = "pregunta")
	String pregunta;
	@Column(name = "eliminado")
	Boolean eliminado;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "idPregunta")
	Set<PonderacionOpcion> ponderaciones;
	@ManyToOne(fetch = FetchType.EAGER,targetEntity=OpcionRespuesta.class)
	@JoinColumn(name = "idOpcionRt")
	OpcionRespuesta opcionRespuesta;
	
	public Integer getIdPregunta() {
		return idPregunta;
	}
	public void setIdPregunta(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public Boolean getEliminado() {
		return eliminado;
	}
	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}
	public Set<PonderacionOpcion> getPonderaciones() {
		return ponderaciones;
	}
	public void setPonderaciones(Set<PonderacionOpcion> ponderaciones) {
		this.ponderaciones = ponderaciones;
	}
	public OpcionRespuesta getOpcionRespuesta() {
		return opcionRespuesta;
	}
	public void setOpcionRespuesta(OpcionRespuesta opcionRespuesta) {
		this.opcionRespuesta = opcionRespuesta;
	}
}
