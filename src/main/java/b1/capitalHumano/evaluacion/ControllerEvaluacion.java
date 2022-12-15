package b1.capitalHumano.evaluacion;

import java.util.ArrayList;
import java.util.Date;
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
import b1.capitalHumano.cuestionario.Bloque;
import b1.capitalHumano.cuestionario.CompetenciaCuestionario;
import b1.capitalHumano.cuestionario.ControllerCuestionario;
import b1.capitalHumano.cuestionario.Cuestionario;
import b1.capitalHumano.cuestionario.EstadoBloque;
import b1.capitalHumano.cuestionario.EstadoCuestionario;
import b1.capitalHumano.cuestionario.FactorCuestionario;
import b1.capitalHumano.cuestionario.OpcionCuestionario;
import b1.capitalHumano.cuestionario.PreguntaCuestionario;
import b1.capitalHumano.puesto.PonderacionNecesaria;
import b1.capitalHumano.puesto.Puesto;
import b1.capitalHumano.puesto.PuestoDAO;
import b1.capitalHumano.puesto.PuestoDAOImp;
import b1.capitalHumano.puesto.PuestoDTO;

public class ControllerEvaluacion implements Singleton {
	private static ControllerEvaluacion instance = null;

	public static ControllerEvaluacion getInstance() {
		if (instance == null) {
			instance = new ControllerEvaluacion();
		}
		return instance;
	}

	EvaluacionDAO evaluacionDAO = new EvaluacionDAOImp();
	ControllerCuestionario controllerCuestionario = ControllerCuestionario.getInstance();
	CandidatoDAO candidatoDAO = new CandidatoDAOImp();
	PuestoDAO puestoDAO = new PuestoDAOImp();

	public void eliminarEvaluacion(Integer idEvaluacion) {
		evaluacionDAO.delete(idEvaluacion);
	}

	public void evaluarCandidatos(List<CandidatoDTO> candidatosDTO, PuestoDTO puestoDTO) {
		Puesto puesto = puestoDAO.getById(puestoDTO.getIdPuesto());
		Integer preguntasPorBloque = controllerCuestionario.getPreguntasPorBloque();
		Evaluacion evaluacion = new Evaluacion();
		evaluacion.setPuesto(puesto);
		evaluacion.setFecha(new Date());
		List<Candidato> candidatos = candidatoDAO.getByIdList(
				candidatosDTO.stream().map(candidato -> candidato.getIdCandidato()).collect(Collectors.toList()));
		for (Candidato candidato : candidatos) {
			List<PreguntaCuestionario> preguntasCuestionario = new ArrayList<PreguntaCuestionario>();
			Cuestionario cuestionario = new Cuestionario();
			candidato.addCuestionario(cuestionario);
			cuestionario.setEstado(EstadoCuestionario.Activo);
			cuestionario.setClave(candidato.getClave());
			cuestionario.setPuesto(puesto);
			cuestionario.setCandidato(candidato);
			cuestionario.setEvaluación(evaluacion);
			evaluacion.addCuestionario(cuestionario);
			for (PonderacionNecesaria ponderacionNecesaria : puesto.getCaracteristicas()) {
				Competencia competencia = ponderacionNecesaria.getCompetencia();
				if (competencia.isEvaluable()) {
					CompetenciaCuestionario competenciaCuestionario = new CompetenciaCuestionario();
					competenciaCuestionario.setNombreComp(competencia.getNombreComp());
					competenciaCuestionario.setPonderacionNecesaria(ponderacionNecesaria.getPonderacionNecesaria());
					cuestionario.addCompetenciaCuestionario(competenciaCuestionario);
					for (Factor factor : competencia.getFactoresEvaluables()) {
						FactorCuestionario factorCuestionario = new FactorCuestionario();
						factorCuestionario.setNombreFactor(factor.getNombreFactor());
						factorCuestionario.setCompetenciaCuestionario(competenciaCuestionario);
						competenciaCuestionario.addFactorCuestionario(factorCuestionario);
						for (Pregunta pregunta : factor.getPreguntasRandom()) {
							PreguntaCuestionario preguntaCuestionario = new PreguntaCuestionario();
							preguntaCuestionario.setDescripcion(pregunta.getPregunta());
							preguntaCuestionario.setFactor(factorCuestionario);
							factorCuestionario.addPreguntaCuestionario(preguntaCuestionario);
							preguntasCuestionario.add(preguntaCuestionario);
							for (PonderacionOpcion ponderacionOpcion : pregunta.getPonderaciones()) {
								OpcionCuestionario opcionCuestionario = new OpcionCuestionario();
								opcionCuestionario.setDescripcion(ponderacionOpcion.getOpcion().getDescripcion());
								opcionCuestionario.setPonderacionNecesaria(ponderacionOpcion.getPonderacion());
								preguntaCuestionario.addOpcion(opcionCuestionario);
							}
						}
					}
				}
			}
			for (int i = 0; i < Math.ceil(preguntasCuestionario.size() / preguntasPorBloque); i++) {
				Bloque bloque = new Bloque();
				bloque.setEstado(EstadoBloque.SinCompletar);
				cuestionario.addBloque(bloque);
				for (int j = 0; j < preguntasPorBloque; j++) {
					PreguntaCuestionario preguntaCuestionario = preguntasCuestionario.get(j + i * preguntasPorBloque);
					preguntaCuestionario.setBloque(bloque);
					bloque.addPreguntaCuestionario(preguntaCuestionario);
				}
			}
		}
		evaluacionDAO.insert(evaluacion);
	}
}
