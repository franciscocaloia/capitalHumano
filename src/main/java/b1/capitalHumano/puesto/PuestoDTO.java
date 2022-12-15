package b1.capitalHumano.puesto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import b1.capitalHumano.empresa.Empresa;

public class PuestoDTO {
	public PuestoDTO(Integer idPuesto, String nombrePuesto, Integer idEmpresa, String nombreEmpresa, String descripción,
			Boolean eliminado) {
		this.idPuesto = idPuesto;
		this.idEmpresa = idEmpresa;
		this.nombreEmpresa = nombreEmpresa;
		this.nombrePuesto = nombrePuesto;
		this.descripcionPuesto = descripción;
		this.eliminado = eliminado;
	}

	public PuestoDTO(Puesto puesto) {
		this.idPuesto = puesto.getIdPuesto();
		this.codigoPuesto = puesto.getCodigoPuesto();
		this.idEmpresa = puesto.getEmpresa().getIdEmpresa();
		this.nombreEmpresa = puesto.getEmpresa().getNombreEmpresa();
		this.nombrePuesto = puesto.getNombrePuesto();
		this.descripcionPuesto = puesto.getDescripcionPuesto();
		this.eliminado = puesto.getEliminado();
		this.evaluable= puesto.isEvaluable();
	}

	public PuestoDTO() {
		// TODO Auto-generated constructor stub
	}

	private Integer idPuesto;
	private Integer idEmpresa;
	private String nombreEmpresa;
	private String nombrePuesto;
	private String descripcionPuesto;
	private Boolean eliminado;
	private Integer codigoPuesto;
	private Boolean evaluable;
	private Set<PonderacionNecesariaDTO> caracteristicasDTO = new HashSet<PonderacionNecesariaDTO>();

	public int getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombreEmpresa() {
		return this.nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getNombrePuesto() {
		return nombrePuesto;
	}

	public void setNombrePuesto(String nombrePuesto) {
		this.nombrePuesto = nombrePuesto;
	}

	public String getDescripcionPuesto() {
		return descripcionPuesto;
	}

	public void setDescripcionPuesto(String descripción) {
		this.descripcionPuesto = descripción;
	}

	public boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}
	
	public void setCodigoPuesto(Integer codigo) {
		this.codigoPuesto= codigo;
	}
	
	public Integer getCodigoPuesto() {
		return this.codigoPuesto;
	}

	public void setCaracteristicasDTO(Set<PonderacionNecesariaDTO> caracteristicasDTO) {
		this.caracteristicasDTO = caracteristicasDTO;
	}

	public Set<PonderacionNecesariaDTO> getCaracteristicasDTO() {
		return this.caracteristicasDTO;
	}

	@Override
	public String toString() {
		return nombrePuesto;
	}

	public boolean getEvaluable() {
		return this.evaluable;
	}
}
