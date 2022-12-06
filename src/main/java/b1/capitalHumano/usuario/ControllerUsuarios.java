package b1.capitalHumano.usuario;

import java.io.IOException;

import org.hibernate.MappingException;

import b1.capitalHumano.Cuestionario;
import b1.capitalHumano.CuestionarioDTO;
import b1.capitalHumano.candidato.Candidato;
import b1.capitalHumano.candidato.CandidatoDAO;
import b1.capitalHumano.candidato.CandidatoDAOImp;
import b1.capitalHumano.candidato.CandidatoDTO;
import b1.capitalHumano.consultor.Consultor;
import b1.capitalHumano.consultor.ConsultorDAO;
import b1.capitalHumano.consultor.ConsultorDAOImp;
import b1.capitalHumano.consultor.ConsultorDTO;

public class ControllerUsuarios {
	public static ConsultorDTO autenticarConsultor(ConsultorDTO consultorDTO) {
		Consultor consultor = null;

		ConsultorDAO consultorDaoImp = new ConsultorDAOImp();

		try {
			//System.out.println(consultorDTO.getNombre());

			consultor = consultorDaoImp.getByFilter(consultorDTO.getNombre());

		} catch (MappingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (consultor == null || !consultor.getContraseña().equals(consultorDTO.getContraseña())) {

			return null;
		}
		return new ConsultorDTO(consultor);
	}

	public static CuestionarioDTO autenticarCandidato(CandidatoDTO candidatoDTO) {
		Candidato candidato = null;
		CandidatoDAO candidatoDAOImp = new CandidatoDAOImp();

		// AGREGAR EN DIAGRAMA DE SECUENCIA GETTIPO

		candidato = candidatoDAOImp.getByFilter(candidatoDTO.getDNI(), candidatoDTO.getTipo());

		if (candidato == null || !candidato.getClave().equals(candidatoDTO.getClave())) {
			return null;
		}

		Cuestionario cuestionario = candidato.getActivo();

		if (cuestionario == null) {
			return null;
		}
		return new CuestionarioDTO(cuestionario);

	}

}
