package b1.capitalHumano.competencia;

import java.util.ArrayList;
import java.util.List;

import b1.capitalHumano.Singleton;


public class ControllerCompetencia implements Singleton  { 
	
		private static ControllerCompetencia instance = null;
	public ControllerCompetencia(){};
	public static ControllerCompetencia getInstance() {
		// TODO Auto-generated method stub
		if (instance == null) {
			instance = new ControllerCompetencia();
		}
		return instance;

	}
	
	
	static CompetenciaDAO competenciaDAO = new CompetenciaDAOImp();
	public static List<CompetenciaDTO> getCompetencias() {

		List<Competencia> competencias = competenciaDAO.getAllInstances();
		List<CompetenciaDTO> competenciasDTO = new ArrayList<>();
		for(Competencia competencia : competencias) {
			competenciasDTO.add(new CompetenciaDTO(competencia.getIdComp(),competencia.getNombreComp(),competencia.getDescripcion()));
		}
		return competenciasDTO;
	}
}
