package b1.capitalHumano;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity(name="Pregunta_Opcion")
@Table(name="Pregunta_Opción")
public class Pregunta_Opcion {

	@Id
	@OneToMany
	@JoinColumn(name = "idPregunta" )
	Pregunta pregunta;
	@Id
	@OneToMany
	@JoinColumn(name = "idOpción" )
	Opcion opcion;
	
	@Column(name = "ponderación")
	Integer ponderacion;
	
}
