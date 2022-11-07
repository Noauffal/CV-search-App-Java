package fr.univ_lyon1.info.m1.cv_search;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.StrategyList;
import fr.univ_lyon1.info.m1.cv_search.model.strategies.GoogleStrategy;
import fr.univ_lyon1.info.m1.cv_search.model.strategies.JavaStrategy;


public class StrategyListTest {
    
    @Test
    public void testStrategyList(){
        StrategyList list = new StrategyList();
        ApplicantBuilder builder = new ApplicantBuilder("ApplicantTestFiles/test1.yaml");
        Applicant a = builder.build();
        list.add(new JavaStrategy());
        list.add(new GoogleStrategy());
        assertThat(2, is(list.size()));
        assertThat(true, is(list.get(0).filter(a)));
        list.clear();
        assertThat(0, is(list.size()));
    }

}
