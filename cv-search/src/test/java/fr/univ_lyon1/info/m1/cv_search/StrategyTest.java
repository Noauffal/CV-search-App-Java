package fr.univ_lyon1.info.m1.cv_search;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.SkillList;
import fr.univ_lyon1.info.m1.cv_search.model.strategies.CompanyStrategy;
import fr.univ_lyon1.info.m1.cv_search.model.strategies.GoogleStrategy;
import fr.univ_lyon1.info.m1.cv_search.model.strategies.JavaStrategy;
import fr.univ_lyon1.info.m1.cv_search.model.strategies.SexStrategy;
import fr.univ_lyon1.info.m1.cv_search.model.strategies.SkillStrategy;
import fr.univ_lyon1.info.m1.cv_search.model.strategies.YearsExpStrategy;
import fr.univ_lyon1.info.m1.cv_search.model.strategies.SkillStrategy.StrategyTypes;

public class StrategyTest {

    @Test
    public void testCompanyStrategy() {
        ApplicantBuilder builder = new ApplicantBuilder("ApplicantTestFiles/test1.yaml");
        Applicant a = builder.build();
        CompanyStrategy strat = new CompanyStrategy();
        strat.setCompanyName("Google");
        assertThat(true, is(strat.filter(a)));
        strat.setCompanyName("Air France");
        assertThat(false, is(strat.filter(a)));
    }

    @Test
    public void testSkillStrategy() {
        ApplicantBuilder builder = new ApplicantBuilder("ApplicantTestFiles/test1.yaml");
        Applicant a = builder.build();
        SkillList skillList = new SkillList();
        skillList.add("Spring");
        skillList.add("Symfony");
        SkillStrategy strat = new SkillStrategy(StrategyTypes.SUPP, 60, skillList);
        assertThat(false, is(strat.filter(a)));
        skillList.remove("Symfony");
        assertThat(true, is(strat.filter(a)));
    }

    @Test
    public void testGoogletrategy() {
        ApplicantBuilder builder = new ApplicantBuilder("ApplicantTestFiles/test1.yaml");
        GoogleStrategy strat = new GoogleStrategy();
        Applicant a = builder.build();
        assertThat(true, is(strat.filter(a)));
       
    }

    @Test
    public void testJavatrategy() {
        ApplicantBuilder builder = new ApplicantBuilder("ApplicantTestFiles/test1.yaml");
        JavaStrategy strat = new JavaStrategy();
        Applicant a = builder.build();
        assertThat(true, is(strat.filter(a)));
    }

    @Test
    public void testSexStrategy() {
        ApplicantBuilder builder = new ApplicantBuilder("ApplicantTestFiles/test1.yaml");
        SexStrategy strat = new SexStrategy();
        strat.setSex("homme");
        Applicant a = builder.build();
        assertThat(true, is(strat.filter(a)));
        strat.setSex("femme");
        assertThat(false, is(strat.filter(a)));
    }

    @Test
    public void testYearsExpStrategy() {
        ApplicantBuilder builder = new ApplicantBuilder("ApplicantTestFiles/test1.yaml");
        YearsExpStrategy strat = new YearsExpStrategy();
        strat.setNbY("10");
        Applicant a = builder.build();
        assertThat(true, is(strat.filter(a)));
        strat.setNbY("75");
        assertThat(false, is(strat.filter(a)));
    }





    
}
