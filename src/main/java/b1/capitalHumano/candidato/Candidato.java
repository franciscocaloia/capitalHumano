package b1.capitalHumano.candidato;

import java.util.Set;

import b1.capitalHumano.Cuestionario;
import b1.capitalHumano.consultor.Consultor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity(name="Candidato")
@Table(name="candidato")
// VERIFICAAR CON LA DB
public class Candidato {

	@Id
	@Column(name="idCandidato")
	Integer idCandidato;
	public Integer getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(Integer idCandidato) {
		this.idCandidato = idCandidato;
	}
	@Column(name="dni")
	private String DNI;
	@Column(name="clave")
	private String clave;
	@Column(name = "nombre")
	String nombre;
	@Column(name = "apellido")
	String apellido;
	@ManyToOne
	@JoinColumn(name = "idConsultor")
	Consultor consultor;
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
	@Column(name="dni")
	private String dni;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "tipoDNI")
	private Set<TipoDNI> tipoDNI;
	private Cuestionario cuestionarioActivo;

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String DNI) {
		this.DNI = DNI;
	}

	public String getTipo() {
		return dni;
	}

	public Set<TipoDNI> getTipoDNI() {
		return tipoDNI;
	}

	public void setTipoDNI(Set<TipoDNI> tipoDNI) {
		this.tipoDNI = tipoDNI;
	}

	public void setTipo(String tipo) {
		this.dni = tipo;
	}
	public Cuestionario getActivo() {
	return cuestionarioActivo;
	}
}
