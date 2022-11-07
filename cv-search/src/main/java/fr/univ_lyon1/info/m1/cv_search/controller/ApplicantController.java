package fr.univ_lyon1.info.m1.cv_search.controller;

import fr.univ_lyon1.info.m1.cv_search.view.JfxView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;



import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantListBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.SkillList;
import fr.univ_lyon1.info.m1.cv_search.model.StrategyList;
import fr.univ_lyon1.info.m1.cv_search.model.strategies.SkillStrategy;
import fr.univ_lyon1.info.m1.cv_search.model.strategies.Strategy;
import fr.univ_lyon1.info.m1.cv_search.model.strategies.SkillStrategy.StrategyTypes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;


public class ApplicantController {

    private List<JfxView> listViews;
    private SkillList skillList;
    private ApplicantList resultApllicantList;
    private ApplicantList listApplicants = new ApplicantListBuilder(new File(".")).build();
    private ObservableList<Applicant> resultApplicant = FXCollections.observableArrayList();
    private AccessingAllClassesInPackage instance = new AccessingAllClassesInPackage();

    private StrategyList listAvailableStrategies = new StrategyList();
    private StrategyList listDisplayedStrategies = new StrategyList("DisplayedStrategyView");
    private StrategyList listAppliedStrategies = new StrategyList("SelectedStrategyView");

    /**
     * Constructeur du controller.
     */
    public ApplicantController() {
        initController();
    }

    /**
     * Ajoute la view a une liste de view. 
     * la nouvelle view observe les modeles.
     * @param view a JfxView.
     */
    public void addViewToController(JfxView view) {
        listViews.add(view);
        skillList.addObserver(view);
        listDisplayedStrategies.addObserver(view);
        listAppliedStrategies.addObserver(view);
        resultApllicantList.addObserver(view);
    }

