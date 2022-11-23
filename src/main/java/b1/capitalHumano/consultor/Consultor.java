package b1.capitalHumano.consultor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name="Consultor")
@Table(name="consultor")
public class Consultor {


	@Id
	@Column(name="usuario")
	private String usuario;
	@Column(name="contraseña")
	private String contraseña;
	public String getContraseña() {
		// TODO Auto-generated method stub
		return contraseña;
	}
	public String getNombre() {
		// TODO Auto-generated method stub
		return usuario;
	}
	public void  setContraseña(String contraseña) {
		// TODO Auto-generated method stub
		this.contraseña= contraseña;
	}
	public void setNombre(String usuario) {
		// TODO Auto-generated method stub
	this.usuario = usuario;
	}
}
