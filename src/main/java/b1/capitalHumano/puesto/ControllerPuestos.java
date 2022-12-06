package b1.capitalHumano.puesto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.hibernate.MappingException;

import b1.capitalHumano.competencia.Competencia;
import b1.capitalHumano.competencia.CompetenciaDAO;
import b1.capitalHumano.competencia.CompetenciaDAOImp;
import b1.capitalHumano.empresa.Empresa;
import b1.capitalHumano.empresa.EmpresaDAO;
import b1.capitalHumano.empresa.EmpresaDAOImp;
import b1.capitalHumano.empresa.EmpresaDTO;
import b1.capitalHumano.evaluacion.Evaluacion;
import javafx.scene.control.Alert.AlertType;

public class ControllerPuestos {
	EmpresaDAO empresaDAO = new EmpresaDAOImp();
	PuestoDAO puestoDAO = new PuestoDAOImp();
	CompetenciaDAO competenciaDAO = new CompetenciaDAOImp();

	public boolean isNumber(String input) {
		Pattern pattern = Pattern.compile("[0-9]+");
		if (pattern.matcher(input).matches()) {
			return true;
		} else
			return false;
	}

	public List<PuestoDTO> buscarPuestos(String codigoInput, String nombreInput, String empresaInput) {
		List<PuestoDTO> puestosDTO = new ArrayList<>();
		// System.out.println("aaaaaaaaaaaaa");

		Set<PonderacionNecesariaDTO> caracteristicasSetDTO = new HashSet<PonderacionNecesariaDTO>();
		if (isNumber(codigoInput)) {
			for (Puesto puesto : puestoDAO.buscarPuestos(Integer.parseInt(codigoInput), nombreInput, empresaInput)) {

				PuestoDTO puestoDTO = new PuestoDTO(puesto.getIdPuesto(), puesto.getNombrePuesto(),
						puesto.getEmpresa().getIdEmpresa(), puesto.getEmpresa().getNombreEmpresa(),
						puesto.getDescripcionPuesto(), puesto.getEliminado());
				for (PonderacionNecesaria ponderacionNecesaria : puesto.getCaracteristicas()) {
					caracteristicasSetDTO
							.add(new PonderacionNecesariaDTO(ponderacionNecesaria.getPonderacionNecesaria(),
									ponderacionNecesaria.getCompetencia().getIdComp()));

				}

				puestoDTO.setCaracteristicasDTO(caracteristicasSetDTO);
				puestosDTO.add(puestoDTO);
			}

		} else {
			for (Puesto puesto : puestoDAO.buscarPuestos(nombreInput, empresaInput)) {

				PuestoDTO puestoDTO = new PuestoDTO(puesto.getIdPuesto(), puesto.getNombrePuesto(),
						puesto.getEmpresa().getIdEmpresa(), puesto.getEmpresa().getNombreEmpresa(),
						puesto.getDescripcionPuesto(), puesto.getEliminado());

				for (PonderacionNecesaria ponderacionNecesaria : puesto.getCaracteristicas()) {

					caracteristicasSetDTO
							.add(new PonderacionNecesariaDTO(ponderacionNecesaria.getPonderacionNecesaria(),
									ponderacionNecesaria.getCompetencia().getIdComp()));

				}

				puestoDTO.setCaracteristicasDTO(caracteristicasSetDTO);

				puestosDTO.add(puestoDTO);
			}
		}

		return puestosDTO;
	}

	public List<PuestoDTO> getPuestos() {
		List<Puesto> puestos = puestoDAO.getAllInstances();

		List<PuestoDTO> puestosDTO = new ArrayList<>();
		for (Puesto puesto : puestos) {

			PuestoDTO puestoDTO = new PuestoDTO(puesto.getIdPuesto(), puesto.getNombrePuesto(),
					puesto.getEmpresa().getIdEmpresa(), puesto.getEmpresa().getNombreEmpresa(),
					puesto.getDescripcionPuesto(), puesto.getEliminado());
			Set<PonderacionNecesariaDTO> ponderacionNecesariaDTO = new HashSet<PonderacionNecesariaDTO>();
			for (PonderacionNecesaria ponderacion : puesto.getCaracteristicas()) {
				ponderacionNecesariaDTO.add(new PonderacionNecesariaDTO(ponderacion.getPonderacionNecesaria(),
						ponderacion.getCompetencia().getIdComp()));
			}
			puestoDTO.setCaracteristicasDTO(ponderacionNecesariaDTO);

			puestosDTO.add(puestoDTO);

		}
		return puestosDTO;
	}

