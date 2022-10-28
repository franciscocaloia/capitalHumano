package b1.capitalHumano;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity(name="Empresa")
@Table(name="Empresa")
public class Empresa {


	@Id
	@Column(name="idEmpresa")
	private Integer idEmpresa;
	@Column(name="nombre")
	private String nombreEmpresa;
	public Empresa() {
	// nno eliminar hibernate pide constructor vacio
	}

	public Empresa(int idEmpresa, String nombreEmpresa) {
		// TODO Auto-generated constructor stub
		this.idEmpresa = idEmpresa;
		this.nombreEmpresa= nombreEmpresa;
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
	        return nombreEmpresa;
	    }
	
}
