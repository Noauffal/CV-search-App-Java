package fr.univ_lyon1.info.m1.cv_search.view.strategies;

import java.util.HashMap;



import fr.univ_lyon1.info.m1.cv_search.model.Observable;

import fr.univ_lyon1.info.m1.cv_search.model.strategies.Strategy;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;




public class YearsExpStrategyView extends Observable {
    
    /**
     * Create the view for strategy.
     */
    public YearsExpStrategyView(Stage stage, Integer width, Integer height, Strategy s) {
        addObserver(s);
        // Name of window
        stage.setTitle("Search for CV");

        VBox root = new VBox();
        Label labelStrategy = new Label("Minimum years of experience: ");
        TextField textField = new TextField();
        Button submitButton = new Button("OK");
        submitButton.setOnAction(e -> {
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("nbY", textField.getText().strip());
            change();
            notifyObservers(params);
            stage.close();
        });

        root.getChildren().addAll(labelStrategy, textField, submitButton);

        // Everything's ready: add it to the scene and display it
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }
}
