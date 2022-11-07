package fr.univ_lyon1.info.m1.cv_search.model; 
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import fr.univ_lyon1.info.m1.cv_search.model.strategies.Strategy;

public class StrategyList extends Observable {

    private List<Strategy> strategyList = new ArrayList<>();
    private String message = "";

    public StrategyList() {
    }

    public StrategyList(String m) {
        message = m;
    }

    /**
     * this method will add a string in the list of skills and notify views that he changed.
     * @param s The String added in the skillList.
     */
    public void add(Strategy s) {
        strategyList.add(s);
        s.setList(this);
        change();
        notifyObservers(null);
    }

    /**
     * this method will aremove a string from the list of skills and notify views that he changed.
     * @param s The String removed from skillList.
     */
    public void remove(Strategy s) {
        strategyList.remove(s);
        change();
        notifyObservers(null);
    }

    public void removeAll(List<Strategy> s) {
        strategyList.removeAll(s);
        change();
        notifyObservers(null);
    }

    /**
     * this method will clear the list of skills.
     */
    public void clear() {
        strategyList.clear();
        change();
        notifyObservers(null);
    }

    /**
     * this method return the size of the list of skills.
     * @return a size(int).
     */
    public int size() {
        return strategyList.size();
    }

    @Override
    public void notifyObservers(Object o) {
        o = new HashMap<String, Object>();
        ((HashMap<String, Object>) o).put("action", message);
        super.notifyObservers(o);
    }

    /**
     * this method will return the i element (that is a string) of the list of skills.
     * @param i the i element.
     * @return the i string.
     */
    public Strategy get(int i) {
        return strategyList.get(i);
    }
}
