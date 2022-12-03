package b1.capitalHumano;

import java.util.ArrayList;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity(name="OpcionRespuesta")
@Table(name="opcionRespuesta")
public class OpcionRespuesta {

	@Id
	@OneToMany
	@JoinColumn(name = "idOpcionRt")
	Set<Opcion> opciones;

	@Column(name = "nombre")
	String nombre;
	@Column(name = "descripción")
	String descripcion;
	@Column(name = "eliminado")
	Boolean eliminado;

}
