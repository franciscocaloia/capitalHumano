package b1.capitalHumano;

import java.util.ArrayList;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity(name="Pregunta")
@Table(name="pregunta")
public class Pregunta {
	@Id
	@OneToMany
	@JoinColumn(name = "idPregunta")
	Set<Pregunta_Opcion> preguntas;

	@Column(name = "idFactor")
	Integer idFactor;

	@Column(name = "pregunta")
	String pregunta;
	@Column(name = "eliminado")
	Boolean eliminado;

	@ManyToOne
	@JoinColumn(name = "idOpcionRt")
	OpcionRespuesta opcionRespuesta;

}
