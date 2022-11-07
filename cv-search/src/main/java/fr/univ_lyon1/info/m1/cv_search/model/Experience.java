package fr.univ_lyon1.info.m1.cv_search.model;


import java.util.Map;

public class Experience {

    
    private Map<String, Map<String, Object>> jobs;
    private int nbJobs;

    public Experience(Map<String, Map<String, Object>> experiences) {
        this.jobs = experiences;
        this.nbJobs = experiences.size();
    }

    public Map<String, Map<String, Object>> getJobs() {
        return jobs;
    }

    public int getNbJobs() {
        return nbJobs;
    }



}
