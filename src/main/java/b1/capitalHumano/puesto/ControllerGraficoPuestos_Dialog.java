package b1.capitalHumano.puesto;

import java.util.ArrayList;
import java.util.HashSet;
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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerGraficoPuestos_Dialog {
	PuestoDTO puestoDTO;
	
	ObservableList<EmpresaDTO> empresaOL;
	ObservableList<CompetenciaDTO> competenciaOL;
	
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
	
	Stage stage;
	
	public void initialize() {
		competenciaOL=FXCollections.observableArrayList(ControllerCompetencia.getCompetencias());
		empresaOL = FXCollections.observableArrayList(ControllerEmpresa.getEmpresas());
		empresaChoice.setItems(empresaOL);
		VBoxCompetencia.getChildren().add(createCompetenciaFXML());
	}
	
	public HBox createCompetenciaFXML() {
		HBox newCompetencia = new HBox();
		newCompetencia.setPrefHeight(50);
		Text textCompetencia = new Text("Competencia");
		ChoiceBox<CompetenciaDTO> choiceCompetencia = new ChoiceBox<>();
		Text textPonderacion = new Text("Ponderacion");
		TextField ponderacionCompetencia = new TextField();
		choiceCompetencia.setItems(competenciaOL);
		newCompetencia.getChildren().addAll(textCompetencia,choiceCompetencia,textPonderacion,ponderacionCompetencia);
		return newCompetencia;
	}
	public void handleAddCompetencia() {
		VBoxCompetencia.getChildren().add(createCompetenciaFXML());
		stage = (Stage) dialog.getScene().getWindow();
		stage.setHeight(stage.getHeight()+50);
	}
	public void setPuesto(PuestoDTO puestoDTO){
		this.puestoDTO = puestoDTO;
	}
	public boolean isNumber(String input) {
		Pattern pattern = Pattern.compile("[0-9]+");
		if(pattern.matcher(input).matches()) {
			return true;
		}else return false;
	}
	public boolean validar() {
		Boolean formValid = true;
		//Codigo
		String idPuesto = codigoInput.getText();
		Pattern pattern = Pattern.compile("[0-9]+");		
		if(idPuesto.isEmpty() || !isNumber(idPuesto)) {
			formValid=false;
			codigoInput.getStyleClass().add("inputError");
		}else {
			codigoInput.getStyleClass().remove("inputError");
		}
		
		//Nombre
		String nombre = nombreInput.getText();
		if(nombre.isEmpty()) {
			formValid=false;
			nombreInput.getStyleClass().add("inputError");
		}else {
			nombreInput.getStyleClass().remove("inputError");
		}
		
		//Descripcion
		String descripcion=descripcionInput.getText();
		if(descripcion.isEmpty()) {
			formValid=false;
			descripcionInput.getStyleClass().add("inputError");
		}else {
			descripcionInput.getStyleClass().remove("inputError");
		}
		//Empresa
		EmpresaDTO empresaDTO =(EmpresaDTO) empresaChoice.getValue();
		if(empresaDTO == null) {
			formValid=false;
			empresaChoice.getStyleClass().add("inputError");
		}else {
			empresaChoice.getStyleClass().remove("inputError");
		}
		
		//Caracteristicas
		Set<PonderacionNecesariaDTO> caracteristicasDTO = new HashSet<PonderacionNecesariaDTO>();
		boolean caracteristicaValid;
		for(Node child: VBoxCompetencia.getChildren()) {
			caracteristicaValid=true;
			HBox caracteristica = (HBox) child;
			ChoiceBox<CompetenciaDTO> choiceCompetencia = (ChoiceBox<CompetenciaDTO>) caracteristica.getChildren().get(1);
			CompetenciaDTO competenciaDTO =(CompetenciaDTO) choiceCompetencia.getValue();
			TextField ponderacionInput = (TextField) caracteristica.getChildren().get(3);
			String ponderacion = ponderacionInput.getText();
			if(ponderacion.isEmpty() || !isNumber(ponderacion)) {
				caracteristicaValid=false;
				formValid=false;
				ponderacionInput.getStyleClass().add("inputError");
			}else {
				ponderacionInput.getStyleClass().remove("inputError");
			}
			if(competenciaDTO == null) {
				caracteristicaValid=false;
				formValid=false;
				choiceCompetencia.getStyleClass().add("inputError");
			}else {
				choiceCompetencia.getStyleClass().remove("inputError");
			}
			if(caracteristicaValid) {
				PonderacionNecesariaDTO newCaracteristica = new PonderacionNecesariaDTO(Integer.parseInt(ponderacion),competenciaDTO.getIdComp());
				if(caracteristicasDTO.contains(newCaracteristica)) {
					caracteristicaValid=false;
					formValid=false;
					caracteristica.getStyleClass().add("inputError");
				}else {
					caracteristica.getStyleClass().remove("inputError");
					caracteristicasDTO.add(newCaracteristica);
				}
				
			}
		}
		
		
		//setear valores puesto
		if(formValid) {
			puestoDTO.setIdPuesto(Integer.parseInt(codigoInput.getText()));
			puestoDTO.setNombrePuesto(nombre);
			puestoDTO.setDescripcionPuesto(descripcion);
			puestoDTO.setIdEmpresa(empresaDTO.getIdEmpresa());
			puestoDTO.setCaracteristicasDTO(caracteristicasDTO);
		}else {
			Text textError = new Text("Se deben completar todos los campos");
			textError.getStyleClass().add("textError");
			textError.applyCss();
			dialogVbox.getChildren().add(textError);
		}
		
		return formValid;
	}
}
