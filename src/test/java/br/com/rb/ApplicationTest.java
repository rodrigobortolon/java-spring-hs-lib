package br.com.rb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ApplicationTest {

    @Test
    public void test_ApplicationStarts() {
      Application.main(new String[] {});
    }
}