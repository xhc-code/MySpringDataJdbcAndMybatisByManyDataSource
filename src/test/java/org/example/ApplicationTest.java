package org.example;

import org.junit.Test;
import org.springframework.jdbc.support.GeneratedKeyHolder;

/**
 * Unit test for simple App.
 */
public class ApplicationTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        Number key = new GeneratedKeyHolder().getKey();
        System.out.println(key);
    }

    @Test
    public void test1(){


        System.out.println(A.AA == A.AA);
        System.out.println(A.AA == A.AA);
    }



}

enum A{
    AA,BB
}
