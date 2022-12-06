package b1.capitalHumano.consultor;

public class ConsultorDTO {
private String usuario;
private String contraseña;
private Integer idConsultor;
public ConsultorDTO(Consultor consultor) {
	usuario=  consultor.getNombre();
	contraseña = consultor.getContraseña();
	this.idConsultor = consultor.getIdConsultor();
}

public String getUsuario() {
	return usuario;
}

public void setUsuario(String usuario) {
	this.usuario = usuario;
}

public Integer getIdConsultor() {
	return idConsultor;
}

public void setIdConsultor(Integer idConsultor) {
	this.idConsultor = idConsultor;
}

public ConsultorDTO() {
	// TODO Auto-generated constructor stub
}

public String getContraseña() {
	return contraseña;
}
public void setContraseña(String contraseña) {
	this.contraseña = contraseña;
}
public String getNombre() {
	return usuario;
}
public void setNombre(String usuario) {
	this.usuario = usuario;
}

}
