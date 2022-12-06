package b1.capitalHumano.candidato;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import b1.capitalHumano.App;
import b1.capitalHumano.consultor.ConsultorDTO;
import b1.capitalHumano.consultor.ControllerGraficoPantallaPrincipal;
import b1.capitalHumano.empresa.ControllerEmpresa;
import b1.capitalHumano.empresa.EmpresaDTO;
import b1.capitalHumano.puesto.ControllerGraficoPuestos;
import b1.capitalHumano.puesto.ControllerPuestos;
import b1.capitalHumano.puesto.PonderacionNecesariaDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerEvaluar_Final_CandidatoGrafico {
	ControllerCandidato controllerCandidato = new ControllerCandidato();
	ControllerEmpresa controllerEmpresa = new ControllerEmpresa();
	ControllerPuestos controllerPuestos = new ControllerPuestos();
	private static FXMLLoader fxmlLoader;
	List<CandidatoDTO> candidatosDTO;
	ConsultorDTO consultorDTO;
	@FXML
	TableView<CandidatoDTO> candidatoTable;
	Stage stage;
	ObservableList<CandidatoDTO> candidatosDTOOl;
	@FXML
	TableColumn<CandidatoDTO, String> apellidoColumn;
	@FXML
	TableColumn<CandidatoDTO, String> nombreColumn;
	@FXML
	TableColumn<CandidatoDTO, String> tipoDocColumn;
	@FXML
	TableColumn<CandidatoDTO, Integer> numDocColumn;
	@FXML
	TableColumn<CandidatoDTO, Integer> claveColumn;

	public void setCandidatoDTO(List<CandidatoDTO> candidatosDTO) {
		this.candidatosDTO = candidatosDTO;
		candidatosDTOOl = FXCollections.observableArrayList(candidatosDTO);
		// ponderacionNecesariaDTOOl.forEach(e ->
		// System.out.println(e.getCompetencia()));
		// System.out.println(ponderacionNecesariaDTOOl.size());
		candidatoTable.setItems(candidatosDTOOl);

		//
		// System.out.println("aaaaaaaaaaaaaaaaaa");

		// OBTENER TIPO DNI

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

public void cancelarHandle() {
	try {
		stage.getScene().setRoot(loadFXML("candidato/EvaluarCandidatos-FiltrarYSeleccionarEmpleados"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	ControllerEvaluarCandidatoGrafico controllerGraficoCandidato = (ControllerEvaluarCandidatoGrafico) fxmlLoader
			.getController();
	//

	// pasar datos al controller de la nueva scene/root

	controllerGraficoCandidato.setStageAndSetupListeners(stage);
	controllerGraficoCandidato.setConsultorDTO(consultorDTO);
	
}

	public void inicioHandleMenu() throws IOException {

		stage.getScene().setRoot(loadFXML("PantallaPrincipal"));
		ControllerGraficoPantallaPrincipal controllerGraficoPantallaPrincipal = (ControllerGraficoPantallaPrincipal) fxmlLoader
				.getController();
		controllerGraficoPantallaPrincipal.setStageAndSetupListeners(stage);
		controllerGraficoPantallaPrincipal.setConsultor(consultorDTO);
	}

	public void initialize() {

		// candidatoTable.setSelectionModel(null);
		// List<EmpresaDTO> empresasDTO = ControllerEmpresa.getEmpresas();
		// System.out.println(empresasDTO.size());
		// empresaChoice.setItems(FXCollections.observableArrayList(empresasDTO));

		apellidoColumn.setCellValueFactory(new PropertyValueFactory<CandidatoDTO, String>("apellido"));
		nombreColumn.setCellValueFactory(new PropertyValueFactory<CandidatoDTO, String>("nombre"));
		claveColumn.setCellValueFactory(new PropertyValueFactory<CandidatoDTO, Integer>("clave"));

		numDocColumn.setCellValueFactory(new PropertyValueFactory<CandidatoDTO, Integer>("DNI"));

		// Obtener DNI
		//
		tipoDocColumn.setCellValueFactory(new PropertyValueFactory<CandidatoDTO, String>("tipo"));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public void setConsultorDTO(ConsultorDTO consultorDTO) {
		this.consultorDTO = consultorDTO;

	}

	public void setStageAndSetupListeners(Stage stage) {
		// TODO Auto-generated method stub
		this.stage = stage;
	}
}
