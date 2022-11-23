package b1.capitalHumano.usuario;

import b1.capitalHumano.App;
import b1.capitalHumano.ControllerGraficoCuestionario;
import b1.capitalHumano.Cuestionario;
import b1.capitalHumano.CuestionarioDTO;

import b1.capitalHumano.candidato.CandidatoDTO;
import b1.capitalHumano.consultor.Consultor;
import b1.capitalHumano.consultor.ConsultorDTO;
import b1.capitalHumano.consultor.ControllerGraficoPantallaPrincipal;
import b1.capitalHumano.puesto.ControllerGraficoPuestos_Dialog;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerGraficoAutenticarUsuario {

	@FXML
	TextField usuario;
	@FXML
	TextField contraseña;
	@FXML
	TextField DNI;
	@FXML
	TextField clave;
	@FXML
	ComboBox<String> tipo;

	private static Stage stage;
	private static FXMLLoader fxmlLoader;

	public void initialize() {
		if (tipo != null) {
			tipo.setItems(FXCollections.observableArrayList(new String("DNI"), new String("CUIT")));
		}
	}

	public void setStageAndSetupListeners(Stage stage) {
		ControllerGraficoAutenticarUsuario.stage = stage;
	}

	public void ingresarConsultor() {
		try {

			stage.getScene().setRoot(loadFXML("usuario/AutenticacionDeUsuario--iniciarSesion"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Parent loadFXML(String fxml) throws IOException {
		fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public void iniciarSesion() {

		ConsultorDTO consultorDTO = new ConsultorDTO();
		consultorDTO.setNombre(usuario.getText());
		consultorDTO.setContraseña(contraseña.getText());
		ConsultorDTO consultorDTOAutenticado = ControllerUsuarios.autenticarConsultor(consultorDTO);

		if (consultorDTOAutenticado == null) {
			try {
				stage.getScene().setRoot(loadFXML("usuario/AutenticacionDeUsuario--ErrorInicioSesión"));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			try {

				stage.getScene().setRoot(loadFXML("PantallaPrincipal"));
				ControllerGraficoPantallaPrincipal controllerGraficoPantallaPrincipal = (ControllerGraficoPantallaPrincipal) fxmlLoader
						.getController();
				// pasar datos al controller de la nueva scene/root
				controllerGraficoPantallaPrincipal.setConsultor(consultorDTOAutenticado);
				controllerGraficoPantallaPrincipal.setStageAndSetupListeners(stage);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void realizarCuestionario() {
		try {
			stage.getScene().setRoot(loadFXML("usuario/AutenticacionDeUsuario--RealizarFormulario"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void iniciarSesionCandidato() {

		CandidatoDTO candidatoDTO = new CandidatoDTO();
		candidatoDTO.setDNI(DNI.getText());
		candidatoDTO.setClave(usuario.getText());
		CuestionarioDTO CuestionarioDTO = ControllerUsuarios.autenticarCandidato(candidatoDTO); // devovler

		if (CuestionarioDTO == null) {
			try {
				stage.getScene().setRoot(loadFXML("usuario/Error-AutenticacionDeCandidato"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			try {
				stage.getScene().setRoot(loadFXML("usuario/CompletarCuestionario-Instrucciones"));
// pasar datos al controller de la nueva scene/root
				ControllerGraficoCuestionario controllerGraficoCuestionario = (ControllerGraficoCuestionario) fxmlLoader
						.getController();
				controllerGraficoCuestionario.setCuestionario(CuestionarioDTO);
				controllerGraficoCuestionario.setStageAndSetupListeners(stage);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void regresar() {
		try {
			stage.getScene().setRoot(loadFXML("usuario/AutenticacionDeUsuario--inicio"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
