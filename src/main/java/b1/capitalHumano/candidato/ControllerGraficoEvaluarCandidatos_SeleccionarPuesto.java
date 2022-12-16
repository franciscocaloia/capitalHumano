package b1.capitalHumano.candidato;

import java.io.IOException;
import java.util.Iterator;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerGraficoEvaluarCandidatos_SeleccionarPuesto {
	ControllerCandidato controllerCandidato = ControllerCandidato.getInstance();
	ControllerEmpresa controllerEmpresa = ControllerEmpresa.getInstance();
	ControllerPuestos controllerPuestos = ControllerPuestos.getInstance();
	private static FXMLLoader fxmlLoader;
	ConsultorDTO consultorDTO;
	List<CandidatoDTO> candidatosDTO;
	PuestoDTO puestoDTO;
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
	ObservableList<PonderacionNecesariaDTO> ponderacionNecesariaDTOOl;
	ObservableList<PuestoDTO> PuestoDTOOl;

	public void setCandidatoDTO(List<CandidatoDTO> candidatosDTO) {
		this.candidatosDTO = candidatosDTO;
	}

	public void initialize() {

		ObservableList<EmpresaDTO> empresaDTOOl = FXCollections.observableArrayList(controllerEmpresa.getEmpresas());

		empresaDTOOl.sort((o1, o2) -> o1.getNombreEmpresa().compareTo(o2.getNombreEmpresa()));

		empresaChoice.setItems(empresaDTOOl);
		ponderacionColumn.setCellValueFactory(
				new PropertyValueFactory<PonderacionNecesariaDTO, Integer>("ponderacionNecesaria"));
		competenciaColumn.setCellValueFactory(new PropertyValueFactory<PonderacionNecesariaDTO, String>("competencia"));
		candidatoTable.setRowFactory((table) -> {
			final TableRow<PonderacionNecesariaDTO> row = new TableRow<>() {
				@Override
				protected void updateItem(PonderacionNecesariaDTO foo, boolean empty) {
					getStyleClass().removeAll("row-error");
					super.updateItem(foo, empty);
					if (foo != null) {
						if (!foo.getEvaluable()) {
							getStyleClass().add("row-error");
						} else { /* remove if condition no longer true */
							getStyleClass().removeAll("row-error");
						}
					}

				}
			};
			return row;
		});
	}

	private static Parent loadFXML(String fxml) throws IOException {
		fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public void volverHandle() {
		try {
			stage.getScene().setRoot(loadFXML("candidato/EvaluarCandidatos"));

			ControllerGraficoEvaluarCandidatos controllerEvaluarCandidatoGrafico = (ControllerGraficoEvaluarCandidatos) fxmlLoader
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
		empresaChoice.getStyleClass().removeAll("inputError");
		if (PuestoDTOOl != null && !PuestoDTOOl.isEmpty()) {
			PuestoDTOOl.clear();
		}
		puestoChoice.getSelectionModel().clearSelection();
		puestoChoice.setValue(null);
		// System.out.println("aaaaaaaaaaaaa");
		puestoChoice.valueProperty().set(null);

		List<PuestoDTO> puestosDTO = controllerPuestos.buscarPuestos(empresaChoice.getValue());

		PuestoDTOOl = FXCollections.observableArrayList(puestosDTO);

		Predicate<PuestoDTO> estaEliminado = p -> p.getEliminado() == true;

		PuestoDTOOl.removeIf(estaEliminado);

		PuestoDTOOl.sort((o1, o2) -> o1.getNombrePuesto().compareTo(o2.getNombrePuesto()));

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

		if (empresaChoice.getValue() == null) {
			Alert alert = new Alert(AlertType.ERROR, "Debe seleccionar una empresa", ButtonType.CLOSE);
			alert.setTitle("Evaluar candidatos - seleccionar empresa");
			alert.show();

			if (!empresaChoice.getStyleClass().contains("inputError")) {
				empresaChoice.getStyleClass().add("inputError");
			}
		} else {
			if (puestoDTO != null) {
				if (puestoDTO.getEvaluable()) {
					try {

						controllerCandidato.generateRandomKey(candidatosDTO);
						stage.getScene().setRoot(loadFXML("candidato/EvaluarCandidatos-ListaCandidatosClaves"));
						ControllerGraficoEvaluarCandidatos_ListaCandidatosClaves controllerGraficoEvaluarCandidatos_ListaCandidatosClave = (ControllerGraficoEvaluarCandidatos_ListaCandidatosClaves) fxmlLoader
								.getController();
						controllerGraficoEvaluarCandidatos_ListaCandidatosClave.setStageAndSetupListeners(stage);
						controllerGraficoEvaluarCandidatos_ListaCandidatosClave.setConsultorDTO(consultorDTO);
						controllerGraficoEvaluarCandidatos_ListaCandidatosClave.setCandidatosDTO(candidatosDTO);
						controllerGraficoEvaluarCandidatos_ListaCandidatosClave.setPuestoDTO(puestoChoice.getValue());

					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					Alert alert = new Alert(AlertType.ERROR, "El puesto no es evaluable", ButtonType.CLOSE);
					alert.setTitle("Evaluar candidatos - seleccionar puesto");
					alert.show();
					if (!puestoChoice.getStyleClass().contains("inputError")) {
						puestoChoice.getStyleClass().add("inputError");
					}
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR, "Debe seleccionar un puesto antes de continuar",
						ButtonType.CLOSE);
				alert.setTitle("Evaluar candidatos - seleccionar puesto");
				alert.show();

				if (!puestoChoice.getStyleClass().contains("inputError")) {
					puestoChoice.getStyleClass().add("inputError");
				}
			}
		}
	}

	public void puestoChoiceSelected() {
		puestoDTO = puestoChoice.getValue();
		if (puestoDTO != null) {
			puestoChoice.getStyleClass().removeAll("inputError");
			// puestoChoice.getStyleClass().remove("inputError");
			Set<PonderacionNecesariaDTO> ponderacionesNecesariasDTO = puestoDTO.getCaracteristicasDTO();
			ponderacionNecesariaDTOOl = FXCollections.observableArrayList(ponderacionesNecesariasDTO)
					.sorted((ponderacion1, ponderacion2) -> Boolean.compare(ponderacion1.getEvaluable(),
							ponderacion2.getEvaluable()));
			candidatoTable.setItems(ponderacionNecesariaDTOOl);
		}

	}
}
