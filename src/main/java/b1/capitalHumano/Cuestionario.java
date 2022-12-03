package b1.capitalHumano;

import java.util.Date;

import b1.capitalHumano.candidato.Candidato;
import b1.capitalHumano.empresa.Empresa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "Cuestionario")
@Table(name = "cuestionario")
public class Cuestionario {

	@Id
	@Column(name = "idCuestionario")
	Integer idCuestionario;

	@Column(name = "idEvaluación")
	String idEvaluación;
	@Column(name = "idPuesto")
	String idPuesto;

	@ManyToOne
	@JoinColumn(name = "idCandidato")
	Candidato candidatoRealzia;

	@ManyToOne
	@JoinColumn(name = "idCandidato")
	Candidato candidatoRealizo;
	@Column(name = "clave")
	Integer clave;

	@Column(name = "fechaInicio")
	Date fechaInicio;
	@Column(name = "ultimoIngreso")
	Date ultimoIngreso;
	@Column(name = "nroIngresos")
	Integer nroIngresos;
	@Column(name = "eliminado")
	Integer eliminado;

}