	public void darDeAltaPuesto(PuestoDTO puestoDTO) {
		Empresa empresa = empresaDAO.getById(puestoDTO.getIdEmpresa());
		Set<PonderacionNecesaria> caracteristicas = new HashSet<PonderacionNecesaria>();
		Puesto puesto = new Puesto();
		puesto.setIdPuesto(puestoDTO.getIdPuesto());
		puesto.setNombrePuesto(puestoDTO.getNombrePuesto());
		puesto.setDescripcion(puestoDTO.getDescripcionPuesto());
		puesto.setEmpresa(empresa);
		for (PonderacionNecesariaDTO pondDTO : puestoDTO.getCaracteristicasDTO()) {

			caracteristicas.add(new PonderacionNecesaria(puesto, pondDTO.getPonderacionNecesaria(),
					competenciaDAO.getById(pondDTO.getIdComp())));
		}
		puesto.setCaracteristicas(caracteristicas);
		puestoDAO.insert(puesto);
	}

	public PuestoDTO eliminarPuesto(int idPuesto) {
		// TODO Auto-generated method stuba
		PuestoDAO puestoDAO = new PuestoDAOImp();
		Puesto puesto = null;
		// List<Evaluacion> listaEvaluaciones = puestoDAO.getEvaluaciones();

		Set<Evaluacion> evaluaciones = puestoDAO.getEvaluaciones(idPuesto);
		if (evaluaciones.isEmpty()) {
			// System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");
			puesto = puestoDAO.getById(idPuesto);
			puesto.setEliminado(true);

			puestoDAO.update(puesto);

			// EN EL DIAGRAMA UPDATE DEBERIA DEVOLVER ALGO Y NO SE QUE HACER CON ESO AHRE
		}

		return puesto == null ? null : new PuestoDTO(puesto);

	}

	public void modificarPuesto(PuestoDTO puestoDTO) {
		Puesto puesto = puestoDAO.getById(puestoDTO.getIdPuesto());
		Empresa empresa = empresaDAO.getById(puestoDTO.getIdEmpresa());

		puesto.setNombrePuesto(puestoDTO.getNombrePuesto());
		puesto.setEmpresa(empresa);
		puesto.setDescripcion(puestoDTO.getDescripcionPuesto());

		Set<PonderacionNecesaria> caracteristicas = new HashSet<PonderacionNecesaria>();

		for (PonderacionNecesariaDTO ponderacionNecesariaDTO : puestoDTO.getCaracteristicasDTO()) {

			caracteristicas.add(new PonderacionNecesaria(puesto, ponderacionNecesariaDTO.getPonderacionNecesaria(),
					competenciaDAO.getById(ponderacionNecesariaDTO.getIdComp())));
		}

		puesto.setCaracteristicas(caracteristicas);
		// puesto.getCaracteristicas().forEach(caracter -> caracter.setPuesto(puesto));
		puestoDAO.update(puesto);
	}

	public List<PuestoDTO> buscarPuestos(EmpresaDTO empresaDTO) {
		List<PuestoDTO> puestosDTO = new ArrayList<PuestoDTO>();

		Empresa empresa = empresaDAO.getById(empresaDTO.getIdEmpresa());

		List<Puesto> puestos = puestoDAO.getByFilter(empresa);

		for (Puesto puesto : puestos) {
			PuestoDTO puestoDTO = new PuestoDTO(puesto);
			Set<PonderacionNecesariaDTO> caracteristicasSetDTO = new HashSet<PonderacionNecesariaDTO>();
			for (PonderacionNecesaria ponderacionNecesaria : puesto.getCaracteristicas()) {

				caracteristicasSetDTO
						.add(new PonderacionNecesariaDTO(ponderacionNecesaria.getPonderacionNecesaria(),
								ponderacionNecesaria.getCompetencia().getIdComp(), ponderacionNecesaria.getCompetencia().getNombreComp()));
		

			}
			puestoDTO.setCaracteristicasDTO(caracteristicasSetDTO);
			puestosDTO.add(puestoDTO);

		}


		return puestosDTO;
	}
}