package edu.gatech.tbd;

import java.io.IOException;

import edu.gatech.tbd.controller.*;
import edu.gatech.tbd.model.UserException;
import edu.gatech.tbd.model.UserManager;
import edu.gatech.tbd.model.UserType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
    /**
     * main stage for application
     */
	private static Stage stage;
	private static SceneController currentController;


	@Override
	public void start(Stage primaryStage) {
		// save our stage for later use
		stage = primaryStage;
		
	    // load the welcome scene and show the window	    
        stage.setTitle("Team 19");
        try {
			UserManager.registerUser("admin", "admin", "none", UserType.Administrator, "none", "none");
			UserManager.logoutUser();
		} catch (UserException e) {
			e.printStackTrace();
		}
        changeScene("WelcomeScene");
        stage.show();
	       
	}
	
	public void changeScene(String path) {
		if(stage == null) return;
		try {
            // loading LoginStage
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/edu/gatech/tbd/view/" + path + ".fxml"));
            if(loader.getLocation() == null) {
            	System.err.println("Error loading scene '/edu/gatech/tbd/view/" + path + ".fxml'");
            	System.err.println("Make sure the file exists!");
            	return;
            }
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            // adding Main reference to LoginStageController
            currentController = loader.getController();
            currentController.setMainApp(this);
            
            
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            System.err.println("Error loading stage!");
            e.printStackTrace();
        }
	}
	
	public SceneController getCurrentController() {
		return currentController;
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}


