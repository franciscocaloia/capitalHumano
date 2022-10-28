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


@Entity(name="Puesto")
@Table(name="Puesto")
public class PuestoDTO {
	
	public PuestoDTO(Integer idPuesto,Integer idEmpresa,String nombrePuesto, String descripción,Boolean eliminado) {
		this.idPuesto = idPuesto;
		this.idEmpresa = idEmpresa;
		this.nombrePuesto = nombrePuesto;
		this.descripción = descripción;
		this.eliminado = eliminado;
	}
	public PuestoDTO() {
			//no eliminar este lo requiere hibernate
	}

//    private List<Caracteristicas> caracteristicas;
    @Id
  //  @GeneratedValue
    @Column(name="idPuesto")
    private int idPuesto;
    @Column(name="idEmpresa")
    private int idEmpresa; 
    @Column(name="nombrePuesto")
    private String nombrePuesto;
	@Column(name="descripción")
    private String descripción;
    @Column(name="eliminado")
    private boolean eliminado;

	   public int getIdPuesto() {
			return idPuesto;
		}
		public void setIdPuesto(int idPuesto) {
			this.idPuesto = idPuesto;
		}
		public int getIdEmpresa() {
			return idEmpresa;
		}
		public void setIdEmpresa(int idEmpresa) {
			this.idEmpresa = idEmpresa;
		}
		public String getNombrePuesto() {
			return nombrePuesto;
		}
		public void setNombrePuesto(String nombrePuesto) {
			this.nombrePuesto = nombrePuesto;
		}
		public String getDescripción() {
			return descripción;
		}
		public void setDescripción(String descripción) {
			this.descripción = descripción;
		}
		public boolean isEliminado() {
			return eliminado;
		}
		public void setEliminado(boolean eliminado) {
			this.eliminado = eliminado;
		}
}
