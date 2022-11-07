package fr.univ_lyon1.info.m1.cv_search;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import fr.univ_lyon1.info.m1.cv_search.model.SkillList;


public class SkillListTest {
    
    @Test
    public void testSkillList(){
        SkillList list = new SkillList();
        list.add("c++");
        list.add("php");
        list.add("python");
        assertThat("c++", is(list.get(0)));
        assertThat("php", is(list.get(1)));
        assertThat("python", is(list.get(2)));
        list.remove("php");
        assertThat(2, is(list.size()));
        list.clear();
        assertThat(0, is(list.size()));
    }

}
