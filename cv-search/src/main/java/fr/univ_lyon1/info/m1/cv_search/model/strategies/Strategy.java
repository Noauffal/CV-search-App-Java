package fr.univ_lyon1.info.m1.cv_search.model.strategies;

import fr.univ_lyon1.info.m1.cv_search.model.Observer;
import fr.univ_lyon1.info.m1.cv_search.model.StrategyList;

public abstract class Strategy extends Observer {
    private StrategyList strategyList;

    public void updateStrategy(Object o) {
        if (strategyList != null) {
            strategyList.change();
            strategyList.notifyObservers(null);
        }
    }

    public abstract boolean filter(Object o);
    
    public abstract String getTitle();

    public void setList(StrategyList sL) {
        strategyList = sL;
    }
}
