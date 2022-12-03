package b1.capitalHumano.candidato;

import java.util.List;

import b1.capitalHumano.empresa.Empresa;
import b1.capitalHumano.puesto.PuestoDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerEvaluarCandidatoGrafico {
	ControllerCandidato controllerCandidato = new ControllerCandidato();
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
	ObservableList<CandidatoDTO> CandidatoOl;
	ObservableList<CandidatoDTO> CandidatoOlEv;
	public void initialize() {
		
		numeroCandidatoColumn.setCellValueFactory(new PropertyValueFactory<CandidatoDTO, Integer>("idCandidato"));
		nombreCandidatoColumn.setCellValueFactory(new PropertyValueFactory<CandidatoDTO, String>("nombre"));
		apellidoCandidatoColumn.setCellValueFactory(new PropertyValueFactory<CandidatoDTO, String>("aepllido"));

		
		List<CandidatoDTO> cadidatosDTO = controllerCandidato.getCandidatos();
		CandidatoOl = FXCollections.observableArrayList(cadidatosDTO);
		candidatoTable.setItems(CandidatoOl);
	}


}
