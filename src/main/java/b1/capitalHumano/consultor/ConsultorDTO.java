package b1.capitalHumano.consultor;

public class ConsultorDTO {
private String usuario;
private String contraseña;
public ConsultorDTO(Consultor consultor) {
	usuario=  consultor.getNombre();
	contraseña = consultor.getContraseña();

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
