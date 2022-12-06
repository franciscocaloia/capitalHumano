package b1.capitalHumano.candidato;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import b1.capitalHumano.App;
import b1.capitalHumano.consultor.ConsultorDTO;
import b1.capitalHumano.consultor.ControllerGraficoPantallaPrincipal;
import b1.capitalHumano.empresa.ControllerEmpresa;
import b1.capitalHumano.empresa.EmpresaDTO;
import b1.capitalHumano.puesto.ControllerGraficoPuestos;
import b1.capitalHumano.puesto.ControllerPuestos;
import b1.capitalHumano.puesto.PonderacionNecesariaDTO;
import b1.capitalHumano.puesto.PuestoDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerEvaluar_SeleccionarPuesto_CandidatoGrafico {
	ControllerCandidato controllerCandidato = new ControllerCandidato();
	ControllerEmpresa controllerEmpresa = new ControllerEmpresa();
	ControllerPuestos controllerPuestos = new ControllerPuestos();
	private static FXMLLoader fxmlLoader;
	ConsultorDTO consultorDTO;
	List<CandidatoDTO> candidatosDTO;
	Stage stage;
	@FXML
	ChoiceBox<PuestoDTO> puestoChoice = new ChoiceBox<>();
	@FXML
	ChoiceBox<EmpresaDTO> empresaChoice = new ChoiceBox<>();
	@FXML
	TableView<PonderacionNecesariaDTO> candidatoTable;
	@FXML
	TableColumn<PonderacionNecesariaDTO, Integer> ponderacionColumn;
	@FXML
	TableColumn<PonderacionNecesariaDTO, String> competenciaColumn;
	ObservableList<PonderacionNecesariaDTO> ponderacionNecesariaDTOOl ;
	ObservableList<PuestoDTO> PuestoDTOOl;

	public void setCandidatoDTO(List<CandidatoDTO> candidatosDTO) {
		this.candidatosDTO = candidatosDTO;

	}

	public void initialize() {
		// candidatoTable.setSelectionModel(null);
		
		List<EmpresaDTO> empresasDTO = ControllerEmpresa.getEmpresas();
		// System.out.println(empresasDTO.size());
		empresaChoice.setItems(FXCollections.observableArrayList(empresasDTO));

		ponderacionColumn.setCellValueFactory(
				new PropertyValueFactory<PonderacionNecesariaDTO, Integer>("ponderacionNecesaria"));
		competenciaColumn.setCellValueFactory(new PropertyValueFactory<PonderacionNecesariaDTO, String>("competencia"));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public void volverHandle() {
		try {
			stage.getScene().setRoot(loadFXML("candidato/EvaluarCandidatos-FiltrarYSeleccionarEmpleados"));

			ControllerEvaluarCandidatoGrafico controllerEvaluarCandidatoGrafico = (ControllerEvaluarCandidatoGrafico) fxmlLoader
					.getController();
			controllerEvaluarCandidatoGrafico.setStageAndSetupListeners(stage);
			controllerEvaluarCandidatoGrafico.setConsultorDTO(consultorDTO);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setConsultorDTO(ConsultorDTO consultorDTO) {
		this.consultorDTO = consultorDTO;

	}

	public void setStageAndSetupListeners(Stage stage) {
		// TODO Auto-generated method stub
		this.stage = stage;
	}

	public void empresaChoiceSelected() {

		// puestoChoice.setSelectionModel(null);
		puestoChoice.setValue(null);

		List<PuestoDTO> puestosDTO = controllerPuestos.buscarPuestos(empresaChoice.getValue());
		// System.out.println(puestoDTO.size());

		// puestoDTO.forEach(e -> System.out.println(e.getCaracteristicasDTO().size()));
		///
		PuestoDTOOl = FXCollections.observableArrayList(puestosDTO);

		Predicate<PuestoDTO> estaEliminado = p -> p.getEliminado() == true;

		PuestoDTOOl.removeIf(estaEliminado);
		puestoChoice.setItems(PuestoDTOOl);

	}

	public void gestionarPuestoHandleMenu() {
		try {
			stage.getScene().setRoot(loadFXML("puesto/GestionarPuesto--Inicio"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ControllerGraficoPuestos controllerGraficoPuestos = (ControllerGraficoPuestos) fxmlLoader.getController();

		// pasar datos al controller de la nueva scene/root

		controllerGraficoPuestos.setStageAndSetupListeners(stage);
		controllerGraficoPuestos.setConsultorDTO(consultorDTO);
	}

	public void inicioHandleMenu() throws IOException {

		stage.getScene().setRoot(loadFXML("PantallaPrincipal"));
		ControllerGraficoPantallaPrincipal controllerGraficoPantallaPrincipal = (ControllerGraficoPantallaPrincipal) fxmlLoader
				.getController();
		controllerGraficoPantallaPrincipal.setStageAndSetupListeners(stage);
		controllerGraficoPantallaPrincipal.setConsultor(consultorDTO);
	}

	public void siguienteHandle() {
		/// Si la competencia a evaluar no posee
		// definidos factores con al menos 2 preguntas, dicha competencia no podrá ser
		/// evaluada
		if (ponderacionNecesariaDTOOl != null &&!ponderacionNecesariaDTOOl.isEmpty()) {

			try {

				stage.getScene().setRoot(loadFXML("candidato/EvaluarCandidatos-ListaCandidatosaEvaluar"));

				ControllerEvaluar_Final_CandidatoGrafico controllerEvaluar_Final_CandidatoGrafico = (ControllerEvaluar_Final_CandidatoGrafico) fxmlLoader
						.getController();

				// pasar datos al controller de la nueva scene/root

				controllerEvaluar_Final_CandidatoGrafico.setStageAndSetupListeners(stage);
				controllerEvaluar_Final_CandidatoGrafico.setConsultorDTO(consultorDTO);
				// candidatosDTO.forEach(e -> System.out.println(e.getClave()));
				controllerEvaluar_Final_CandidatoGrafico.setCandidatoDTO(candidatosDTO);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void puestoChoiceSelected() {
		Set<PonderacionNecesariaDTO> ponderacionNecesariaDTO = puestoChoice.getValue().getCaracteristicasDTO();
		ponderacionNecesariaDTOOl = FXCollections.observableArrayList(ponderacionNecesariaDTO);
		// ponderacionNecesariaDTOOl.forEach(e ->
		// System.out.println(e.getCompetencia()));
		// System.out.println(ponderacionNecesariaDTOOl.size());

		candidatoTable.setItems(ponderacionNecesariaDTOOl);

	}
}
