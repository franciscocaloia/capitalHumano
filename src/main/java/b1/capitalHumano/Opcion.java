package b1.capitalHumano;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity(name="Opcion")
@Table(name="opcion")
public class Opcion {

	@Id
	@OneToMany
	@JoinColumn(name = "idOpcionRt")
	Set<Pregunta_Opcion> opciones;

	

	@Column(name = "descripción")
	String descripcion;
	@Column(name = "eliminado")
	Boolean eliminado;

}
