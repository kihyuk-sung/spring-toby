package springboot.learningtest.junit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/junit.xml")
class JUnitTest {
    @Autowired
    ApplicationContext context;

    static Set<JUnitTest> testObjects = new HashSet<>();
    static ApplicationContext contextObject = null;

    @Test
    public void test1() {
        test();
    }


    @Test
    public void test2() {
        test();
    }

    @Test
    public void test3() {
        test();
    }

    private void test() {
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);
        assertThat(contextObject == null || contextObject == this.context).isTrue();
        contextObject = this.context;
    }
}
