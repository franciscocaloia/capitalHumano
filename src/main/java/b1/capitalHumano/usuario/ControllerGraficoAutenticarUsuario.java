package b1.capitalHumano.usuario;

import b1.capitalHumano.App;
import b1.capitalHumano.ControllerGraficoCuestionario;
import b1.capitalHumano.Cuestionario;
import b1.capitalHumano.CuestionarioDTO;

import b1.capitalHumano.candidato.CandidatoDTO;
import b1.capitalHumano.consultor.Consultor;
import b1.capitalHumano.consultor.ConsultorDTO;
import b1.capitalHumano.consultor.ControllerGraficoPantallaPrincipal;
import b1.capitalHumano.puesto.ControllerGraficoPuestoModificar_Dialog;
import b1.capitalHumano.puesto.ControllerGraficoPuestos;
import b1.capitalHumano.puesto.ControllerGraficoPuestos_Dialog;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

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

	public static Stage stage;
	private static FXMLLoader fxmlLoader;

	public void initialize() {
		if (tipo != null) {
			tipo.setItems(FXCollections.observableArrayList(new String("DNI"), new String("LE"), new String("LC"),
					new String("PP")));
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

		if (usuario.getText().isEmpty() && contraseña.getText().isEmpty()) {

			if (!usuario.getStyleClass().contains("inputError")) {
				usuario.getStyleClass().add("inputError");
			}

			if (!contraseña.getStyleClass().contains("inputError")) {
				contraseña.getStyleClass().add("inputError");

			}

		} else if (contraseña.getText().isEmpty()) {

			if (usuario.getStyleClass().contains("inputError")) {
				usuario.getStyleClass().remove("inputError");
			}
			if (!contraseña.getStyleClass().contains("inputError")) {
				contraseña.getStyleClass().add("inputError");
			}

		} else if (usuario.getText().isEmpty()) {
			if (!usuario.getStyleClass().contains("inputError")) {
				usuario.getStyleClass().add("inputError");
			}
			if (contraseña.getStyleClass().contains("inputError")) {
				contraseña.getStyleClass().remove("inputError");
			}
		} else {
			// usuario.getStyleClass().remove("inputError");
			// contraseña.getStyleClass().remove("inputError");
			if (usuario.getStyleClass().contains("inputError")) {
				usuario.getStyleClass().remove("inputError");
			}
			if (contraseña.getStyleClass().contains("inputError")) {
				contraseña.getStyleClass().remove("inputError");
			}
			consultorDTO.setNombre(usuario.getText());
			consultorDTO.setContraseña(contraseña.getText());
			ConsultorDTO consultorDTOAutenticado = ControllerUsuarios.autenticarConsultor(consultorDTO);

			if (consultorDTOAutenticado == null) {

				Alert alertError = new Alert(AlertType.ERROR, "El usuario o la contraseña son incorrectos.",
						ButtonType.CLOSE);
				alertError.setHeaderText("Error de Inicio de Sesión");
				alertError.show();

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

		if (DNI.getText().isEmpty() || clave.getText().isEmpty()
				|| tipo.getSelectionModel().getSelectedItem() == null) {
			if (DNI.getText().isEmpty()) {
				if (!DNI.getStyleClass().contains("inputError")) {
					DNI.getStyleClass().add("inputError");
		
				}
			} else {
				if (DNI.getStyleClass().contains("inputError")) {
					DNI.getStyleClass().remove("inputError");
				}
			}
			if (clave.getText().isEmpty()) {
				if (!clave.getStyleClass().contains("inputError")) {
					clave.getStyleClass().add("inputError");
				}
			} else {
				if (clave.getStyleClass().contains("inputError")) {
					clave.getStyleClass().remove("inputError");
				}
			}
			if (tipo.getSelectionModel().getSelectedItem() == null) {
				if (!tipo.getStyleClass().contains("inputError")) {
					tipo.getStyleClass().add("inputError");
				}
			} else {
				if (tipo.getStyleClass().contains("inputError")) {
					tipo.getStyleClass().remove("inputError");
				}
			}
		} else {
			
			
			
			if (clave.getStyleClass().contains("inputError")) {
				clave.getStyleClass().remove("inputError");
			}
			if (DNI.getStyleClass().contains("inputError")) {
				DNI.getStyleClass().remove("inputError");
			}
			if (tipo.getStyleClass().contains("inputError")) {
				tipo.getStyleClass().remove("inputError");
			}
			
			
			CandidatoDTO candidatoDTO = new CandidatoDTO();
			candidatoDTO.setTipo(tipo.getSelectionModel().getSelectedItem());
			candidatoDTO.setDNI(DNI.getText());
			candidatoDTO.setClave(clave.getText());

			CuestionarioDTO CuestionarioDTO = ControllerUsuarios.autenticarCandidato(candidatoDTO); // devovler

			if (CuestionarioDTO == null) {

				Alert alertError = new Alert(AlertType.ERROR, "Los datos ingresados no son válidos o no existe un cuestionario para el candidato.",
						ButtonType.CLOSE);
				alertError.setHeaderText("Error de Inicio de Sesión");
				alertError.show();

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
