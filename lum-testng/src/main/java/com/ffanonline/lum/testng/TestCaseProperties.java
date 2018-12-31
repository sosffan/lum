package com.ffanonline.lum.testng;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Map;

/**
 * Created by fanfei on 11/09/2018.
 */
public class TestCaseProperties implements Serializable {


    public String testCaseName;
    public String testClassName;
    public long startMils;
    public long endMils;
    public Map<String, String> annotationMap = null;
    public Status status;
    public Throwable testCaseResult = null;
    public String attachmentName;

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public String getTestClassName() {
        return testClassName;
    }

    public void setTestClassName(String testClassName) {
        this.testClassName = testClassName;
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

    public Map<String, String> getAnnotationMap() {
        return annotationMap;
    }

    public void setAnnotationMap(Map<String, String> annotationMap) {
        this.annotationMap = annotationMap;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Throwable getTestCaseResult() {
        return testCaseResult;
    }

    public String getTestCaseStackTrace() {
        final StringWriter stringWriter = new StringWriter();
        if (testCaseResult == null) {
            return "";
        }
        testCaseResult.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public void setTestCaseResult(Throwable testCaseResult) {
        this.testCaseResult = testCaseResult;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
}
