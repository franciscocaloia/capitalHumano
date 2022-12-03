package b1.capitalHumano.puesto;

public class PonderacionNecesariaDTO {
	Integer ponderacionNecesaria;
	Integer idComp;
	public PonderacionNecesariaDTO() {
		
	}
	public PonderacionNecesariaDTO(Integer ponderacionNecesaria, Integer idComp) {
		super();
		this.ponderacionNecesaria = ponderacionNecesaria;
		this.idComp = idComp;
	}
	public Integer getPonderacionNecesaria() {
		return ponderacionNecesaria;
	}
	public void setPonderacionNecesaria(Integer ponderacionNecesaria) {
		this.ponderacionNecesaria = ponderacionNecesaria;
	}
	public Integer getIdComp() {
		return idComp;
	}
	public void setIdComp(Integer idComp) {
		this.idComp = idComp;
	}

}