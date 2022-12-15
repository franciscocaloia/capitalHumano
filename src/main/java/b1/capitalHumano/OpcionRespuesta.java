package b1.capitalHumano;

import java.util.ArrayList;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "OpcionRespuesta")
@Table(name = "opcionRespuesta")
public class OpcionRespuesta {

	@Id
	@Column(name = "idOpcionRt")
	Integer idOpcionRt;
	@Column(name = "nombre")
	String nombre;
	@Column(name = "descripcion")
	String descripcion;

	@Column(name = "eliminado")
	Boolean eliminado;
	@OneToMany
	@JoinColumn(name = "idOpcionRt")
	Set<Opcion> opciones;	
	public Integer getIdOpcionRt() {
		return idOpcionRt;
	}
	public void setIdOpcionRt(Integer idOpcionRt) {
		this.idOpcionRt = idOpcionRt;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getEliminado() {
		return eliminado;
	}
	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}
	public Set<Opcion> getOpciones() {
		return opciones;
	}
	public void setOpciones(Set<Opcion> opciones) {
		this.opciones = opciones;
	}
}
