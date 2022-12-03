package b1.capitalHumano.competencia;

import java.util.ArrayList;
import java.util.List;


public class ControllerCompetencia {
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
