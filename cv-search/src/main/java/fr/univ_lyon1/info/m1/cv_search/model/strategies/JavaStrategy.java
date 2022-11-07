package fr.univ_lyon1.info.m1.cv_search.model.strategies;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;

public class JavaStrategy extends Strategy {
    
    public boolean filter(Object o) {
        Applicant a = (Applicant) o;
        if (a.getSkill("java") != 0) {
            return true;
        }
        return false;
    }

    public String getTitle() {
        return "Java strategy";
    }

    public String toString() {
        return "Can code in Java";
    }

    
}
