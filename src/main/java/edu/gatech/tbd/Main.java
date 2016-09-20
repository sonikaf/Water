package edu.gatech.tbd;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static Stage stage;

	@Override
	public void start(Stage primaryStage) {
		Parent root;
		stage = primaryStage;
		try {
			root = FXMLLoader.load(getClass().getResource("/edu/gatech/tbd/stages/MainStage.fxml"));
		    Scene scene = new Scene(root, 800, 600);
		    
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
}
