package b1.capitalHumano;

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


@Entity(name="Pregunta_Opcion")
@Table(name="Pregunta_Opción")
public class PonderacionOpcion {
	@Id
	@ManyToOne(fetch = FetchType.EAGER ,targetEntity = Pregunta.class)
	@JoinColumn(name = "idPregunta" )
	Pregunta pregunta;
	@Id
	@ManyToOne(fetch = FetchType.EAGER ,targetEntity = Opcion.class)
	@JoinColumn(name = "idOpcion" )
	Opcion opcion;
	@Column(name = "ponderacion")
	Integer ponderacion;
	public Pregunta getPregunta() {
		return pregunta;
	}
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
	public Opcion getOpcion() {
		return opcion;
	}
	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}
	public Integer getPonderacion() {
		return ponderacion;
	}
	public void setPonderacion(Integer ponderacion) {
		this.ponderacion = ponderacion;
	}
}
