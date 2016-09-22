package edu.gatech.tbd;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import edu.gatech.tbd.controllers.*;

public class Main extends Application {
	
    /**
     * main stage for application
     */
	private static Stage stage;


	@Override
	public void start(Stage primaryStage) {
		// save our stage for later use
		stage = primaryStage;
		
	    // load the welcome scene and show the window	    
        stage.setTitle("Team 19");
        changeScene("WelcomeScene");
        stage.show();
	       
	}
	
	public void changeScene(String path) {
		if(stage == null) return;
		try {
            // loading LoginStage
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/edu/gatech/tbd/scenes/" + path + ".fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            // adding Main reference to LoginStageController
            SceneController controller = loader.getController();
            controller.setMainApp(this);
            
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            System.err.println("Error loading stage!");
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
