package fr.univ_lyon1.info.m1.cv_search;

import java.io.File;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantListBuilder;

public class ApplicantTest {

    /**
     * Test Applicant et experience.
     */
    @Test
    public void testReadApplicant() {
        // Given
        ApplicantBuilder builder = new ApplicantBuilder("ApplicantTestFiles/test1.yaml");

        // When
        Applicant a = builder.build();

        // Then
        assertThat(50, is(a.getSkill("Hootsuite")));
        assertThat("Noauffal", is(a.getName()));
        assertThat("Mohamed", is(a.getLastName()));
        assertThat("homme", is(a.getGender()));
        assertThat("Mohamed@mail.com", is(a.getEmail()));
        assertThat(61.25, is(a.getAverage()));

        //Test Experience
        assertThat(2005, is((Integer) a.getExp().getJobs().get("Google").get("start")));
        assertThat(2010, is((Integer) a.getExp().getJobs().get("Google").get("end")));
        assertThat("NoSQL", is((String)((List<String>)a.getExp().getJobs().get("Google").get("keywords")).get(1)));
        assertThat(5, is(a.getExp().getNbJobs()));
    }

    @Test
    public void testReadManyApplicant() {
        // Given
        ApplicantListBuilder builder = new ApplicantListBuilder(new File("ApplicantTestFiles/"));

        // When
        ApplicantList list = builder.build();

        // Then
        boolean nonoFound = false;
        boolean testFound = false;
        for (Applicant a : list) {
            if (a.getName().equals("Noauffal")) {
                assertThat(50, is(a.getSkill("Hootsuite")));
                assertThat("Noauffal", is(a.getName()));
                assertThat("Mohamed", is(a.getLastName()));
                assertThat("Mohamed@mail.com", is(a.getEmail()));
                assertThat("homme", is(a.getGender()));
                assertThat(61.25, is(a.getAverage()));
                assertThat(2005, is((Integer) a.getExp().getJobs().get("Google").get("start")));
                assertThat(2010, is((Integer) a.getExp().getJobs().get("Google").get("end")));
                assertThat("NoSQL", is((String)((List<String>)a.getExp().getJobs().get("Google").get("keywords")).get(1)));
                assertThat(5, is(a.getExp().getNbJobs()));
                nonoFound = true;
            }
            if (a.getName().equals("testName")) {
                assertThat(50, is(a.getSkill("Hootsuite")));
                assertThat("testName", is(a.getName()));
                assertThat("testLastName", is(a.getLastName()));
                assertThat("Test@mail.com", is(a.getEmail()));
                assertThat("homme", is(a.getGender()));
                assertThat(61.25, is(a.getAverage()));
                assertThat(2000, is((Integer) a.getExp().getJobs().get("Google").get("start")));
                assertThat(2018, is((Integer) a.getExp().getJobs().get("Google").get("end")));
                assertThat("c++", is((String)((List<String>)a.getExp().getJobs().get("Google").get("keywords")).get(2)));
                assertThat(1, is(a.getExp().getNbJobs()));
                testFound = true;
            }
        }
        assertThat(nonoFound, is(true)); 
        assertThat(testFound, is(true));  
    }
}
