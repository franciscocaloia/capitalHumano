package b1.capitalHumano.candidato;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import b1.capitalHumano.cuestionario.Cuestionario;
import b1.capitalHumano.cuestionario.CuestionarioDTO;

public class CandidatoDTO {
	private Integer idCandidato;
	private String clave;
	private String nombre;
	private String apellido;
	private Integer DNI;
	private String tipo;
	private CuestionarioDTO cuestionarioActivo;
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

	public CuestionarioDTO getCuestionarioActivo() {
		return cuestionarioActivo;
	}

	public void setCuestionarioActivo(CuestionarioDTO cuestionarioActivo) {
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
	
	public CuestionarioDTO getActivo() {
		return cuestionarioActivo;
	}

	public Integer getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(Integer idCandidato) {
		this.idCandidato = idCandidato;
	}

}
