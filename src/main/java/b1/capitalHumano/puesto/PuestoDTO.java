package b1.capitalHumano.puesto;

import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import b1.capitalHumano.Empresa;
import b1.capitalHumano.Caracteristicas;
@Entity
@Table(name="Puesto")
public class PuestoDTO {

//    private List<Caracteristicas> caracteristicas;
    @Id
    @Column(name="idPuesto")
    private int idPuesto;
    @Column(name="idEmpresa")
    private int idEmpresa; 
    @Basic
    @Column(name="nombrePuesto")
    private String nombrePuesto;
    @Basic
    @Column(name="descripción")
    private String descripción;
    @Column(name="eliminado")
    private boolean eliminado;
	public PuestoDTO(Integer idPuesto,Integer idEmpresa,String nombrePuesto, String descripción,Boolean eliminado) {
		this.idPuesto = idPuesto;
		this.idEmpresa = idEmpresa;
		this.nombrePuesto = nombrePuesto;
		this.descripción = descripción;
		this.eliminado = eliminado;
	}
}
