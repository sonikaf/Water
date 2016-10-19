package edu.gatech.tbd;

import java.io.IOException;

import edu.gatech.tbd.controller.*;
import edu.gatech.tbd.model.WaterReportManager;
import edu.gatech.tbd.model.UserException;
import edu.gatech.tbd.model.UserManager;
import edu.gatech.tbd.model.UserType;
import edu.gatech.tbd.model.WaterCondition;
import edu.gatech.tbd.model.WaterType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * This class launches the application, changes scenes, and contains a reference
 * to the current controller.
 */
public class Main extends Application {

	/**
	 * Main stage for application.
	 */
	private static Stage stage;
	
	/**
	 * Current controller being used.
	 */
	private static SceneController currentController;
	
	/**
	 * Stage for popup windows.
	 */
	private static Stage popup;
	
	/**
	 * Controller for the current popup window.
	 */
	private static SceneController popupController;

	@Override
	public void start(Stage primaryStage) {
		// Save our stage for later use.
		stage = primaryStage;
		
		
		// Temporary Test.
		try {
            UserManager.registerUser("admin", "admin", "none", UserType.Administrator, "none", "none");
            UserManager.logoutUser();
            UserManager.registerUser("user", "user", "none", UserType.User, "none", "none");
            WaterReportManager.registerAvailabilityReport(33.774804,-84.3976288, WaterType.Bottled, WaterCondition.Potable);
            UserManager.logoutUser();
            UserManager.registerUser("worker", "worker", "none", UserType.Worker, "none", "none");
            UserManager.logoutUser();
            UserManager.registerUser("manager", "manager", "none", UserType.Manager, "none", "none");
            UserManager.logoutUser();
            
        } catch (UserException e) {
            e.printStackTrace();
        }
		
		
		// Load the welcome scene and show the window.
		stage.setTitle("Team 19");
		changeScene("WelcomeScene");
		stage.show();

	}
	
	/**
	 * Changes the scene.
	 * 
	 * @param path The path name for the scene to change to.
	 */
	public void changeScene(String path) {
		if (stage == null)
			return;
		try {
			// loading LoginStage
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/edu/gatech/tbd/view/" + path + ".fxml"));
			if (loader.getLocation() == null) {
				System.err.println("Error loading scene '/edu/gatech/tbd/view/" + path + ".fxml'");
				System.err.println("Make sure the file exists!");
				return;
			}
			Parent root = loader.load();
			Scene scene = new Scene(root);

			// adding Main reference to SceneController
			currentController = loader.getController();
			if(currentController != null)
				currentController.setMainApp(this);

			stage.setScene(scene);
			stage.show();
			
			// center the new scene on the screen
			Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
	        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
	        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

		} catch (IOException e) {
			System.err.println("Error loading stage!");
			e.printStackTrace();
		}
	}
	
	/**
	 * Opens up a popup window.
	 * 
	 * @param path The path name for the scene on the popup stage.
	 */
	public void doPopupWindow(String path) {
		if(popup != null) {
			System.err.println("There is already an open popup window!");
			return;
		}
		try {
			// loading LoginStage
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/edu/gatech/tbd/view/" + path + ".fxml"));
			if (loader.getLocation() == null) {
				System.err.println("Error loading scene '/edu/gatech/tbd/view/" + path + ".fxml'");
				System.err.println("Make sure the file exists!");
				return;
			}
			Parent root = loader.load();
			Scene scene = new Scene(root);

			// adding Main reference to LoginStageController
			popupController = loader.getController();
			if(popupController != null)
				popupController.setMainApp(this);
			popup = new Stage();
			popup.initModality(Modality.WINDOW_MODAL);
			popup.initOwner(stage);

			popup.setScene(scene);
			popup.show();
			
			popup.setOnCloseRequest((e) -> {
				popup = null;
			});
		} catch (IOException e) {
			System.err.println("Error loading stage!");
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes the current popup window.
	 */
	public void closePopup() {
		if(popup == null) {
			System.err.println("No popup window is open, can't close.");
			return;
		}
		popup.close();
		popup = null;
	}
	
	/**
	 * Returns the current scene controller.
	 * 
	 * @return currentController The current scene controller.
	 */
	public SceneController getCurrentController() {
		return currentController;
	}
	
	/**
     * Returns the current popup scene controller.
     * 
     * @return popupController The current popup controller.
     */
	public SceneController getPopupController() {
		return popupController;
	}
	
	/**
	 * The main method for the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}