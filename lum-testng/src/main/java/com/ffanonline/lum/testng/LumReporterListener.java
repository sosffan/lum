package com.ffanonline.lum.testng;

import com.alibaba.fastjson.serializer.SerializerFeature;
import org.testng.*;
import org.testng.xml.XmlSuite;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by fanfei on 11/06/2018.
 */

@Deprecated
public class LumReporterListener implements IReporter {

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String output) {
        System.out.println("---- in LumReporterListener ----");


        List<ITestResult> resultList = new ArrayList<>();

        for (ISuite suite: suites) {
            Collection<ISuiteResult> values = suite.getResults().values();

            System.out.println(suite.getResults().keySet().toArray()[0]);
            for (ISuiteResult suiteResult: values) {
                System.out.println("------");
                ITestContext context = suiteResult.getTestContext();
                IResultMap passedTest = context.getPassedTests();
                IResultMap failedTest = context.getFailedTests();
                IResultMap failedConfig = context.getFailedConfigurations();

            }

        }
    }
}
