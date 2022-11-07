package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.ArrayList;
import java.util.List;

public class SkillList extends Observable {

    private List<String> skillList = new ArrayList<>();

    /**
     * this method will add a string in the list of skills and notify views that he changed.
     * @param s The String added in the skillList.
     */
    public void add(String s) {
        skillList.add(s);
        change();
        notifyObservers(null);
    }

    /**
     * this method will aremove a string from the list of skills and notify views that he changed.
     * @param s The String removed from skillList.
     */
    public void remove(String s) {
        skillList.remove(s);
        change();
        notifyObservers(null);
    }

    /**
     * this method will clear the list of skills.
     */
    public void clear() {
        skillList.clear();
        change();
        notifyObservers(null);
    }

    /**
     * this method return the size of the list of skills.
     * @return a size(int).
     */
    public int size() {
        return skillList.size();
    }

    /**
     * this method will return the i element (that is a string) of the list of skills.
     * @param i the i element.
     * @return the i string.
     */
    public String get(int i) {
        return skillList.get(i);
    }

    


}
