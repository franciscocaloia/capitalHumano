package b1.capitalHumano;

import java.util.Date;
import java.util.Set;

import b1.capitalHumano.candidato.Candidato;
import b1.capitalHumano.empresa.Empresa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

	// @ManyToOne
	// @JoinColumn(name = "idCandidato" , insertable = false, updatable = false)
	// Candidato candidatoRealzia;

	@OneToMany()
	@JoinColumn(name = "idCandidato")
	Set<Candidato> candidatoRealizo;
	
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

	public Integer getIdCuestionario() {
		return idCuestionario;
	}

	public void setIdCuestionario(Integer idCuestionario) {
		this.idCuestionario = idCuestionario;
	}

	public String getIdEvaluación() {
		return idEvaluación;
	}

	public void setIdEvaluación(String idEvaluación) {
		this.idEvaluación = idEvaluación;
	}

	public String getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(String idPuesto) {
		this.idPuesto = idPuesto;
	}

	public Integer getCandidatoRealizo() {
		return candidatoRealizo;
	}

	public void setCandidatoRealizo(Integer candidatoRealizo) {
		this.candidatoRealizo = candidatoRealizo;
	}

	public Integer getClave() {
		return clave;
	}

	public void setClave(Integer clave) {
		this.clave = clave;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getUltimoIngreso() {
		return ultimoIngreso;
	}

	public void setUltimoIngreso(Date ultimoIngreso) {
		this.ultimoIngreso = ultimoIngreso;
	}

	public Integer getNroIngresos() {
		return nroIngresos;
	}

	public void setNroIngresos(Integer nroIngresos) {
		this.nroIngresos = nroIngresos;
	}

	public Integer getEliminado() {
		return eliminado;
	}

	public void setEliminado(Integer eliminado) {
		this.eliminado = eliminado;
	}

}
