package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.HashMap;
import java.util.Map;

public class Applicant {
    private Map<String, Integer> skills = new HashMap<>();
    private String name;
    private String lastName;
    private String gender;
    private double average;
    private String email;
    private Experience exp;

    public int getSkill(String string) {
        return skills.getOrDefault(string, 0);
    }

    public void setSkill(String string, int value) {
        skills.put(string, value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setExperience(Experience exp) {
        this.exp = exp;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public Experience getExp() {
        return exp;
    }
}
