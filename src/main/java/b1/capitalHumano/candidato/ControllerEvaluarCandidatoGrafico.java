package b1.capitalHumano.candidato;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import b1.capitalHumano.App;
import b1.capitalHumano.competencia.CompetenciaDTO;
import b1.capitalHumano.consultor.ConsultorDTO;
import b1.capitalHumano.consultor.ControllerGraficoPantallaPrincipal;
import b1.capitalHumano.empresa.ControllerEmpresa;
import b1.capitalHumano.empresa.Empresa;
import b1.capitalHumano.empresa.EmpresaDTO;
import b1.capitalHumano.puesto.ControllerGraficoPuestos;
import b1.capitalHumano.puesto.ControllerPuestos;
import b1.capitalHumano.puesto.PonderacionNecesariaDTO;
import b1.capitalHumano.puesto.Puesto;
import b1.capitalHumano.puesto.PuestoDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerEvaluarCandidatoGrafico {
	ControllerCandidato controllerCandidato = new ControllerCandidato();
	ControllerEmpresa controllerEmpresa = new ControllerEmpresa();
	ControllerPuestos controllerPuestos = new ControllerPuestos();
	@FXML
	TableView<CandidatoDTO> candidatoTable;
	@FXML
	TableView<CandidatoDTO> candidatoTableEv;

	@FXML
	TableColumn<CandidatoDTO, Integer> numeroCandidatoColumn;
	@FXML
	TableColumn<CandidatoDTO, String> nombreCandidatoColumn;
	@FXML
	TableColumn<CandidatoDTO, String> apellidoCandidatoColumn;

	@FXML
	TableColumn<CandidatoDTO, Integer> numeroCandidatoColumnEv;
	@FXML
	TableColumn<CandidatoDTO, String> nombreCandidatoColumnEv;
	@FXML
	TableColumn<CandidatoDTO, String> apellidoCandidatoColumnEv;
	@FXML
	TextField apellidoInput;
	@FXML
	TextField nombreInput;
	@FXML
	TextField codigoInput;

	ConsultorDTO consultorDTO;
	private static FXMLLoader fxmlLoader;
	Stage stage;
	ObservableList<CandidatoDTO> CandidatoOl;
	ObservableList<CandidatoDTO> CandidatoOlEv;

	public void initialize() {

		// ELIMINAR ESTOS OBJS DE EJEMPLO

		///////////////////

		candidatoTableEv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		candidatoTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		numeroCandidatoColumn.setCellValueFactory(new PropertyValueFactory<CandidatoDTO, Integer>("idCandidato"));
		nombreCandidatoColumn.setCellValueFactory(new PropertyValueFactory<CandidatoDTO, String>("nombre"));
		apellidoCandidatoColumn.setCellValueFactory(new PropertyValueFactory<CandidatoDTO, String>("apellido"));

		numeroCandidatoColumnEv.setCellValueFactory(new PropertyValueFactory<CandidatoDTO, Integer>("idCandidato"));
		nombreCandidatoColumnEv.setCellValueFactory(new PropertyValueFactory<CandidatoDTO, String>("nombre"));
		apellidoCandidatoColumnEv.setCellValueFactory(new PropertyValueFactory<CandidatoDTO, String>("apellido"));

		List<CandidatoDTO> cadidatosDTO = controllerCandidato.getCandidatos();
		CandidatoOl = FXCollections.observableArrayList(cadidatosDTO);

		candidatoTable.setItems(CandidatoOl);
	}

	public void setStageAndSetupListeners(Stage stage) {
		// TODO Auto-generated method stub
		this.stage = stage;
	}

	public void setConsultorDTO(ConsultorDTO consultorDTO) {
		// TODO Auto-generated method stub
		this.consultorDTO = consultorDTO;
	}

	public void agregarCandidatoHandle() {

		if (CandidatoOlEv == null) {
			CandidatoOlEv = FXCollections.observableArrayList(candidatoTable.getSelectionModel().getSelectedItems());
		} else {

			for (CandidatoDTO candidatoDTO : candidatoTable.getSelectionModel().getSelectedItems()) {
				FilteredList<CandidatoDTO> listaFiltrada = CandidatoOlEv
						.filtered(e -> e.getIdCandidato() == candidatoDTO.getIdCandidato());

				if (!CandidatoOlEv.contains(candidatoDTO) && listaFiltrada.isEmpty()) {
					CandidatoOlEv.add(candidatoDTO);
				}
			}

		}

		candidatoTableEv.setItems(CandidatoOlEv);
	}

	private static Parent loadFXML(String fxml) throws IOException {
		fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
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
		ControllerGraficoPantallaPrincipal 
		controllerGraficoPantallaPrincipal = (ControllerGraficoPantallaPrincipal) fxmlLoader
				.getController();
		controllerGraficoPantallaPrincipal.setStageAndSetupListeners(stage);
		controllerGraficoPantallaPrincipal.setConsultor(consultorDTO);
	}


	public void siguienteHandle() {
		if (CandidatoOlEv != null && !CandidatoOlEv.isEmpty()) {

			try {
				stage.getScene().setRoot(loadFXML("candidato/EvaluarCandidatos-SeleccionarFuncionPuestoaEvaluar"));

				ControllerEvaluar_SeleccionarPuesto_CandidatoGrafico controllerEvaluar_SeleccionarPuesto_CandidatoGrafico = (ControllerEvaluar_SeleccionarPuesto_CandidatoGrafico) fxmlLoader
						.getController();

				// pasar datos al controller de la nueva scene/root

				controllerEvaluar_SeleccionarPuesto_CandidatoGrafico.setStageAndSetupListeners(stage);
				controllerEvaluar_SeleccionarPuesto_CandidatoGrafico.setConsultorDTO(consultorDTO);
				// CandidatoOlEv.forEach(e -> System.out.println(e.getClave()));
				controllerEvaluar_SeleccionarPuesto_CandidatoGrafico.setCandidatoDTO(CandidatoOlEv);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void quitarCandidatosHandle() {

		if (CandidatoOlEv != null) {
			CandidatoOlEv.removeAll(candidatoTableEv.getSelectionModel().getSelectedItems());
			candidatoTableEv.setItems(CandidatoOlEv);
		}

	}

	public void buscarCandidatosHandle() {
		List<CandidatoDTO> candidatosDTO = controllerCandidato.buscarCandidatos(codigoInput.getText(),
				nombreInput.getText(), apellidoInput.getText());

		CandidatoOl = FXCollections.observableArrayList(candidatosDTO);

		candidatoTable.setItems(CandidatoOl);

	}

}
