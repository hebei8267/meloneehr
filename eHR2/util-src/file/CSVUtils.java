package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import static constant.UtilConstant.CHARSET_NAME;
import static constant.UtilConstant.DELIM;

public class CSVUtils {
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
