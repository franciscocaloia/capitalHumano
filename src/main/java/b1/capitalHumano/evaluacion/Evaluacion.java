package b1.capitalHumano.evaluacion;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import b1.capitalHumano.cuestionario.Cuestionario;
import b1.capitalHumano.puesto.Puesto;
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
import jakarta.persistence.Table;

@Entity(name="Evaluacion")
@Table(name="evaluación")
public class Evaluacion {
	@Id
	@Column(name="idEvaluacion")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idEvaluacion;
	@ManyToOne
	@JoinColumn(name="idPuesto")
	Puesto puesto;
	@Column(name="fecha")
	private Date fecha;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = Cuestionario.class)
	@JoinColumn(name = "idEvaluacion")
	Set<Cuestionario> cuestionarios = new HashSet<Cuestionario>();
	
	public Evaluacion() {
		super();
	}

	public Puesto getPuesto() {
		return this.puesto;
	}
	
	public void setPuesto(Puesto puesto) {
		this.puesto= puesto;
	}
	
	public Integer getIdEvaluacion() {
		return idEvaluacion;
	}
	public void setIdEvaluacion(Integer idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void addCuestionario(Cuestionario cuestionario) {
		this.cuestionarios.add(cuestionario);
	}
}
