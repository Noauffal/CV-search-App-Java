package fr.univ_lyon1.info.m1.cv_search;

import fr.univ_lyon1.info.m1.cv_search.controller.ApplicantController;
import fr.univ_lyon1.info.m1.cv_search.view.JfxView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class for the application (structure imposed by JavaFX).
 */
public class App extends Application {

    /**
     * With javafx, start() is called when the application is launched.
     */
    @Override
    public void start(Stage stage) throws Exception {
        
        ApplicantController c = new ApplicantController();
        new JfxView(c, stage, 600, 600);
        new JfxView(c, new Stage(), 400, 400);
    }


    /**
     * A main method in case the user launches the application using
     * App as the main class.
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}
