package com.ffanonline.lum.testng;


import com.ffanonline.lum.testng.util.DataUtils;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;
import org.testng.internal.ConstructorOrMethod;


/**
 * Created by fanfei on 11/07/2018.
 */
public class LumTestCaseListener implements ITestListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(LumTestCaseListener.class);

    TestResultContainer testResultContainer = new TestResultContainer();

    private ThreadLocal<ResultTracker> resultTracker = new InheritableThreadLocal<ResultTracker>() {
        @Override
        protected ResultTracker initialValue() {
            return new ResultTracker();
        }
    };


    /**
     * Invoked each time before a test will be invoked.
     * The <code>ITestResult</code> is only partially filled with the references to
     * class, method, start millis and status.
     *
     * @param result the partially filled <code>ITestResult</code>
     * @see ITestResult#STARTED
     */
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("\ntest listener onTestStart");

        ResultTracker tracker = resultTracker.get();

        ITestNGMethod method = result.getMethod();
        ITestClass testClass = method.getTestClass();
        String testCaseName = method.getMethodName();   // Test Case name
        String className = testClass.getName(); // Class name

        Map<String, String> annotations = getAnnotations(method);
        TestCaseProperties testCaseProperties = new TestCaseProperties();
        testCaseProperties.setTestCaseName(testCaseName);
        testCaseProperties.setTestClassName(className);
        testCaseProperties.setStartMils(System.currentTimeMillis());
        testCaseProperties.setAnnotationMap(annotations);

        if (tracker.isStarted == true) {
            resultTracker.remove();
            tracker = resultTracker.get();
        }
        System.out.println(tracker.testCaseId);
        testResultContainer.addTestCase(tracker.testCaseId, testCaseProperties);

    }

    /**
     * Invoked each time a test succeeds.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("\ntest listener onTestSuccess");
        ResultTracker tracker = resultTracker.get();
        tracker.setStarted(true);
        TestCaseProperties testCase = testResultContainer.getTestCase(tracker.testCaseId);
        testCase.setStatus(Status.PASSED);
        testCase.setEndMils(System.currentTimeMillis());
    }

    /**
     * Invoked each time a test fails.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#FAILURE
     */
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("\ntest listener onTestFailure");
        ResultTracker tracker = resultTracker.get();
        tracker.setStarted(true);

        TestCaseProperties testCase = testResultContainer.getTestCase(tracker.testCaseId);
        testCase.setStatus(Status.FAILED);
        testCase.setTestCaseResult(result.getThrowable());
        testCase.setEndMils(System.currentTimeMillis());
    }

    /**
     * Invoked each time a test is skipped.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SKIP
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("\ntest listener onTestSkipped");
    }

    /**
     * Invoked each time a method fails but has been annotated with
     * successPercentage and this failure still keeps it within the
     * success percentage requested.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("\ntest listener onTestFailedButWithinSuccessPercentage");
    }

    /**
     * Invoked after the test class is instantiated and before
     * any configuration method is called.
     *
     * @param context
     */
    @Override
    public void onStart(ITestContext context) {
        System.out.println("\ntest listener onStart");

    }

    /**
     * Invoked after all the tests have run and all their
     * Configuration methods have been called.
     *
     * @param context
     */
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("\ntest listener onFinish");
        System.out.println("on finish ----------------- test result: ");

        Map<String, TestCaseProperties> a = testResultContainer.getResultContainer();

        System.out.println("size: " + a.size());
        for (TestCaseProperties testCase : a.values()) {
            System.out.println("for each test case: ");

            System.out.println(testCase.getStartMils());
            System.out.println(testCase.getEndMils());
            System.out.println(testCase.getTestCaseName());
            System.out.println(testCase.getTestClassName());
            System.out.println(testCase.getStatus().toString());
            if (testCase.getStatus() != Status.PASSED) {
                System.out.println(testCase.getTestCaseStackTrace());
            }
            System.out.println(testCase.getAnnotationMap().keySet());

        }


        System.out.println("on finish ---------------- end.");

        DataUtils.wirteToDisk(a, "./ffantestResportLocatioin/");

    }

    /**
     * Got all annotations from method and class
     *
     * @param method the method of this test
     * @return a map with annotation name and value
     */
    private Map<String, String> getAnnotations(ITestNGMethod method) {

        ConstructorOrMethod constructorOrMethod = method.getConstructorOrMethod();

        // Store all annotations in the list.
        List<Annotation> annotationList = Stream.of(constructorOrMethod)
                .map(ConstructorOrMethod::getMethod)
                .flatMap(m -> Stream.of(m.getAnnotations()))
                .collect(Collectors.toList());

        List<Annotation> classAnnotationList = Stream.of(constructorOrMethod)
                .map(ConstructorOrMethod::getDeclaringClass)
                .flatMap(m -> Stream.of(m.getAnnotations()))
                .collect(Collectors.toList());

        annotationList.addAll(classAnnotationList); // Data formats: ["@com.packagepath.AnnotationName(value=AnnotationVaule)", ..]

        Map<String, String> annotationMap = new HashMap<>();

        for (Annotation annotation : annotationList) {
            String str = annotation.toString();
            String annotationValue = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
            String annotationName = str.substring(0, str.indexOf("("));

            annotationMap.put(annotationName, annotationValue);

        }

        return annotationMap;
    }


    public static class ResultTracker {
        String testCaseId = null;
        Boolean isStarted = false;

        ResultTracker() {
            testCaseId = UUID.randomUUID().toString();
        }

        public String getTestCaseId() {
            return testCaseId;
        }

        public void setTestCaseId(String testCaseId) {
            this.testCaseId = testCaseId;
        }

        public Boolean getStarted() {
            return isStarted;
        }

        public void setStarted(Boolean started) {
            isStarted = started;
        }
    }

}
