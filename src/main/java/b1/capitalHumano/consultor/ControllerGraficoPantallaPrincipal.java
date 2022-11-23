package b1.capitalHumano.consultor;

import b1.capitalHumano.usuario.ControllerGraficoAutenticarUsuario;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerGraficoPantallaPrincipal {
private static ConsultorDTO consultorDTO;
private static Stage stage;


@FXML
Text textoPrincipal;

public void initialize() {


}

public ConsultorDTO getConsultor() {
	return consultorDTO;
}

public  void setConsultor(ConsultorDTO consultorDTO) {
	ControllerGraficoPantallaPrincipal.consultorDTO = consultorDTO;
	System.out.println(consultorDTO.getNombre());
	textoPrincipal.setText("Bienvenido"+consultorDTO.getNombre());

}
public void setStageAndSetupListeners(Stage stage) {
	ControllerGraficoPantallaPrincipal.stage = stage;
}
}
