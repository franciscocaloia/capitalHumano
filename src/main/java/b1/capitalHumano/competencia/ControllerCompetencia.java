package b1.capitalHumano.competencia;

import java.util.ArrayList;
import java.util.List;


public class ControllerCompetencia {
	public static List<CompetenciaDTO> getCompetencias() {
		List<Competencia> competencias = CompetenciaDAOImp.getAllInstances();
		List<CompetenciaDTO> competenciasDTO = new ArrayList<>();
		for(Competencia competencia : competencias) {
			competenciasDTO.add(new CompetenciaDTO(competencia.getIdComp(),competencia.getNombreComp(),competencia.getDescripcion()));
		}
		return competenciasDTO;
	}
}
