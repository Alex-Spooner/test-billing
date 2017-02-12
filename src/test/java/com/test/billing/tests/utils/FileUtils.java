package com.test.billing.tests.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gosh on 12.02.2017.
 * Nothing special
 */
public class FileUtils {

    public static List<Object[]> getStringsFromFile(String fileSource) {

        List<Object[]> list = new ArrayList<Object[]>();
        Resource resource = new ClassPathResource(fileSource);
        try {
            File file = resource.getFile();
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF8"));
            String line = bufferedReader.readLine();
            while (line != null) {
                list.add(new Object[]{line});
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
