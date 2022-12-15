package b1.capitalHumano.cuestionario;

import javafx.stage.Stage;

public class ControllerGraficoCuestionario {
	private static Stage stage;
	private CuestionarioDTO cuestionarioDTO;

	public void setCuestionario(CuestionarioDTO cuestionarioActivoDTO) {
		// TODO Auto-generated method stub
		this.cuestionarioDTO = cuestionarioActivoDTO;

	}

	public void setStageAndSetupListeners(Stage stage) {
		// TODO Auto-generated method stub
		this.stage = stage;
	}

}
