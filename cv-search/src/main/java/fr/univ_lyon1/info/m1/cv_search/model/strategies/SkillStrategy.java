package fr.univ_lyon1.info.m1.cv_search.model.strategies;

import java.util.Map;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.Observable;
import fr.univ_lyon1.info.m1.cv_search.model.SkillList;

public class SkillStrategy extends Strategy {
    public enum StrategyTypes {
        SUPP,
        INFOREQ,
        AVG
    }

    private StrategyTypes typeStrategy;
    private float value;
    private SkillList skillList;

    public SkillStrategy() {
        typeStrategy = StrategyTypes.SUPP;
        value = 0;
    }

    /**
     * Constructeur de la strategy.
     * @param st Type de la strategy.
     * @param val Valeur de comparaison.
     * @param skl Liste de skill a comparé.
     */
    public SkillStrategy(StrategyTypes st, float val, SkillList skl) {
        typeStrategy = st;
        value = val;
        skillList = skl;
    }

    /**
     * Update la strategy.
     */
    public void updateStrategy(Object o) {
        Map<String, Object> params = (Map<String, Object>) o;
        typeStrategy = (StrategyTypes) params.get("stType");
        value = (float) params.get("value");
        super.updateStrategy(o);
    }

    /**
     * Override notify.
     */
    @Override
    public void notify(Observable obs, Object o) {
        updateStrategy(o);
    }
    
    /**
     * Application de la strategy sur l'objet.
     */
    public boolean filter(Object o) {
        Applicant a = (Applicant) o;
        switch (typeStrategy) {
            case SUPP:
                for (int i = 0; i < skillList.size(); i++) {
                    if (a.getSkill(skillList.get(i)) <= value) {
                        return false;
                    } 
                }
                return true;
                
            case INFOREQ:
                for (int i = 0; i < skillList.size(); i++) {
                    if (a.getSkill(skillList.get(i)) > value) {
                        return false;
                    } 
                }
                return true;
            
            case AVG:
                float avg = 0;

                for (int i = 0; i < skillList.size(); i++) {
                    avg += a.getSkill(skillList.get(i));
                }

                avg /= skillList.size();
                return avg >= value;
            
            default:
                return false;
        }
    }

    public String getTitle() {
        return "Skill strategy";
    }

    /**
     * method toString basique.
     */
    public String toString() {
        if (this.typeStrategy == StrategyTypes.SUPP || this.typeStrategy == StrategyTypes.INFOREQ) {
            return "ALL " + this.typeStrategy + " à " + this.value; 
        } else {
            return "" + this.typeStrategy + " supérieur à " + this.value;
        }
    }

    
}
