package b1.capitalHumano.empresa;

public class EmpresaDTO {
	private Integer idEmpresa;
	private String nombreEmpresa;
	public EmpresaDTO() {
		
	}
	public EmpresaDTO(Integer idEmpresa, String nombreEmpresa) {
		super();
		this.idEmpresa = idEmpresa;
		this.nombreEmpresa = nombreEmpresa;
	}
	public Integer getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	
	@Override 
	public String toString() {
		return this.nombreEmpresa;
	}
}
