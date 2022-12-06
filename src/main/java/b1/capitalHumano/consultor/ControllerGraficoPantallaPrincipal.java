package b1.capitalHumano.consultor;

import java.io.IOException;

import b1.capitalHumano.App;
import b1.capitalHumano.candidato.ControllerEvaluarCandidatoGrafico;
import b1.capitalHumano.puesto.ControllerGraficoPuestos;
import b1.capitalHumano.usuario.ControllerGraficoAutenticarUsuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerGraficoPantallaPrincipal {
	private static ConsultorDTO consultorDTO;
	private static Stage stage;
	private static FXMLLoader fxmlLoader;

	@FXML
	Text textoPrincipal;

	public void initialize() {

	}

	private static Parent loadFXML(String fxml) throws IOException {
		fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public void gestionarPuestoHandleMenu() throws IOException {

		stage.getScene().setRoot(loadFXML("puesto/GestionarPuesto--Inicio"));

		ControllerGraficoPuestos controllerGraficoPuestos = (ControllerGraficoPuestos) fxmlLoader.getController();

		// pasar datos al controller de la nueva scene/root

		controllerGraficoPuestos.setStageAndSetupListeners(stage);
		controllerGraficoPuestos.setConsultorDTO(consultorDTO);
		// System.out.println("aaaaaaaaa");

	}

	public void evaluarHandleMenu() throws IOException {

		stage.getScene().setRoot(loadFXML("candidato/EvaluarCandidatos-FiltrarYSeleccionarEmpleados"));

		ControllerEvaluarCandidatoGrafico controllerGraficoCandidato = (ControllerEvaluarCandidatoGrafico) fxmlLoader
				.getController();
		//

		// pasar datos al controller de la nueva scene/root

		controllerGraficoCandidato.setStageAndSetupListeners(stage);
		controllerGraficoCandidato.setConsultorDTO(consultorDTO);
		// System.out.println("aaaaaaaaa");

	}

	public ConsultorDTO getConsultor() {
		return consultorDTO;
	}

	public void setConsultor(ConsultorDTO consultorDTO) {
		ControllerGraficoPantallaPrincipal.consultorDTO = consultorDTO;
		// System.out.println(consultorDTO.getNombre());
		textoPrincipal.setText("Bienvenido: " + consultorDTO.getNombre());

	}

	public void setStageAndSetupListeners(Stage stage) {
		ControllerGraficoPantallaPrincipal.stage = stage;
	}

}
