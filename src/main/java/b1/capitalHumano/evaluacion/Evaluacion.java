package b1.capitalHumano.evaluacion;

import java.util.Date;

import b1.capitalHumano.puesto.Puesto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

@Entity(name="Evaluacion")
@Table(name="evaluación")
public class Evaluacion {
	@Id
	@Column(name="idEvaluación")
	private Integer idEvaluacion;

	@ManyToOne
	@JoinColumn(name="idPuesto", insertable = false, updatable = false)
	Puesto puesto;
	@Column(name="idPuesto")
	private Integer idPuesto;
	@Column(name="fecha")
	private Date fecha;
	public Integer getIdEvaluacion() {
		return idEvaluacion;
	}
	public void setIdEvaluacion(Integer idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}
	public Integer getIdPuesto() {
		return idPuesto;
	}
	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
