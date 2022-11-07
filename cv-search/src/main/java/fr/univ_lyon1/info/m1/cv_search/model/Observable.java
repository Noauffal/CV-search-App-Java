package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.ArrayList;

/**
 * Observable.
 */
public class Observable {
    private boolean changed = false;
    private ArrayList<Observer> observerList = new ArrayList<Observer>();

    /**
     * change de value of changed at true.
     */
    public void change() {
        changed = true;
    }

    /**
     * this method return true if an element has changed.
     * @return boolean.
     */
    public boolean hasChanged() {
        return changed;
    }

    /**
     * Add an abserver in the list of observer. 
     * @param obs an observer.
     */
    public void addObserver(Observer obs) {
        observerList.add(obs);
    }

    /**
     * delete an observer from the list.
     * @param obs an observer.
     */
    public void delObserver(Observer obs) {
        observerList.remove(obs);
    }
    
    /**
     * Fonction qui permet de notifier les observers.
     * @param o objet qui notifie.
     */
    public void notifyObservers(Object o) {
        if (hasChanged()) {
            for (Observer observer : observerList) {
                observer.notify(this, o);
            }
            changed = false;
        }
    }
}
