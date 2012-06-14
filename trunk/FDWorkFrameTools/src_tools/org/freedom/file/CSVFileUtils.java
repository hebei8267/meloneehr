/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static org.freedom.constant.GeneratorUtilConstant.CHARSET_NAME;
import static org.freedom.constant.GeneratorUtilConstant.DELIM;

/**
 * CSV文件操作
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class CSVFileUtils {
    public static List<List<String>> readCSVFile(String filePath) throws IOException {

        File csvFile = new File(filePath); // CSVデータファイル

        InputStreamReader read = new InputStreamReader(new FileInputStream(csvFile), CHARSET_NAME);

        BufferedReader br = new BufferedReader(read);
        List<List<String>> csvFileContent = new ArrayList<List<String>>();

        // 最終行まで読み込む
        while (br.ready()) {
            String line = br.readLine();
            List<String> lineContent = new ArrayList<String>();

            // 1行をデータの要素に分割
            StringTokenizer st = new StringTokenizer(line, DELIM);
            while (st.hasMoreTokens()) {
                // 1行の各要素をタブ区切りで表示
                lineContent.add(st.nextToken());
            }

            csvFileContent.add(lineContent);
        }
        br.close();

        return csvFileContent;

    }
}