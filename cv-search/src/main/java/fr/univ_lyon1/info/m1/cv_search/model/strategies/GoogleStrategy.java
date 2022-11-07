package fr.univ_lyon1.info.m1.cv_search.model.strategies;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;

public class GoogleStrategy extends Strategy {
    
    public boolean filter(Object o) {
        Applicant a = (Applicant) o;
        if (a.getExp().getJobs().containsKey("Google")) {
            return true;
        }
        return false;
    }

    public String getTitle() {
        return "Google strategy";
    }

    public String toString() {
        return "has worked in Google";
    }

    
}
