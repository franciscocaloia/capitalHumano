package b1.capitalHumano.puesto;

import b1.capitalHumano.competencia.Competencia;

public class PonderacionNecesariaDTO {
	Integer ponderacionNecesaria;
	Integer idComp;
	String competencia;
	Boolean evaluable;
	
	
	public PonderacionNecesariaDTO() {

	}
	
	public PonderacionNecesariaDTO(Integer ponderacionNecesaria, Integer idComp) {
		super();
		this.ponderacionNecesaria = ponderacionNecesaria;
		this.idComp = idComp;
	}

	public PonderacionNecesariaDTO(Integer ponderacionNecesaria, Integer idComp, String competencia) {
		super();
		this.ponderacionNecesaria = ponderacionNecesaria;
		this.idComp = idComp;
		this.competencia = competencia;
	}
	public PonderacionNecesariaDTO(PonderacionNecesaria ponderacionNecesaria) {
		this.ponderacionNecesaria = ponderacionNecesaria.getPonderacionNecesaria();
		this.idComp = ponderacionNecesaria.getCompetencia().getIdComp();
		this.competencia = ponderacionNecesaria.getCompetencia().getNombreComp();
		this.evaluable = ponderacionNecesaria.getCompetencia().isEvaluable();
	}
	
	public String getCompetencia() {
		return competencia;
	}

	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}
	
	public void setEvaluable(Boolean evaluable) {
		this.evaluable = evaluable;
	}

	public Boolean getEvaluable() {
		return this.evaluable;
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