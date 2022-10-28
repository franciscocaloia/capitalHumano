package b1.capitalHumano.puesto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import b1.capitalHumano.Empresa;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ControllerPuesto {
	ObservableList<Puesto> puestosOL;
	private static List<Empresa> listaEmpresa;
	@FXML
	TableView<Puesto> puestoTable;
	@FXML
	TableColumn<Puesto, Integer> idColumn;
	@FXML
	TableColumn<Puesto, String> nombreColumn;
	@FXML
	TableColumn<Puesto, String> empresaColumn;
	@FXML
	TableColumn<Puesto, String> descripcionColumn;

	public static List<Empresa> getAllInstancesEmpresa() {
		Session session = new Configuration().configure().addAnnotatedClass(Empresa.class).buildSessionFactory()
				.openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Empresa> criteriaQuery = criteriaBuilder.createQuery(Empresa.class);
		Root<Empresa> root = criteriaQuery.from(Empresa.class);
		criteriaQuery.select(root);
		Query<Empresa> query = session.createQuery(criteriaQuery);
		List<Empresa> Empresas = query.getResultList();
		return Empresas;
	}

	public static List<Puesto> getAllInstances() {

		Session session = new Configuration().configure().addAnnotatedClass(PuestoDTO.class).buildSessionFactory()
				.openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<PuestoDTO> criteriaQuery = criteriaBuilder.createQuery(PuestoDTO.class);
		Root<PuestoDTO> root = criteriaQuery.from(PuestoDTO.class);
		criteriaQuery.select(root);
		Query<PuestoDTO> query = session.createQuery(criteriaQuery);
		List<PuestoDTO> puestosDTO = query.getResultList();

		List<Puesto> puestos = new ArrayList<>();

		for (PuestoDTO puesto : puestosDTO) {
			puestos.add(new Puesto(puesto.getIdPuesto(), puesto.getNombrePuesto(), puesto.getDescripción(),
					listaEmpresa.get(puesto.getIdEmpresa())));
		}
		return puestos;

	}

	public void actualizarTabla() {
		List<Puesto> puestos = getAllInstances();
		puestosOL = FXCollections.observableArrayList(puestos);
		puestoTable.setItems(puestosOL);
		idColumn.setCellValueFactory(new PropertyValueFactory<Puesto, Integer>("idPuesto"));
		nombreColumn.setCellValueFactory(new PropertyValueFactory<Puesto, String>("nombrePuesto")); // cambiar
		empresaColumn.setCellValueFactory(new PropertyValueFactory<Puesto, String>("nombreEmpresa"));
		descripcionColumn.setCellValueFactory(new PropertyValueFactory<Puesto, String>("descripcionPuesto"));
	}

	public void initialize() {
		// obtiene empresa de la db
		listaEmpresa = getAllInstancesEmpresa();
//actualziar tabla si queres cambia estos metodos down
		actualizarTabla();
	}

	@FXML
	public void handleAdd() {
		ObservableList<Empresa> empresaObs = FXCollections.observableArrayList(listaEmpresa);

		try {

			//
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("GestionarPuesto--DarDeAlta.fxml"));
			DialogPane puestoDialogPane = fxmlLoader.load();
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setDialogPane(puestoDialogPane);

			TextField codigoInput = (TextField) fxmlLoader.getNamespace().get("codigoInput");
			TextField nombreInput = (TextField) fxmlLoader.getNamespace().get("nombreInput");
			TextField descripcionInput = (TextField) fxmlLoader.getNamespace().get("descripcionInput");
			ChoiceBox<Empresa> empresaChoice = (ChoiceBox<Empresa>) fxmlLoader.getNamespace().get("empresaChoice");
			empresaChoice.setItems(empresaObs);

			Optional<ButtonType> clickedButton = dialog.showAndWait();

			if (clickedButton.get() == ButtonType.OK) {
				System.out.println("User selected ok");
				// FALTA VERIFICAR LOS INPUT
				PuestoDAOImp.insert(new Puesto(Integer.parseInt(codigoInput.getText()), nombreInput.getText(),
						descripcionInput.getText(), (Empresa) empresaChoice.getValue()));

			}
			actualizarTabla();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void handleDialogConfirm() {

	}
}