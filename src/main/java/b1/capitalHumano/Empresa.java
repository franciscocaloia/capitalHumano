package b1.capitalHumano;

public class Empresa {
	Integer idEmpresa;
	String nombreEmpresa;
	
	public Empresa(int idEmpresa, String nombreEmpresa) {
		// TODO Auto-generated constructor stub
		this.idEmpresa = idEmpresa;
		this.nombreEmpresa= nombreEmpresa;
	}
	public String getNombre() {
		return this.nombreEmpresa;
	}
	public Integer getId() {
		return this.idEmpresa;	
	}
}
