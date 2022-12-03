package b1.capitalHumano.puesto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import b1.capitalHumano.App;
import b1.capitalHumano.ControllerGraficoCuestionario;
import b1.capitalHumano.consultor.ConsultorDTO;
import b1.capitalHumano.consultor.ControllerGraficoPantallaPrincipal;
import b1.capitalHumano.empresa.Empresa;
import b1.capitalHumano.usuario.ControllerGraficoAutenticarUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerGraficoPuestos {

	@FXML
	TextField codigoInput;
	@FXML
	TextField nombreInput;
	@FXML
	TextField empresaInput;
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
	private static FXMLLoader fxmlLoader;
	ContextMenu contextMenu = new ContextMenu();
	ControllerPuestos controllerPuesto = new ControllerPuestos();
	ConsultorDTO consultorDTO;
	// Creating the menu Items for the context menu
	MenuItem modificar = new MenuItem("Modificar");
	MenuItem eliminar = new MenuItem("Eliminar");
	List<PuestoDTO> puestosDTO;
	private static Stage stage;
	
	
	public void setStageAndSetupListeners(Stage stage) {
		ControllerGraficoPuestos.stage = stage;
	}

	private static Parent loadFXML(String fxml) throws IOException {
		fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}
	

	public void inicioHandleMenu() throws IOException {

		stage.getScene().setRoot(loadFXML("PantallaPrincipal"));
		ControllerGraficoPantallaPrincipal 
		controllerGraficoPantallaPrincipal = (ControllerGraficoPantallaPrincipal) fxmlLoader
				.getController();
		controllerGraficoPantallaPrincipal.setStageAndSetupListeners(stage);
		controllerGraficoPantallaPrincipal.setConsultor(consultorDTO);
	}


	
	public void actualizarTabla() {

		ControllerPuestos controllerPuesto = new ControllerPuestos();

		puestosDTO = controllerPuesto.getPuestos();

		puestosOL = FXCollections.observableArrayList(puestosDTO);

		Predicate<PuestoDTO> estaEliminado = p -> p.getEliminado() == true;

		puestosOL.removeIf(estaEliminado);

		puestoTable.setItems(puestosOL);

	}

	public void buscarHandle() {

		List<PuestoDTO> puestosEncontrados = controllerPuesto.buscarPuestos(codigoInput.getText(),
				nombreInput.getText(), empresaInput.getText());

		puestosOL = FXCollections.observableArrayList(puestosEncontrados);

		Predicate<PuestoDTO> estaEliminado = p -> !puestosEncontrados.contains(p) || p.getEliminado() == true;

		puestosOL.removeIf(estaEliminado);
		puestoTable.setItems(puestosOL);

	}
	public void initialize() {
		contextMenu.getItems().addAll(modificar, eliminar);
		actualizarTabla();
		idColumn.setCellValueFactory(new PropertyValueFactory<PuestoDTO, Integer>("idPuesto"));
		nombreColumn.setCellValueFactory(new PropertyValueFactory<PuestoDTO, String>("nombrePuesto"));
		empresaColumn.setCellValueFactory(new PropertyValueFactory<PuestoDTO, String>("nombreEmpresa"));
		descripcionColumn.setCellValueFactory(new PropertyValueFactory<PuestoDTO, String>("descripcionPuesto"));

		puestoTable.getSelectionModel().selectedItemProperty()
				.addListener((puestosOL, seleccionVieja, seleccionNueva) -> {
					if (seleccionNueva != null) {
						puestoTable.setContextMenu(contextMenu);
						eliminar.setOnAction(event -> {
							PuestoDTO puestoDTOEliminado = controllerPuesto
									.eliminarPuesto(seleccionNueva.getIdPuesto());
							if (puestoDTOEliminado == null) {
								Alert alert = new Alert(Alert.AlertType.WARNING);
								alert.setHeaderText(null);
								alert.setTitle("No se pudo eliminar");
								alert.setContentText("El puesto '" + seleccionNueva.getNombrePuesto()
										+ "' posee evaluaciones y no se puede borrar.");
								alert.showAndWait();
							}
							actualizarTabla();
						});

						modificar.setOnAction(event -> {
							try {
								FXMLLoader fxmlLoader = new FXMLLoader();
								fxmlLoader.setLocation(getClass().getResource("GestionarPuesto--Modificar.fxml"));
								DialogPane puestoDialogPane = fxmlLoader.load();

								puestoDialogPane.getStylesheets()
										.add(getClass().getResource("styles.css").toExternalForm());

								Dialog<ButtonType> dialog = new Dialog<>();
								dialog.setDialogPane(puestoDialogPane);
								ControllerGraficoPuestoModificar_Dialog controllerDialog = (ControllerGraficoPuestoModificar_Dialog) fxmlLoader
										.getController();
								controllerDialog.setPuesto(seleccionNueva);
								final Button btOk = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
								btOk.addEventFilter(ActionEvent.ACTION, e -> {
									if (!controllerDialog.validar()) {
										e.consume();
									}
								});
								Optional<ButtonType> clickedButton = dialog.showAndWait();
								if (clickedButton.get() == ButtonType.OK) {

									handleModificar(seleccionNueva);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}

							actualizarTabla();

						});

					} else {

						puestoTable.setContextMenu(null);
					}
				});
	}

	public void handleModificar(PuestoDTO puestoDTO) {

		controllerPuesto.modificarPuesto(puestoDTO);

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
			btOk.addEventFilter(ActionEvent.ACTION, event -> {
				if (!controllerDialog.validar()) {
					// System.out.println("error");
					event.consume();
				}
			});
			Optional<ButtonType> clickedButton = dialog.showAndWait();
			if (clickedButton.get() == ButtonType.OK) {
				// System.out.println(newPuesto.getIdPuesto());
				controllerPuesto.darDeAltaPuesto(newPuesto);
				actualizarTabla();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setConsultorDTO(ConsultorDTO consultorDTO) {
		// TODO Auto-generated method stub
		this.consultorDTO = consultorDTO;
		
	}
}