    /**
     * initialise les elements instanciés par le controller.
     */
    public void initController() {
        listViews = new ArrayList<>();
        skillList = new SkillList();
        resultApllicantList = new ApplicantList();

        for (Class clazz : instance.findAllClassesUsingClassLoader(
                "fr.univ_lyon1.info.m1.cv_search.model.strategies")) {
            try {
                Object object = clazz.getConstructor().newInstance();
                listAvailableStrategies.add((Strategy) object);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        listDisplayedStrategies = listAvailableStrategies;
    }
 
    /**
     * searchButtonHandler va trier les applicants en fonction de la strategy choisi.
     * @param view la Jfx view.
     */
    public void searchButtonHandler(JfxView view) {
        ComboBox<SkillStrategy> cbStrategy = view.getSkillStrategyComboBox();
        SkillStrategy s = cbStrategy.getValue();
        if (s != null) {
            listAppliedStrategies.add(s);
        }

        resultApllicantList.clear();

        for (Applicant a : listApplicants) {
            boolean selected = true;

            for (int sI = 0; sI < listAppliedStrategies.size(); sI++) {
                Strategy strat = listAppliedStrategies.get(sI);
                if (!strat.filter(a)) {
                    selected = false;
                    break;
                }
            }

            if (selected) {
                resultApllicantList.add(a);
            }     
        }

        if (s != null) {
            listAppliedStrategies.remove(s);
        }
    }

    /**
     * ajoute la nouvelle skill dans la skillList.
     * @param textField le champ de text de la view.
     */
    public void addSkillButtonHandler(TextField textField) {
        String text = textField.getText().strip();
        if (text.equals("")) {
            return; // Do nothing
        }
        skillList.add(text);
        textField.setText("");
        textField.requestFocus();
    }

    /**
    * Update la ComboBox de SkillStrategy.
    * @param cb ComboBoc de SkillSTrategy.
    */
    public void updateSkillStrategyComboBox(ComboBox<SkillStrategy> cb) {
        cb.getItems().addAll(new SkillStrategy(StrategyTypes.SUPP, 0, skillList),
                             new SkillStrategy(StrategyTypes.SUPP, 50, skillList),
                             new SkillStrategy(StrategyTypes.SUPP, 60, skillList),
                             new SkillStrategy(StrategyTypes.AVG, 40, skillList));
        cb.getSelectionModel().selectFirst();
    }

    /**
     * Met a jour l'affichage de la skillBox en fonction de la skillList.
     * @param searchSkillsBox la HBox qui va etre mis a jour.
     */
    public void updateSkillBox(HBox searchSkillsBox) {
        searchSkillsBox.getChildren().clear();
        for (int i = 0; i < skillList.size(); i++) {
            final HBox box = new HBox();
            final Label labelContact = new Label(skillList.get(i) + "  ");
            final Button b = new Button("x");
            b.setOnMouseClicked(event -> {
                searchSkillsBox.getChildren().remove(box);
                skillList.remove(labelContact.getText().strip());
            });
            box.setStyle("-fx-padding: 2;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 1;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: black;");
            box.setAlignment(Pos.BASELINE_CENTER);
            box.getChildren().addAll(labelContact, b);
            searchSkillsBox.getChildren().add(box);
        }
    }

    /**
     * Met a jour l'affichage des resultat en fonction des applicants repondant a la bonne strategy.
     * @param resultBox la VBox qui est mis a jour.
     */
    public void updateResultBox(VBox resultBox) {
        TableView<Applicant> table = (TableView<Applicant>) resultBox.getChildren().get(0);
        table.getItems().clear();
        for (Applicant a : resultApllicantList) {
            resultApplicant.add(a);
        }   
        table.setItems(resultApplicant);
    }

    public void updateDisplayedStrategyView(ListView<Strategy> lV) {
        List<Strategy> strategyList = new ArrayList<>();

        for (int i = 0; i < listDisplayedStrategies.size(); i++) {
            strategyList.add(listDisplayedStrategies.get(i));
        }
        
        lV.getItems().clear();
        lV.getItems().addAll(strategyList);
    }

    public void updateSelectedStrategyView(ListView<Strategy> lV) {
        List<Strategy> strategyList = new ArrayList<>();

        for (int i = 0; i < listAppliedStrategies.size(); i++) {
            Strategy s = listAppliedStrategies.get(i);
            if (!(s instanceof SkillStrategy)) {
                strategyList.add(s);
            }
        }
        
        lV.getItems().clear();
        lV.getItems().addAll(strategyList);
    }

    public void selectStrategies(ListView<Strategy> list) {
        ArrayList<Strategy> list3 = new ArrayList<Strategy>();
        for (Strategy s : list.getSelectionModel().getSelectedItems()) {
            try {
                Strategy nS = s.getClass().getConstructor().newInstance();
                listAppliedStrategies.add(nS);
                try {
                    Class viewC = Class.forName(s.getClass().getName()
                        .replace("model", "view") + "View");
                    Object view = viewC.getConstructor(
                        Stage.class, Integer.class, Integer.class, Strategy.class)
                        .newInstance(new Stage(), 400, 400, nS);
                } catch (Exception e) {
                    //System.out.println(e);
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
        }
    }

    public void removeStrategies(ListView<Strategy> list2) {
        List<Strategy> list3 = list2.getSelectionModel().getSelectedItems();
        listAppliedStrategies.removeAll(list3);
    }

    public ApplicantList getResultApllicantList() {
        return resultApllicantList;
    }

    public class AccessingAllClassesInPackage {
        /**
         * Desciption.
         * @param packageName Desciption.
         * @return Desciption.
         */
        public Set<Class> findAllClassesUsingClassLoader(String packageName) {
            InputStream stream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            Set<Class> s =  reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
            s.remove(null);
            return s;
        }
     
        private Class getClass(String className, String packageName) {
            try {
                String cN = className.substring(0, className.lastIndexOf('.'));
                if (cN.contains("$") || cN.equals("Strategy") || cN.equals("SkillStrategy")) {
                    return null;
                }
                
                return Class.forName(packageName + "."
                  + className.substring(0, className.lastIndexOf('.')));
            } catch (ClassNotFoundException e) {
                // handle the exception
            }
            return null;
        }
    }
}
