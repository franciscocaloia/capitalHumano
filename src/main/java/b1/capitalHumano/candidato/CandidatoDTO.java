package b1.capitalHumano.candidato;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import b1.capitalHumano.Cuestionario;

public class CandidatoDTO {
	private String clave;
	private Integer DNI;

	private String nombre;
	private String apellido;
	private Integer idCandidato;
	String primerTipoDoc;
	private String tipo ;
	
	private Cuestionario cuestionarioActivo;

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

	public Cuestionario getCuestionarioActivo() {
		return cuestionarioActivo;
	}

	public void setCuestionarioActivo(Cuestionario cuestionarioActivo) {
		this.cuestionarioActivo = cuestionarioActivo;
	}

	public CandidatoDTO(Candidato candidato) {
		this.clave = candidato.getClave();
		this.DNI = candidato.getDNI();
	}

	public CandidatoDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String string) {
		this.clave = string;
	}

	public Integer getDNI() {
		return DNI;
	}

	public void setDNI(Integer integer) {
		this.DNI = integer;
	}

	

	public String getTipo() {

		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Cuestionario getActivo() {
		return cuestionarioActivo;
	}

	public Integer getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(Integer idCandidato) {
		this.idCandidato = idCandidato;
	}

}
