package b1.capitalHumano.candidato;

import b1.capitalHumano.Cuestionario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity(name="Candidato")
@Table(name="candidato")
// VERIFICAAR CON LA DB
public class Candidato {

	@Id
	@Column(name="dni")
	private String DNI;
	@Column(name="clave")
	private String clave;
	@Column(name="dni")
	private String tipo;
	
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
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Cuestionario getActivo() {
	return cuestionarioActivo;
	}
}
