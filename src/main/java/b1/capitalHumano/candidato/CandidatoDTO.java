package b1.capitalHumano.candidato;

import b1.capitalHumano.Cuestionario;

public class CandidatoDTO {
	private String clave;
	private String DNI;
	private String tipo;
	private Cuestionario cuestionarioActivo;
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

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String DNI) {
		this.DNI = DNI;
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

}
