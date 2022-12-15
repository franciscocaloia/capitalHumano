package b1.capitalHumano;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import b1.capitalHumano.candidato.ControllerGraficoEvaluarCandidatos;
import b1.capitalHumano.puesto.PuestoDAOImp;
import b1.capitalHumano.usuario.ControllerGraficoAutenticarUsuario;

/**
 * JavaFX App
 */
public class App extends Application {
	 private static FXMLLoader fxmlLoader;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
    	
    	//evaluar coandidato
//    	scene = new Scene(loadFXML("candidato/EvaluarCandidatos"));
//    	 ControllerGraficoEvaluarCandidatos controllerGraficoEvaluarCandidatos = (ControllerGraficoEvaluarCandidatos)fxmlLoader.getController();
//    	 controllerGraficoEvaluarCandidatos.setStageAndSetupListeners(stage);
//    
    	//gestionar
    //	scene = new Scene(loadFXML("puesto/GestionarPuesto--inicio"));
    	 //////////////////////////
    //Flujo desde autenticar
 	  	 scene = new Scene(loadFXML("usuario/AutenticacionDeUsuario--inicio"));
 		 ControllerGraficoAutenticarUsuario controllerGrafico = (ControllerGraficoAutenticarUsuario)fxmlLoader.getController();
 	 	 controllerGrafico.setStageAndSetupListeners(stage);
    	 //////////////////////
        stage.setScene(scene); 
        stage.setResizable(false);
        stage.show();
    }

    private static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
         fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}