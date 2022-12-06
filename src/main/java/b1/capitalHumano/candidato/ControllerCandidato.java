package b1.capitalHumano.candidato;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import b1.capitalHumano.puesto.PonderacionNecesaria;
import b1.capitalHumano.puesto.PonderacionNecesariaDTO;
import b1.capitalHumano.puesto.Puesto;
import b1.capitalHumano.puesto.PuestoDTO;
import javafx.scene.control.TextField;

public class ControllerCandidato {
	CandidatoDAO candidatoDAO = new CandidatoDAOImp();

	public List<CandidatoDTO> getCandidatos() {
		// TODO Auto-generated method stub
		List<CandidatoDTO> candidatosDTO = new ArrayList<CandidatoDTO>();

		for (Candidato candidato : candidatoDAO.getAllInstances()) {
			// if (candidato.getConsultor().getIdConsultor() == idConsultor) {
			CandidatoDTO candidatoDTO = new CandidatoDTO();

			candidatoDTO.setIdCandidato(candidato.getIdCandidato());
			candidatoDTO.setNombre(candidato.getNombre());
			candidatoDTO.setApellido(candidato.getApellido());
			candidatoDTO.setCuestionarioActivo(candidato.getActivo());
			candidatoDTO.setDNI(candidato.getDNI());
			candidatoDTO.setTipo(candidato.getTipo());
			candidatoDTO.setClave(candidato.getClave());
			
		//	System.out.println(candidato.getTipo().size());	
		
			
			candidatosDTO.add(candidatoDTO);
			// }

		}
		return candidatosDTO;
	}

	public boolean isNumber(String input) {
		Pattern pattern = Pattern.compile("[0-9]+");
		if (pattern.matcher(input).matches()) {
			return true;
		} else
			return false;
	}

	public List<CandidatoDTO> buscarCandidatos(String codigoInput, String nombreInput, String apellidoInput) {
		List<CandidatoDTO> candidatosDTO = new ArrayList<>();

		// System.out.println("aaaaaaaaaaaaa");
		candidatoDAO.buscarCandidatos(nombreInput, apellidoInput);
		Set<PonderacionNecesariaDTO> caracteristicasSetDTO = new HashSet<PonderacionNecesariaDTO>();
		if (isNumber(codigoInput)) {
			for (Candidato candidato : candidatoDAO.buscarCandidatos(Integer.parseInt(codigoInput), nombreInput,
					apellidoInput)) {

				CandidatoDTO candidatoDTO = new CandidatoDTO();
				candidatoDTO.setApellido(candidato.getApellido());
				candidatoDTO.setNombre(candidato.getNombre());
				candidatoDTO.setIdCandidato(candidato.getIdCandidato());
				candidatoDTO.setCuestionarioActivo(candidato.getActivo());
				candidatoDTO.setDNI(candidato.getDNI());
				candidatoDTO.setTipo(candidato.getTipo());
				candidatoDTO.setClave(candidato.getClave());
				// FALTA DNI
				candidatosDTO.add(candidatoDTO);
			}

		} else {
			for (Candidato candidato : candidatoDAO.buscarCandidatos(nombreInput, apellidoInput)) {

				CandidatoDTO candidatoDTO = new CandidatoDTO();
				candidatoDTO.setApellido(candidato.getApellido());
				candidatoDTO.setNombre(candidato.getNombre());
				candidatoDTO.setIdCandidato(candidato.getIdCandidato());
				candidatoDTO.setCuestionarioActivo(candidato.getActivo());
				candidatoDTO.setDNI(candidato.getDNI());
				candidatoDTO.setTipo(candidato.getTipo());
				candidatoDTO.setClave(candidato.getClave());
				candidatosDTO.add(candidatoDTO);
			}
		}

		return candidatosDTO;

	}

}
