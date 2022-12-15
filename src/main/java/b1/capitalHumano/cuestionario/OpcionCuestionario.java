package b1.capitalHumano.cuestionario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name="OpcionCuestionario")
@Table(name="opcióncuestionario")
public class OpcionCuestionario {
	@Id
	@Column(name="idOpcion")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer idOpcion;
	@Column(name="descripcion")
	String descripcion;
	@Column(name="ponderacion")
	Integer ponderacion;
	
	public OpcionCuestionario() {
		
	}
	public Integer getIdOpcion() {
		return idOpcion;
	}
	public void setIdOpcion(Integer idOpcion) {
		this.idOpcion = idOpcion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getPonderacionNecesaria() {
		return ponderacion;
	}
	public void setPonderacionNecesaria(Integer ponderacionNecesaria) {
		this.ponderacion = ponderacionNecesaria;
	}
	
}
