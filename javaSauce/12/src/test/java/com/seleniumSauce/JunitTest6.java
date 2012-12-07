package com.seleniumSauce;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: fjodorekstrom
 * Date: 12/7/12
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(value = Parameterized.class)
public class JunitTest6 {

    private int number;

    public JunitTest6(int number){
        this.number = number;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        Object[][] data = new Object[][] { {1}, {2}, {3}, {4} };
        return Arrays.asList(data);
    }

    @Test
    public void pushTest(){
        System.out.println("Parameterized Number is: " + number);
        Assert.assertEquals(number, number);
    }

}
