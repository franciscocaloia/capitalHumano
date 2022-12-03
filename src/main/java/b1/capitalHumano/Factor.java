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

@Entity(name = "Factor")
@Table(name = "factor")
public class Factor {


	@Column(name = "nombreFactor")
	String nombreFactor;
	@Column(name = "descripción")
	String descripcion;
	@Column(name = "eliminado")
	Boolean eliminado;
	@Column(name = "idComp")
	Integer competencia;

	@Id
	@OneToMany
	@JoinColumn(name = "idFactor")
	Set<Pregunta> preguntas;
}
