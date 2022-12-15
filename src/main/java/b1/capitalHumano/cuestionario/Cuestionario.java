package b1.capitalHumano.cuestionario;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Generated;

import b1.capitalHumano.candidato.Candidato;
import b1.capitalHumano.empresa.Empresa;
import b1.capitalHumano.evaluacion.Evaluacion;
import b1.capitalHumano.puesto.PonderacionNecesaria;
import b1.capitalHumano.puesto.Puesto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "Cuestionario")
@Table(name = "cuestionario")
public class Cuestionario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idCuestionario")
	Integer idCuestionario;
	@ManyToOne()
	@JoinColumn(name="idEvaluacion")
	Evaluacion evaluacion;
	@ManyToOne()
	@JoinColumn(name = "idPuesto")
	Puesto puesto;
	@ManyToOne()
	@JoinColumn(name = "idCandidato")
	Candidato candidato;
	@Column(name = "clave")
	String clave;
	@Column(name = "fechaInicio")
	Date fechaInicio;
	@Column(name = "ultimoIngreso")
	Date ultimoIngreso;
	@Column(name = "nroIngresos")
	Integer nroIngresos;
	@Column(name = "eliminado")
	Integer eliminado;
	@Column(name="estado")
	@Enumerated(EnumType.STRING)
	EstadoCuestionario estadoCuestionario;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = CompetenciaCuestionario.class)
	@JoinColumn(name="idCuestionario")
	Set<CompetenciaCuestionario> competenciasCuestionario = new HashSet<CompetenciaCuestionario>();
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = Bloque.class)
	@JoinColumn(name="idCuestionario")
	Set<Bloque> bloques = new HashSet<Bloque>();
	
	public Cuestionario() {
		super();
	}
	public Integer getIdCuestionario() {
		return idCuestionario;
	}

	public void setIdCuestionario(Integer idCuestionario) {
		this.idCuestionario = idCuestionario;
	}

	public Evaluacion getEvaluación() {
		return this.evaluacion;
	}

	public void setEvaluación(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}

	public Puesto getPuesto() {
		return this.puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String string) {
		this.clave = string;
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

	public EstadoCuestionario getEstado() {
		return estadoCuestionario;
	}

	public void setEstado(EstadoCuestionario estado) {
		this.estadoCuestionario = estado;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public Candidato getCandidato() {
		return this.candidato;
	}
	
	public Set<CompetenciaCuestionario> getCompetenciasCuestionario(){
		return this.competenciasCuestionario;
	}
	
	public void addCompetenciaCuestionario(CompetenciaCuestionario competenciaCuestionario) {
		this.competenciasCuestionario.add(competenciaCuestionario);
	}
	
	public Set<Bloque> getBloques(){
		return this.bloques;
	}

	public void addBloque(Bloque bloque) {
		this.bloques.add(bloque);
	}
}
