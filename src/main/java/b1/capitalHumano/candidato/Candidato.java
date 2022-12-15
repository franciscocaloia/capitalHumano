package b1.capitalHumano.candidato;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import b1.capitalHumano.competencia.Competencia;
import b1.capitalHumano.consultor.Consultor;
import b1.capitalHumano.cuestionario.Cuestionario;
import b1.capitalHumano.cuestionario.EstadoCuestionario;
import b1.capitalHumano.puesto.PonderacionNecesaria;
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
	@OneToMany( fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = Cuestionario.class)
	@JoinColumn(name = "idCandidato", updatable = false)
	Set<Cuestionario> cuestionariosFinalizados = new HashSet<Cuestionario>();
	
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
		for (Cuestionario cuestionario : cuestionariosFinalizados) {
			System.out.println(cuestionario.getEstado());
			if(cuestionario.getEstado().equals(EstadoCuestionario.Activo)){
				return cuestionario;
			}
		}
		return null;
	}

	public void setAtributos(CandidatoDTO candidatoDTO) {
		this.nombre=candidatoDTO.getNombre();
		this.apellido=candidatoDTO.getApellido();
		this.DNI=candidatoDTO.getDNI();
		this.tipo=candidatoDTO.getTipo();
		this.clave=candidatoDTO.getClave();
	}

	public void addCuestionario(Cuestionario cuestionario) {
		this.cuestionariosFinalizados.add(cuestionario);
	}
}
