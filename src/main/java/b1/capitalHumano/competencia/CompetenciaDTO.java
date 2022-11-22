package b1.capitalHumano.competencia;

import java.util.ArrayList;



public class CompetenciaDTO {	
	Integer idComp;
	String nombreComp;
	String descripcion;
	public CompetenciaDTO(Integer idComp, String nombreComp, String descripcion) {
		this.idComp = idComp;
		this.nombreComp = nombreComp;
		this.descripcion = descripcion;
		this.eliminado =false;
		
		
	}
	 @Override
	    public String toString() {
	        return nombreComp;
	    }
	public Integer getIdComp() {
		return idComp;
	}
	public void setIdComp(Integer idComp) {
		this.idComp = idComp;
	}
	public String getNombreComp() {
		return nombreComp;
	}
	public void setNombreComp(String nombreComp) {
		this.nombreComp = nombreComp;
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
	Boolean eliminado;
}

