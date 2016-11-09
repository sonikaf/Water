package edu.gatech.tbd.controller;

import edu.gatech.tbd.Main;
import edu.gatech.tbd.model.User;

/**
 * Controller for a scene.
 */
public abstract class SceneController {
	
	/**
    * reference to main application class
    */
    Main mainApp;
	
    /**
     * sets the mainApp field
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    
    /**
     * Routes user, worker, manager to main app and
     * routes admin to admin scene.
     * 
     * @param user current user
     */
    void routeUserOnLogin(User user) {
        if (user.isAdmin()) {
            mainApp.changeScene("AdminScene");
        } else {
            mainApp.changeScene("ApplicationScene");
        }
    }
    
}
