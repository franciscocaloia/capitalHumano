package b1.capitalHumano.candidato;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ControllerCandidato {
	CandidatoDAO candidatoDAO = new CandidatoDAOImp();

	public List<CandidatoDTO> getCandidatos() {
		// TODO Auto-generated method stub
		List<CandidatoDTO> candidatosDTO = new ArrayList<CandidatoDTO>();

		for (Candidato candidato : candidatoDAO.getAllInstances()) {
			CandidatoDTO candidatoDTO = new CandidatoDTO();

			candidatoDTO.setIdCandidato(candidato.getIdCandidato());
			candidatoDTO.setNombre(candidato.getNombre());
			candidatoDTO.setApellido(candidato.getApellido());
			candidatoDTO.setCuestionarioActivo(candidato.getActivo());
			candidatosDTO.add(candidatoDTO);

		}

		return candidatosDTO;
	}

}
