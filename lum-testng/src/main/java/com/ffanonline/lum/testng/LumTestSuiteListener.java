package com.ffanonline.lum.testng;

import org.testng.ISuite;
import org.testng.ISuiteListener;

/**
 * Created by fanfei on 11/12/2018.
 */
public class LumTestSuiteListener implements ISuiteListener {

    /**
     * This method is invoked before the SuiteRunner starts.
     *
     * @param suite
     */
    @Override
    public void onStart(ISuite suite) {
        System.out.println("\ntest suite onStart.");
        System.out.println(suite.getName());
        System.out.println(suite.getHost());
        System.out.println(suite.getAllMethods().toString());
    }

    /**
     * This method is invoked after the SuiteRunner has run all
     * the test suites.
     *
     * @param suite
     */
    @Override
    public void onFinish(ISuite suite) {
        System.out.println("\ntest suite onFinish.");
        System.out.println(suite.getName());
        System.out.println(suite.getHost());
        System.out.println(suite.getResults().toString());

    }
}
