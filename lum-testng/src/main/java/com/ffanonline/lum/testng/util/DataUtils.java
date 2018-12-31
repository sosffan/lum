package com.ffanonline.lum.testng.util;


import com.alibaba.fastjson.JSON;
import com.ffanonline.lum.testng.TestCaseProperties;
import com.ffanonline.lum.testng.TestResultContainer;

import java.util.Map;

/**
 * Created by fanfei on 11/09/2018.
 */
public class DataUtils {

    public static void wirteToDisk(Map<String, TestCaseProperties> container, String direction) {

        System.out.println("print JSON: ");
        String result = JSON.toJSONString(container);
        System.out.println(result);
        System.out.println("end JSON.");
    }
}
