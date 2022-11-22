package b1.capitalHumano.puesto;

import java.util.List;
import java.util.Optional;

import b1.capitalHumano.empresa.Empresa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerGraficoPuestos {
	@FXML
	TableView<PuestoDTO> puestoTable;
	@FXML
	TableColumn<PuestoDTO, Integer> idColumn;
	@FXML
	TableColumn<PuestoDTO, String> nombreColumn;
	@FXML
	TableColumn<PuestoDTO, String> empresaColumn;
	@FXML
	TableColumn<PuestoDTO, String> descripcionColumn;
	
	ObservableList<PuestoDTO> puestosOL;
	ObservableList<Empresa> empresaObs;
	
	public void actualizarTabla() {
		List<PuestoDTO> puestosDTO = ControllerPuestos.getPuestos();
		puestosOL = FXCollections.observableArrayList(puestosDTO);
		puestoTable.setItems(puestosOL);
	}
	public void initialize() {
		actualizarTabla();	
		idColumn.setCellValueFactory(new PropertyValueFactory<PuestoDTO, Integer>("idPuesto"));
		nombreColumn.setCellValueFactory(new PropertyValueFactory<PuestoDTO, String>("nombrePuesto"));
		empresaColumn.setCellValueFactory(new PropertyValueFactory<PuestoDTO, String>("nombreEmpresa"));
		descripcionColumn.setCellValueFactory(new PropertyValueFactory<PuestoDTO, String>("descripcionPuesto"));
	}
	
	public void handleAdd() {
		try {
			PuestoDTO newPuesto = new PuestoDTO();
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("GestionarPuesto--DarDeAlta.fxml"));
			DialogPane puestoDialogPane = fxmlLoader.load();
			puestoDialogPane.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
			
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setDialogPane(puestoDialogPane);
			ControllerGraficoPuestos_Dialog controllerDialog = fxmlLoader.getController();
			
			controllerDialog.setPuesto(newPuesto);
			final Button btOk = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
			btOk.addEventFilter(
			    ActionEvent.ACTION, 
			    event -> {
			        if (!controllerDialog.validar()) {
			        	System.out.println("error");
			            event.consume();
			        }
			    }
			);
			Optional<ButtonType> clickedButton = dialog.showAndWait();
			if (clickedButton.get() == ButtonType.OK) {
				System.out.println(newPuesto.getIdPuesto());
				ControllerPuestos.darDeAltaPuesto(newPuesto);
				actualizarTabla();
			}
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
}
