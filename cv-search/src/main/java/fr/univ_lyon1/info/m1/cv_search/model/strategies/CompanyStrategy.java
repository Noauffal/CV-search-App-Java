package fr.univ_lyon1.info.m1.cv_search.model.strategies;

import java.util.Map;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.Observable;

public class CompanyStrategy extends Strategy {
    private String companyName = null;

    public void updateStrategy(Object o) {
        Map<String, Object> params = (Map<String, Object>) o;
        companyName = (String) params.get("companyName");
        super.updateStrategy(o);
    }
    
    public boolean filter(Object o) {
        if (companyName == null) {
            return true;
        }
        Applicant a = (Applicant) o;
        if (a.getExp().getJobs().containsKey(companyName)) {
            return true;
        }
        return false;
    }

    public String getTitle() {
        return "Company strategy";
    }

    public String toString() {
        if (companyName == null) {
            return "Company name still not specified";
        }
        return "Has worked in " + companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    /**
     * Override notify.
     */
    @Override
    public void notify(Observable obs, Object o) {
        updateStrategy(o);
    }
}
