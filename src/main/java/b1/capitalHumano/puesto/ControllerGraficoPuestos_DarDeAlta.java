package b1.capitalHumano.puesto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import b1.capitalHumano.competencia.Competencia;
import b1.capitalHumano.competencia.CompetenciaDAOImp;
import b1.capitalHumano.competencia.CompetenciaDTO;
import b1.capitalHumano.competencia.ControllerCompetencia;
import b1.capitalHumano.empresa.ControllerEmpresa;
import b1.capitalHumano.empresa.Empresa;
import b1.capitalHumano.empresa.EmpresaDAOImp;
import b1.capitalHumano.empresa.EmpresaDTO;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ControllerGraficoPuestos_DarDeAlta {
	PuestoDTO puestoDTO;
	ControllerEmpresa controllerEmpresa= ControllerEmpresa.getInstance();
	ObservableList<EmpresaDTO> empresaOL;
	ObservableList<CompetenciaDTO> competenciaOL;
	Set<PonderacionNecesariaDTO> caracteristicasDTO = new HashSet<PonderacionNecesariaDTO>();
	@FXML
	DialogPane dialog;

	@FXML
	VBox dialogVbox;

	@FXML
	TextField codigoInput;

	@FXML
	TextField nombreInput;

	@FXML
	TextField descripcionInput;
	@FXML
	ChoiceBox<EmpresaDTO> empresaChoice;
	@FXML
	VBox VBoxCompetencia;
	// ScrollPane scrollPane = new ScrollPane();

	Text textError = new Text("Se deben completar y/o veririficar todos los campos");
	Stage stage;
	List<PuestoDTO> puestosDTO;
	ControllerPuestos controllerPuesto = ControllerPuestos.getInstance();

	public void initialize() {

		// scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		// scrollPane.fitToHeightProperty().set(true);

		competenciaOL = FXCollections.observableArrayList(ControllerCompetencia.getCompetencias());
		empresaOL = FXCollections.observableArrayList(controllerEmpresa.getEmpresas());
		competenciaOL.sort((o1, o2) -> o1.getNombreComp().compareTo(o2.getNombreComp()));
		empresaOL.sort((o1, o2) -> o1.getNombreEmpresa().compareTo(o2.getNombreEmpresa()));
		empresaChoice.setItems(empresaOL);
		VBoxCompetencia.getChildren().add(createCompetenciaFXML());

		puestosDTO = controllerPuesto.getPuestos();

	}

	public HBox createCompetenciaFXML() {
		HBox newCompetencia = new HBox();
		Button borrar = new Button();
		borrar.setOnAction(null);
		borrar.setText("-");
		borrar.setDisable(true);
		borrar.setPrefSize(25, 25);
		newCompetencia.setPrefHeight(50);
		Pane spacer = new Pane();
		spacer.setMinSize(15, 1);

		Text textCompetencia = new Text("Competencia ");
		ChoiceBox<CompetenciaDTO> choiceCompetencia = new ChoiceBox<>();
		Text textPonderacion = new Text(" Ponderacion ");
		TextField ponderacionCompetencia = new TextField();
		ponderacionCompetencia.setMaxWidth(50);
		ponderacionCompetencia.setStyle("-fx-margin: 10px");
		choiceCompetencia.setItems(competenciaOL);
		newCompetencia.getChildren().addAll(textCompetencia, choiceCompetencia, textPonderacion, ponderacionCompetencia,
				spacer, borrar);

		newCompetencia.maxWidth(250);
		if (VBoxCompetencia.getChildren().size() > 0) {
			borrar.setDisable(false);
		}
		borrar.setOnAction(e -> {
			// newCompetencia.getChildren().clear();

			// borrar.setDisable(false);
			VBoxCompetencia.getChildren().remove(newCompetencia);

			// caracteristicasDTO.clear();
		});
		return newCompetencia;
	}

	public void handleAddCompetencia() {
		HBox hbox = createCompetenciaFXML();
		VBoxCompetencia.getChildren().add(hbox);
		// stage = (Stage) dialog.getScene().getWindow();
		// stage.setHeight(stage.getHeight() + 50);
		// scrollPane.setContent(VBoxCompetencia);
		// dialog.getChildren().add(scrollPane);
	}

	public void setPuesto(PuestoDTO puestoDTO) {
		this.puestoDTO = puestoDTO;
	}

	public boolean isNumber(String input) {
		Pattern pattern = Pattern.compile("[0-9]+");
		if (pattern.matcher(input).matches()) {
			return true;
		} else
			return false;
	}

	public boolean validar() {
		Boolean formValid = true;
		// Codigo
		textError.setText("Se deben completar y/o veririficar todos los campos");
		String idPuesto = codigoInput.getText();

		String nombre = nombreInput.getText();
		if (nombre.isEmpty()) {
			if (!nombreInput.getStyleClass().contains("inputError")) {
				nombreInput.getStyleClass().add("inputError");
			}
		} else {
			nombreInput.getStyleClass().remove("inputError");
		}

		if (idPuesto.isEmpty() || !isNumber(idPuesto)) {

			formValid = false;

			// FALTA VERIFICAR QUE EL CODIGO ID DE PEUSTO NO EXSITE

			if (!codigoInput.getStyleClass().contains("inputError")) {
				codigoInput.getStyleClass().add("inputError");
			}
		} else {

			for (PuestoDTO puestoDTO : puestosDTO) {

				// ARREGLAR EL ESTILO
				if (puestoDTO.getIdEmpresa() == empresaChoice.getValue().getIdEmpresa()) {
					if (nombre.isEmpty()
							|| puestoDTO.getNombrePuesto().toLowerCase().equals(nombreInput.getText().toLowerCase())) {
						formValid = false;
						textError.setText("Se debe veririficar el nombre del puesto o la empresa seleccionada");
						if (!nombreInput.getStyleClass().contains("inputError")) {
							nombreInput.getStyleClass().add("inputError");
						}

					}
					if (puestoDTO.getCodigoPuesto() == Integer.parseInt(idPuesto)) {
						formValid = false;
						textError.setText("Se debe veririficar el codigo o la empresa seleccionada.");
						if (!codigoInput.getStyleClass().contains("inputError")) {
							codigoInput.getStyleClass().add("inputError");
						}

					}
				}
			}
			if (formValid) {
				codigoInput.getStyleClass().remove("inputError");
			}
		}

		// Nombre

		// Descripcion
		String descripcion = descripcionInput.getText();
		if (descripcion.isEmpty()) {
			formValid = false;
			if (!descripcionInput.getStyleClass().contains("inputError")) {
				descripcionInput.getStyleClass().add("inputError");
			}
		} else {
			descripcionInput.getStyleClass().remove("inputError");
		}
		// Empresa
		EmpresaDTO empresaDTO = (EmpresaDTO) empresaChoice.getValue();
		if (empresaDTO == null) {
			formValid = false;
			if (!empresaChoice.getStyleClass().contains("inputError")) {
				empresaChoice.getStyleClass().add("inputError");
			}
		} else {
			empresaChoice.getStyleClass().remove("inputError");
		}

		// Caracteristicas

		boolean caracteristicaValid;
		for (Node child : VBoxCompetencia.getChildren()) {
			caracteristicaValid = true;
			HBox caracteristica = (HBox) child;
			ChoiceBox<CompetenciaDTO> choiceCompetencia = (ChoiceBox<CompetenciaDTO>) caracteristica.getChildren()
					.get(1);
			CompetenciaDTO competenciaDTO = (CompetenciaDTO) choiceCompetencia.getValue();
			TextField ponderacionInput = (TextField) caracteristica.getChildren().get(3);
			String ponderacion = ponderacionInput.getText();
			if (ponderacion.isEmpty() || !isNumber(ponderacion) || Integer.parseInt(ponderacion) < 0
					|| Integer.parseInt(ponderacion) > 10) {
				caracteristicaValid = false;
				formValid = false;

				if (!ponderacionInput.getStyleClass().contains("inputError")) {
					ponderacionInput.getStyleClass().add("inputError");
				}

			} else {
				ponderacionInput.getStyleClass().remove("inputError");
			}
			if (competenciaDTO == null) {
				caracteristicaValid = false;
				formValid = false;

				if (!choiceCompetencia.getStyleClass().contains("inputError")) {
					choiceCompetencia.getStyleClass().add("inputError");
				}

			} else {
				choiceCompetencia.getStyleClass().remove("inputError");
			}
			if (caracteristicaValid) {
				// caracteristicasDTO.clear();

				// System.out.println(caracteristicasDTO.size());
				PonderacionNecesariaDTO newCaracteristica = new PonderacionNecesariaDTO(Integer.parseInt(ponderacion),
						competenciaDTO.getIdComp());

				for (PonderacionNecesariaDTO ponderacionNecesariaDTO : caracteristicasDTO) {
					if (ponderacionNecesariaDTO.getIdComp() == newCaracteristica.getIdComp()) {

						newCaracteristica = ponderacionNecesariaDTO;
						break;
					}
				}

				if (caracteristicasDTO.contains(newCaracteristica)) {
					caracteristicaValid = false;
					formValid = false;
					if (!caracteristica.getStyleClass().contains("inputError")) {
						caracteristica.getStyleClass().add("inputError");
					}
				} else {
					caracteristica.getStyleClass().remove("inputError");
					caracteristicasDTO.add(newCaracteristica);
				}

			}
		}

		// setear valores puesto
		if (formValid) {
			puestoDTO.setCodigoPuesto(Integer.parseInt(codigoInput.getText()));
			puestoDTO.setNombrePuesto(nombre);
			puestoDTO.setDescripcionPuesto(descripcion);
			puestoDTO.setIdEmpresa(empresaDTO.getIdEmpresa());
			puestoDTO.setCaracteristicasDTO(caracteristicasDTO);
			puestoDTO.setIdPuesto(null);
		} else {
			caracteristicasDTO.clear();
			if (!dialogVbox.getChildren().contains(textError)) {

				if (!textError.getStyleClass().contains("inputError")) {
					textError.getStyleClass().add("textError");
				}
				textError.applyCss();
				dialogVbox.getChildren().add(textError);
			}

		}

		return formValid;
	}
}
