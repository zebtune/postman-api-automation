package com.main;

import com.scripts.NegativeCases;
import org.testng.TestNG;
import com.scripts.ApiGETCollections;
import com.scripts.PostCollections;

public class TestRunner{
    static TestNG testNG;
    public static void main(String[] args) throws NoClassDefFoundError {

        testNG = new TestNG();
        testNG.setTestClasses(new Class[] {
                ApiGETCollections.class,
                PostCollections.class,
                NegativeCases.class});
        testNG.run();
    }
}