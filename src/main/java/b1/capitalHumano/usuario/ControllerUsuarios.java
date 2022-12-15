package b1.capitalHumano.usuario;

import java.io.IOException;

import org.hibernate.MappingException;

import b1.capitalHumano.Singleton;
import b1.capitalHumano.candidato.Candidato;
import b1.capitalHumano.candidato.CandidatoDAO;
import b1.capitalHumano.candidato.CandidatoDAOImp;
import b1.capitalHumano.candidato.CandidatoDTO;
import b1.capitalHumano.consultor.Consultor;
import b1.capitalHumano.consultor.ConsultorDAO;
import b1.capitalHumano.consultor.ConsultorDAOImp;
import b1.capitalHumano.consultor.ConsultorDTO;
import b1.capitalHumano.cuestionario.Cuestionario;
import b1.capitalHumano.cuestionario.CuestionarioDTO;

public class ControllerUsuarios implements Singleton {
	
			private static ControllerUsuarios instance = null;
	public ControllerUsuarios(){};
	public static ControllerUsuarios getInstance() {
		// TODO Auto-generated method stub
		if (instance == null) {
			instance = new ControllerUsuarios();
		}
		return instance;

	}
	
	public ConsultorDTO autenticarConsultor(ConsultorDTO consultorDTO) {
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

	public CuestionarioDTO autenticarCandidato(CandidatoDTO candidatoDTO) {
		Candidato candidato = null;
		CandidatoDAO candidatoDAOImp = new CandidatoDAOImp();

		// AGREGAR EN DIAGRAMA DE SECUENCIA GETTIPO

		candidato = candidatoDAOImp.getByFilter(candidatoDTO.getTipo(),candidatoDTO.getDNI()); 
		
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
