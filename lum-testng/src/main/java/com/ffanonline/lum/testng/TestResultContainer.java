package com.ffanonline.lum.testng;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by fanfei on 11/12/2018.
 */
public class TestResultContainer implements Serializable {

    public final Map<String, TestCaseProperties> resultContainer = new ConcurrentHashMap<>();

    public final List<TestCaseProperties> resultList = Collections.synchronizedList(new ArrayList());

    public void addTestCase(String id, TestCaseProperties testCaseProperties) {

        resultContainer.put(id, testCaseProperties);
    }

    public TestCaseProperties getTestCase(String id) {
        return resultContainer.get(id);
    }


    public void addTestCase(TestCaseProperties properties) {
        resultList.add(properties);
    }


    public void setTestResult(String id, TestCaseProperties properties) {
        resultContainer.put(id, properties);
    }

    public Map<String, TestCaseProperties> getResultContainer() {
        return resultContainer;
    }


}
