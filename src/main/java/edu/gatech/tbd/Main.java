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
		Parent root;
		stage = primaryStage;
		try {
		    // loading login stage
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(
			        "/edu/gatech/tbd/stages/LoginStage.fxml"));
			root = loader.load();
			Scene scene = new Scene(root);
		    
		    // adding Main reference to LoginStageController
		    LoginStageController controller = loader.getController();
		    controller.setMainApp(this);
		    
	        stage.setTitle("Team 19");
	        stage.setScene(scene);
	        stage.show();
	        
		} catch (IOException e) {
			System.err.println("Error loading main stage!");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	/**
     * Changes scene to LoginStage
     */
    public void showLoginStage() { 
        try {
            // loading LoginStage
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(
                    "/edu/gatech/tbd/stages/LoginStage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            // adding Main reference to LoginStageController
            LoginStageController controller = loader.getController();
            controller.setMainApp(this);
            
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            System.err.println("Error loading login stage!");
            e.printStackTrace();
        }
    }
	
	/**
	 * Changes scene to ApplicationStage
	 */
	public void showApplicationStage() { 
	    try {
	        // loading ApplicationStage
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("/edu/gatech/tbd/stages/ApplicationStage.fxml"));
	        Parent root = loader.load();
            Scene scene = new Scene(root);
            
            // adding Main reference to ApplicationStageController
            ApplicationStageController controller = loader.getController();
            controller.setMainApp(this);
            
            stage.setScene(scene);
	        stage.show();
	        
	    } catch (IOException e) {
	        System.err.println("Error loading application stage!");
            e.printStackTrace();
	    }
	}
	
}
