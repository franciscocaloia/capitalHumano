package b1.capitalHumano.candidato;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;

import b1.capitalHumano.Singleton;
import b1.capitalHumano.competencia.Competencia;
import b1.capitalHumano.cuestionario.CompetenciaCuestionario;
import b1.capitalHumano.cuestionario.ControllerCuestionario;
import b1.capitalHumano.cuestionario.Cuestionario;
import b1.capitalHumano.cuestionario.CuestionarioDTO;
import b1.capitalHumano.cuestionario.PreguntaCuestionario;
import b1.capitalHumano.evaluacion.Evaluacion;
import b1.capitalHumano.puesto.PonderacionNecesaria;
import b1.capitalHumano.puesto.PonderacionNecesariaDTO;
import b1.capitalHumano.puesto.Puesto;
import b1.capitalHumano.puesto.PuestoDAO;
import b1.capitalHumano.puesto.PuestoDAOImp;
import b1.capitalHumano.puesto.PuestoDTO;

public class ControllerCandidato implements Singleton {
	
	private static ControllerCandidato instance = null;
	CandidatoDAO candidatoDAO = new CandidatoDAOImp();
	PuestoDAO puestoDAO = new PuestoDAOImp();
	ControllerCuestionario controllerCuestionario = ControllerCuestionario.getInstance();
	
	
	public ControllerCandidato(){};
	
	public static ControllerCandidato getInstance() {
		if (instance == null) {
			instance = new ControllerCandidato();
		}
		return instance;
	}


	public List<CandidatoDTO> getCandidatos() {
		// TODO Auto-generated method stub
		List<CandidatoDTO> candidatosDTO = new ArrayList<CandidatoDTO>();
		for (Candidato candidato : candidatoDAO.getAllInstances()) {
			// if (candidato.getConsultor().getIdConsultor() == idConsultor) {
			CandidatoDTO candidatoDTO = new CandidatoDTO();
			candidatoDTO.setIdCandidato(candidato.getIdCandidato());
			candidatoDTO.setNombre(candidato.getNombre());
			candidatoDTO.setApellido(candidato.getApellido());
			if(candidato.getActivo()!=null) candidatoDTO.setCuestionarioActivo(new CuestionarioDTO(candidato.getActivo()));
			candidatoDTO.setDNI(candidato.getDNI());
			candidatoDTO.setTipo(candidato.getTipo());
			candidatoDTO.setClave(candidato.getClave());

			candidatosDTO.add(candidatoDTO);
			// }

		}
		return candidatosDTO;
	}
	
	public List<CandidatoDTO> getCandidatosSinActivo(){
		List<CandidatoDTO> candidatosDTO = getCandidatos().stream().filter(e->e.getCuestionarioActivo() == null).collect(Collectors.toList());
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
//		candidatoDAO.buscarCandidatos(nombreInput, apellidoInput);
		Set<PonderacionNecesariaDTO> caracteristicasSetDTO = new HashSet<PonderacionNecesariaDTO>();
		if (isNumber(codigoInput)) {
			for (Candidato candidato : candidatoDAO.buscarCandidatos(Integer.parseInt(codigoInput), nombreInput,
					apellidoInput)) {

				CandidatoDTO candidatoDTO = new CandidatoDTO();
				candidatoDTO.setApellido(candidato.getApellido());
				candidatoDTO.setNombre(candidato.getNombre());
				candidatoDTO.setIdCandidato(candidato.getIdCandidato());
				candidatoDTO.setCuestionarioActivo(new CuestionarioDTO(candidato.getActivo()));
				candidatoDTO.setDNI(candidato.getDNI());
				candidatoDTO.setTipo(candidato.getTipo());
				candidatoDTO.setClave(candidato.getClave());
				candidatosDTO.add(candidatoDTO);
			}
		} else {
			for (Candidato candidato : candidatoDAO.buscarCandidatos(nombreInput, apellidoInput)) {
				CandidatoDTO candidatoDTO = new CandidatoDTO();
				candidatoDTO.setApellido(candidato.getApellido());
				candidatoDTO.setNombre(candidato.getNombre());
				candidatoDTO.setIdCandidato(candidato.getIdCandidato());
				candidatoDTO.setCuestionarioActivo(new CuestionarioDTO(candidato.getActivo()));
				candidatoDTO.setDNI(candidato.getDNI());
				candidatoDTO.setTipo(candidato.getTipo());
				candidatoDTO.setClave(candidato.getClave());
				candidatosDTO.add(candidatoDTO);
			}
		}
		return candidatosDTO;

	}
	
	public void generateRandomKey(List<CandidatoDTO> candidatosDTO) {
		for (CandidatoDTO candidatoDTO : candidatosDTO) {
			int length = 8;
		    boolean useLetters = true;
		    boolean useNumbers = true;
		    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
			candidatoDTO.setClave(generatedString);
		}
	}

	public void modificarCandidato(List<CandidatoDTO> candidatosDTO) {
		Set<Candidato> candidatos = new HashSet<Candidato>();
		for (CandidatoDTO candidatoDTO : candidatosDTO) {
			Candidato candidato = candidatoDAO.getById(candidatoDTO.getIdCandidato());
			candidato.setAtributos(candidatoDTO);
			candidatos.add(candidato);
		}
		candidatoDAO.update(candidatos);
	}
}
