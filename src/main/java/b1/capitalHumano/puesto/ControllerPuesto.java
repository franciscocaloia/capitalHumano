package b1.capitalHumano.puesto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import b1.capitalHumano.Empresa;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControllerPuesto{
	private Puesto puesto;
	ObservableList<Puesto> puestosOL;
	
	 @FXML
	TableView<Puesto> puestoTable;
	 @FXML
	TableColumn<Puesto,Integer> idColumn ;
	 @FXML
	TableColumn<Puesto,String> nombreColumn ;
	 @FXML
	TableColumn<Puesto,String> empresaColumn;
	 @FXML
	TableColumn<Puesto,String> descripcionColumn;
	 
	 public static List<Puesto> getAllInstances(){
		 List<Puesto>puestos = new ArrayList<Puesto>();
		 Empresa gregolyPower = new Empresa(1,"gregolyPower");
			puestos.add(new Puesto(1,"gerente","Esta a cargo de los empleados",gregolyPower));
			puestos.add(new Puesto(2,"empleado administrativo","realiza trabajos administrativos",gregolyPower));
			puestos.add(new Puesto(3,"empleado limpieza","realiza trabajos de limpieza",gregolyPower));
			return puestos;
		}
	 
//	public void initialize() {
//		List<Puesto>puestos = getAllInstances();
//		puestosOL = FXCollections.observableArrayList(puestos);
//		puestoTable.setItems(puestosOL);
//		idColumn.setCellValueFactory(new PropertyValueFactory<Puesto,Integer>("idPuesto"));
//		nombreColumn.setCellValueFactory(new PropertyValueFactory<Puesto,String>("nombrePuesto"));
//		//cambiar
//		empresaColumn.setCellValueFactory(new PropertyValueFactory<Puesto,String>("nombreEmpresa"));
//		descripcionColumn.setCellValueFactory(new PropertyValueFactory<Puesto,String>("descripcionPuesto"));
//	 	}
	public void handleAdd() {
		try {
//			Empresa gregolyPower = new Empresa(1,"gregolyPower");
//			PuestoDAOImp db = new PuestoDAOImp();
//			db.insert(new Puesto(3,"empleado limpieza","realiza trabajos de limpieza",gregolyPower));
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("GestionarPuesto--DarDeAlta.fxml"));
			DialogPane puestoDialogPane = fxmlLoader.load();
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setDialogPane(puestoDialogPane);
			Optional<ButtonType> clickedButton = dialog.showAndWait();
			if (clickedButton.get() == ButtonType.OK){
    			System.out.println("User selected ok"); 
    		}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	public void handleDialogConfirm() {
		
	}
}