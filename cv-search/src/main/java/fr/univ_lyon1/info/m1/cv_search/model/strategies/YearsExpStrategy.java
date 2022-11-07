package fr.univ_lyon1.info.m1.cv_search.model.strategies;


import java.util.Map;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.Observable;



public class YearsExpStrategy extends Strategy {
    private String nbY = null;

    public void updateStrategy(Object o) {
        Map<String, Object> params = (Map<String, Object>) o;
        nbY = (String) params.get("nbY");
        super.updateStrategy(o);
    }
    
    public boolean filter(Object o) {
        int nbYExp;
        if (nbY == null) {
            return true;
        }
        try {
            nbYExp = Integer.parseInt(nbY);
        } catch (Exception e) {
            return true;
        }
        Applicant a = (Applicant) o;

        int nbYOfA = 0;
        for (String key : a.getExp().getJobs().keySet()) {
            nbYOfA += ((Integer) a.getExp().getJobs().get(key).get("end")) 
                - ((Integer) a.getExp().getJobs().get(key).get("start"));
        }

        if (nbYOfA >= nbYExp) {
            return true;
        }
        return false;
    }

    public String getTitle() {
        return "Minimum years of experience strategy";
    }

    public String toString() {
        if (nbY == null) {
            return "Number of years still not specified";
        }
        try {
            Integer.parseInt(nbY);
        } catch (Exception e) {
            return "Number of years not in right Integer format";
        }
        return "Has worked for at least " + nbY + " years.";
    }

    public void setNbY(String nbY) {
        this.nbY = nbY;
    }

    
    /**
     * Override notify.
     */
    @Override
    public void notify(Observable obs, Object o) {
        updateStrategy(o);
    } 
}
