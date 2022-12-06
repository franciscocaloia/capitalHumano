package b1.capitalHumano.candidato;

import java.util.Date;
import java.util.Set;

import b1.capitalHumano.Cuestionario;
import b1.capitalHumano.competencia.Competencia;
import b1.capitalHumano.consultor.Consultor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "Candidato")
@Table(name = "candidato")
// VERIFICAAR CON LA DB
public class Candidato {

	@Id
	@Column(name = "idCandidato")
	Integer idCandidato;
//	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Consultor.class)
//	@JoinColumn(name = "idConsultor")
	// Consultor consultor;
	@Column(name = "clave")
	String clave;
	@Column(name = "nombre")
	String nombre;
	@Column(name = "apellido")
	String apellido;
	@Column(name = "nacionalidad")
	String nacionalidad;
	@Column(name = "escolaridad")
	String escolaridad;
	@Column(name = "dni")
	Integer DNI;
	@Column(name = "email")
	String email;
	@Column(name = "fechaNacimiento")
	Date fechaNacimiento;
	@Column(name = "eliminado")
	Boolean eliminado;
	@Column(name = "tipo")
	String tipo;

	
	
	//@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name = "idCandidato")
	//Set<TipoDNI> tipo;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "idCandidato")
	Cuestionario cuestionarioActivo;
//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name = "idCandidato")
//	Set<Cuestionario> cuestionarioRealizo;;

//	public Consultor getConsultor() {
//		return consultor;
//	}

//	public void setConsultor(Consultor consultor) {
//		this.consultor = consultor;
//	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Integer getDNI() {
		return DNI;
	}

	public void setDNI(Integer DNI) {
		this.DNI = DNI;
	}

	public String getTipo() {
		return tipo;
	}

	public Integer getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(Integer idCandidato) {
		this.idCandidato = idCandidato;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Cuestionario getActivo() {
		return cuestionarioActivo;
	}
}
