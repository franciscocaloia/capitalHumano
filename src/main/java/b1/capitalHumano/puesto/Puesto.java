package b1.capitalHumano.puesto;

import java.util.HashSet;
import java.util.Set;

import b1.capitalHumano.empresa.Empresa;
import b1.capitalHumano.evaluacion.Evaluacion;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "Puesto")
@Table(name = "puesto")
public class Puesto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idPuesto")
	Integer idPuesto; 
	@Column(name="codigoPuesto")
	Integer codigoPuesto;
	@Column(name = "nombrePuesto")
	String nombrePuesto;
	@Column(name = "descripcion")
	String descripcionPuesto;
	@ManyToOne
	@JoinColumn(name = "idEmpresa")
	Empresa empresa;
	@Column(name = "eliminado")
	Boolean eliminado;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = PonderacionNecesaria.class)
	@JoinColumn(name = "idPuesto", updatable = false)
	Set<PonderacionNecesaria> caracteristicas = new HashSet<PonderacionNecesaria>();

	public Puesto(Integer codigoPuesto, String nombrePuesto, String descripcion, Empresa empresa,
			Set<PonderacionNecesaria> caracteristicas) {
		this.codigoPuesto = codigoPuesto;
		this.nombrePuesto = nombrePuesto;
		this.descripcionPuesto = descripcion;
		this.empresa = empresa;
		this.eliminado = false;
		this.caracteristicas = caracteristicas;
	}
	public Puesto(PuestoDTO puestoDTO) {
		this.codigoPuesto = puestoDTO.getCodigoPuesto();
		this.nombrePuesto = puestoDTO.getNombrePuesto();
		this.descripcionPuesto = puestoDTO.getDescripcionPuesto();
		this.eliminado = false;
	}

	public Puesto() {
		this.eliminado = false;
	}

	public Integer getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
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

	public void setDescripcion(String descripcion) {
		this.descripcionPuesto = descripcion;
	}

	public Boolean getEliminado() {
		return this.eliminado;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;

	}

	public Empresa getEmpresa() {
		return this.empresa;
		
	}

	public Set<PonderacionNecesaria> getCaracteristicas() {
		return this.caracteristicas;
	}

	public void setCaracteristicas(Set<PonderacionNecesaria> caracteristicas) {
		this.caracteristicas = caracteristicas;

	}

	public void setEliminado(boolean eliminado) {
		// TODO Auto-generated method stub
		this.eliminado = eliminado;
	}
	
	public Integer getCodigoPuesto() {
		return codigoPuesto;
	}

	public void setCodigoPuesto(Integer codigoPuesto) {
		this.codigoPuesto = codigoPuesto;
	}

	public void setDescripcionPuesto(String descripcionPuesto) {
		this.descripcionPuesto = descripcionPuesto;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}
	public Boolean isEvaluable() {
		for (PonderacionNecesaria ponderacionNecesaria : caracteristicas) {
			if(ponderacionNecesaria.getCompetencia().isEvaluable()) {
				return true;
			}
		}
		return false;
	}
	  @Override
	    public String toString() {
	        return nombrePuesto;
	    }
}