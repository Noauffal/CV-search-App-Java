package fr.univ_lyon1.info.m1.cv_search.model.strategies;

import java.util.Map;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.Observable;

public class SexStrategy extends Strategy {
    private String sex = null;

    public void updateStrategy(Object o) {
        Map<String, Object> params = (Map<String, Object>) o;
        sex = (String) params.get("sex");
        super.updateStrategy(o);
    }
    
    public boolean filter(Object o) {
        if (sex == null) {
            return true;
        }
        Applicant a = (Applicant) o;
        if (a.getGender().equals(sex)) {
            return true;
        }
        return false;
    }

    public String getTitle() {
        return "Sex strategy";
    }

    public String toString() {
        if (sex == null) {
            return "Sex still not specified";
        }
        return "Sex is " + sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    
    /**
     * Override notify.
     */
    @Override
    public void notify(Observable obs, Object o) {
        updateStrategy(o);
    }
}
