package fr.univ_lyon1.info.m1.cv_search.view;

import java.util.Map;


import fr.univ_lyon1.info.m1.cv_search.controller.ApplicantController;
import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.Observable;
import fr.univ_lyon1.info.m1.cv_search.model.Observer;
import fr.univ_lyon1.info.m1.cv_search.model.SkillList;
import fr.univ_lyon1.info.m1.cv_search.model.strategies.SkillStrategy;
import fr.univ_lyon1.info.m1.cv_search.model.strategies.Strategy;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;

public class JfxView extends Observer {
    private HBox searchSkillsBox;
    private VBox resultBox;
    private HBox strategyBox;
    private HBox strategyBox2;
    private ComboBox<SkillStrategy> cbStrategy;

    private ApplicantController controller;
    

    /**
     * Create the main view of the application.
     */
    public JfxView(ApplicantController c, Stage stage, int width, int height) {
        // Name of window
        stage.setTitle("Search for CV");

        controller = c;
        c.addViewToController(this);

        VBox root = new VBox();

        Node newSkillBox = createNewSkillWidget();
        root.getChildren().add(newSkillBox);

        Node searchSkillsBox = createCurrentSearchSkillsWidget();
        root.getChildren().add(searchSkillsBox);

        Node strategyBox = createSkillStrategyBox();
        root.getChildren().add(strategyBox);
        controller.updateSkillStrategyComboBox(
            (ComboBox<SkillStrategy>) ((HBox) strategyBox).getChildren().get(1));

        Node strategyBox2 = createStrategyBox2();
        root.getChildren().add(strategyBox2);
        controller.updateDisplayedStrategyView(
                (ListView<Strategy>) ((HBox) strategyBox2).getChildren().get(0));

        Node search = createSearchWidget();
        root.getChildren().add(search);

        Node resultBox = createResultsWidget();
        root.getChildren().add(resultBox);

        // Everything's ready: add it to the scene and display it
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    private Node createSkillStrategyBox() {
        strategyBox = new HBox();
        Label labelStrategy = new Label("Skill strategy: ");
        // nouvelle comboBpx de strategy
        cbStrategy = new ComboBox<SkillStrategy>();
        strategyBox.getChildren().addAll(labelStrategy, cbStrategy);
        //controller.updateSkillBox(strategyBox);
        return strategyBox;
    }

    private Node createStrategyBox2() {
        strategyBox2 = new HBox();
        ListView<Strategy> strategyListView = new ListView<>();
        strategyListView.setCellFactory(lv -> new ListCell<Strategy>() {
            @Override
            public void updateItem(Strategy row, boolean empty) {
                super.updateItem(row, empty);
                setText(empty ? null : row.getTitle());
            }
        });
        strategyListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        VBox buttonBox = new VBox();
        Button filter = new Button("Select strategies");
        filter.setOnAction(e -> controller.selectStrategies(
            (ListView<Strategy>) strategyBox2.getChildren().get(0)));
        Button removeSButton = new Button("Remove strategies");
        removeSButton.setOnAction(e -> controller.removeStrategies(
            (ListView<Strategy>) strategyBox2.getChildren().get(2)));
        buttonBox.getChildren().addAll(filter, removeSButton);

        // nouvelle comboBpx de strategy
        ListView<Strategy> strategyListViewUsed = new ListView<>();
        strategyListViewUsed.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        strategyBox2.getChildren().addAll(strategyListView, buttonBox, strategyListViewUsed);

        return strategyBox2;
    }
    

    /**
     * Create the text field to enter a new skill.
     */
    private Node createNewSkillWidget() {
        HBox newSkillBox = new HBox();
        Label labelSkill = new Label("Skill:");
        TextField textField = new TextField();
        Button submitButton = new Button("Add skill");
        newSkillBox.getChildren().addAll(labelSkill, textField, submitButton);
        newSkillBox.setSpacing(10);

        EventHandler<ActionEvent> skillHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.addSkillButtonHandler(textField);
            }
        };
        submitButton.setOnAction(skillHandler);
        textField.setOnAction(skillHandler);
        return newSkillBox;
    }

    /**
     * Create the widget showing the list of applicants.
     */
    private Node createResultsWidget() {
        resultBox = new VBox();
        TableView<Applicant> table = new TableView<>();
        TableColumn<Applicant, String> applicantNameCol 
            = new TableColumn<Applicant, String>("name");
        applicantNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        
        TableColumn<Applicant, String> applicantLastNameCol 
            = new TableColumn<Applicant, String>("last name");
        applicantLastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Applicant, String> applicantMailCol 
            = new TableColumn<Applicant, String>("email");
        applicantMailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Applicant, String> applicantGenderCol 
            = new TableColumn<Applicant, String>("gender");
        applicantGenderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Applicant, String> applicantAverageCol 
            = new TableColumn<Applicant, String>("average of skills");
        applicantAverageCol.setCellValueFactory(new PropertyValueFactory<>("average"));

        table.getColumns().addAll(applicantNameCol, applicantLastNameCol,
            applicantMailCol, applicantGenderCol, applicantAverageCol);
        resultBox.getChildren().addAll(table);
        return resultBox;
    }

    /**
     * Create the widget used to trigger the search.
     */
    private Node createSearchWidget() {
        Button search = new Button("Search");
        search.setOnAction(e -> controller.searchButtonHandler(this));
        return search;
    }

    /**
     * Create the widget showing the list of skills currently searched for.
     */
    private Node createCurrentSearchSkillsWidget() {
        searchSkillsBox = new HBox();
        return searchSkillsBox;
    }

    public ComboBox<SkillStrategy> getSkillStrategyComboBox() {
        return cbStrategy;
    }

    public HBox getSearchSkillBox() {
        return searchSkillsBox;
    }

    public VBox getResultBox() {
        return resultBox;
    }

    public ListView<Strategy> getAppliedListView() {
        return (ListView<Strategy>) strategyBox2.getChildren().get(2);
    }

    @Override
    public void notify(Observable obs, Object o) {
        Map<String, Object> params = (Map<String, Object>) o;
        String action = "None";
        if (params != null) {
            action = (String) params.get("action");
        }

        if (obs instanceof SkillList) {
            controller.updateSkillBox(searchSkillsBox);
        } else if (action.equals("ResultBox")) {
            controller.updateResultBox(resultBox);
        } else if (action.equals("DisplayedStrategyView")) {
            controller.updateDisplayedStrategyView(
                (ListView<Strategy>) ((HBox) strategyBox2).getChildren().get(0));
        } else if (action.equals("SelectedStrategyView")) {
            controller.updateSelectedStrategyView(
                (ListView<Strategy>) ((HBox) strategyBox2).getChildren().get(2));
        }
        controller.updateResultBox(resultBox);
    }
}
