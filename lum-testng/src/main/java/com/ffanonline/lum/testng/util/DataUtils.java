package com.ffanonline.lum.testng.util;


import com.alibaba.fastjson.JSON;
import com.ffanonline.lum.testng.TestCaseProperties;
import com.ffanonline.lum.testng.TestResultContainer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

/**
 * Created by fanfei on 11/09/2018.
 */
public class DataUtils {

    public static void wirteToDisk(Map<String, TestCaseProperties> container, String path) {

        System.out.println("print JSON: ");
        String result = JSON.toJSONString(container);
        System.out.println(result);
        System.out.println("end JSON.");

        try {
            File file = new File(path);
            file.getParentFile().mkdirs();
            Files.write(Paths.get(path), result.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
