package com.main;

import com.scripts.*;
import org.testng.TestNG;

public class TestRunner{
    static TestNG testNG;
    public static void main(String[] args) throws NoClassDefFoundError {
        testNG = new TestNG();
        testNG.setTestClasses(new Class[] {
                GetCollections.class,
                PostCollections.class,
                NegativeCases.class});
        testNG.run();
    }
}