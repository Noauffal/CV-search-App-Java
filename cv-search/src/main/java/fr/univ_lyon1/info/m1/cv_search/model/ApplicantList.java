package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ApplicantList extends Observable implements Iterable<Applicant> {
    

    private List<Applicant> list = new ArrayList<>();

    /**
     * this method add a new appliant to the applicant list.
     * @param a an applicant
     */
    public void add(Applicant a) {
        list.add(a);
        change();
        notifyObservers(null);
    }
    
    /**
     * this method return the size of the list.
     * @return size (int)
     */
    public int size() {
        return list.size();
    }

    /**
     * return an iterator of Applicant list.
     */
    @Override
    public Iterator<Applicant> iterator() {
        return list.iterator();
    }

    /** Clear the list of applicants. */
    public void clear() {
        list.clear();
        change();
        notifyObservers(null);
    }

    /** Sets the content of the applicant list. */
    public void setList(ApplicantList list) {
        this.list = list.list;
    }

    public List<Applicant> getList() {
        return list;
    }
}
