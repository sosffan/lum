package com.ffanonline.lum.testng;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fanfei on 11/12/2018.
 */
public class  TestSuiteProperties implements Serializable {

    List<TestCaseProperties> testCaseProperties = null;

    public String testSuiteName;
    public long startMils;
    public long endMils;

    public List<TestCaseProperties> getTestCaseProperties() {
        return testCaseProperties;
    }

    public void setTestCaseProperties(List<TestCaseProperties> testCaseProperties) {
        this.testCaseProperties = testCaseProperties;
    }

    public String getTestSuiteName() {
        return testSuiteName;
    }

    public void setTestSuiteName(String testSuiteName) {
        this.testSuiteName = testSuiteName;
    }

    public long getStartMils() {
        return startMils;
    }

    public void setStartMils(long startMils) {
        this.startMils = startMils;
    }

    public long getEndMils() {
        return endMils;
    }

    public void setEndMils(long endMils) {
        this.endMils = endMils;
    }
}
