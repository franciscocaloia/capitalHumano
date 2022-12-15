package b1.capitalHumano.cuestionario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import b1.capitalHumano.Factor;
import b1.capitalHumano.PonderacionOpcion;
import b1.capitalHumano.Pregunta;
import b1.capitalHumano.Singleton;
import b1.capitalHumano.candidato.Candidato;
import b1.capitalHumano.candidato.CandidatoDAO;
import b1.capitalHumano.candidato.CandidatoDAOImp;
import b1.capitalHumano.candidato.CandidatoDTO;
import b1.capitalHumano.competencia.Competencia;
import b1.capitalHumano.evaluacion.Evaluacion;
import b1.capitalHumano.puesto.PonderacionNecesaria;
import b1.capitalHumano.puesto.Puesto;
import b1.capitalHumano.puesto.PuestoDAO;
import b1.capitalHumano.puesto.PuestoDAOImp;
import b1.capitalHumano.puesto.PuestoDTO;

public class ControllerCuestionario implements Singleton {
	private static ControllerCuestionario instance = new ControllerCuestionario();
	private Integer preguntasPorBloque = 2;
	public static ControllerCuestionario getInstance() {
		return instance;
	}
	
	public Integer getPreguntasPorBloque() {
		return this.preguntasPorBloque;
	}
	
}
