package b1.capitalHumano.puesto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import b1.capitalHumano.competencia.Competencia;
import b1.capitalHumano.competencia.CompetenciaDAO;
import b1.capitalHumano.competencia.CompetenciaDAOImp;
import b1.capitalHumano.competencia.CompetenciaDTO;
import b1.capitalHumano.competencia.ControllerCompetencia;
import b1.capitalHumano.empresa.ControllerEmpresa;
import b1.capitalHumano.empresa.Empresa;
import b1.capitalHumano.empresa.EmpresaDAO;
import b1.capitalHumano.empresa.EmpresaDAOImp;
import b1.capitalHumano.empresa.EmpresaDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerGraficoPuestoModificar_Dialog {
	PuestoDAO puestoDAO = new PuestoDAOImp();
	EmpresaDAO empresaDAO = new EmpresaDAOImp();
	ControllerPuestos controllerPuesto = new ControllerPuestos();
	PuestoDTO puestoDTO;
	CompetenciaDAO competenciaDAO = new CompetenciaDAOImp();
	EmpresaDTO empresaDTO;
	ObservableList<EmpresaDTO> empresaOL;
	ObservableList<CompetenciaDTO> competenciaOL;
	Set<PonderacionNecesariaDTO> caracteristicasDTO = new HashSet<PonderacionNecesariaDTO>();
	Text textError = new Text("Se deben completar y/o veririficar todos los campos");
	Integer idPuesto;
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

	@FXML
	TextField puntajeInput;
	List<CompetenciaDTO> competenciasDTOList = ControllerCompetencia.getCompetencias();
	Stage stage;
	List<EmpresaDTO> empresasDTO;

	public void initialize() {
		empresasDTO = ControllerEmpresa.getEmpresas();
		empresaOL = FXCollections.observableArrayList(empresasDTO);
		competenciaOL = FXCollections.observableArrayList(competenciasDTOList);
		empresaChoice.setItems(empresaOL);

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
		VBoxCompetencia.getChildren().add(createCompetenciaFXML());
	}
	public void setPuesto(PuestoDTO puestoDTO) {
		this.puestoDTO = puestoDTO;
		idPuesto = puestoDTO.getIdPuesto();
		codigoInput.setText(idPuesto.toString());
		nombreInput.setText(puestoDTO.getNombrePuesto());
		descripcionInput.setText(puestoDTO.getDescripcionPuesto());
		// falta empresa y competencia
		// empresaChoice.getSelectionModel().select(puestoDTO.getIdEmpresa());
		for (EmpresaDTO empresa : empresasDTO) {
			if (empresa.getIdEmpresa() == puestoDTO.getIdEmpresa()) {
				empresaChoice.getSelectionModel().select(empresa);
				break;
			}
		}
		int cantidadCaracteristicas = puestoDTO.getCaracteristicasDTO().size();
		if (cantidadCaracteristicas < 1) {
			HBox aux = createCompetenciaFXML();
			VBoxCompetencia.getChildren().add(aux); 
		}
		for (PonderacionNecesariaDTO ponderacionNecesariaDTO : puestoDTO.getCaracteristicasDTO()) {

			HBox aux = createCompetenciaFXML();
			TextField auxInput = (TextField) aux.getChildren().get(3);
			ChoiceBox<CompetenciaDTO> auxChoice = (ChoiceBox<CompetenciaDTO>) aux.getChildren().get(1);
			auxInput.setText(ponderacionNecesariaDTO.getPonderacionNecesaria().toString());

			for (CompetenciaDTO competenciaDTO : competenciasDTOList) {
				if (competenciaDTO.getIdComp() == ponderacionNecesariaDTO.getIdComp()) {
					auxChoice.setValue(competenciaDTO);
				}
			}
			// (ChoiceBox<CompetenciaDTO>)aux.getChildren().get(1).set(false);
			VBoxCompetencia.getChildren().add(aux);
			//caracteristicasDTO.add(ponderacionNecesariaDTO);
		}
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
		ChoiceBox<CompetenciaDTO> choiceCompetencia;
		// Codigo
		String idPuesto = codigoInput.getText();

		// Nombre
		String nombre = nombreInput.getText();
		if (nombre.isEmpty()) {
			formValid = false;
			if (!nombreInput.getStyleClass().contains("inputError")) {
				nombreInput.getStyleClass().add("inputError");
			}
		} else {
			nombreInput.getStyleClass().remove("inputError");
		}

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
			choiceCompetencia = (ChoiceBox<CompetenciaDTO>) caracteristica.getChildren().get(1);
			CompetenciaDTO competenciaDTO = (CompetenciaDTO) choiceCompetencia.getValue();
			TextField ponderacionInput = (TextField) caracteristica.getChildren().get(3);
			String ponderacion = ponderacionInput.getText();
			if (ponderacion.isEmpty() || !isNumber(ponderacion)) {
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
			puestoDTO.setIdPuesto(Integer.parseInt(codigoInput.getText()));
			puestoDTO.setNombrePuesto(nombre);
			puestoDTO.setDescripcionPuesto(descripcion);
			puestoDTO.setIdEmpresa(empresaDTO.getIdEmpresa());
			puestoDTO.setCaracteristicasDTO(caracteristicasDTO);
		
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
