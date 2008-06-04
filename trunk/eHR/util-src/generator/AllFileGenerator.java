package generator;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import file.ExcelUtils;

public class AllFileGenerator {
    public final static String DATA_DICTIONARY_FILE_PATH = "";

    public static void main(String[] args) throws IOException {
        AllFileGenerator fg = new AllFileGenerator();

        Map<String, String> dataMap = fg.getDataDictionaryContent(DATA_DICTIONARY_FILE_PATH);
    }

    /**
     * 取得数据字典内容
     * 
     * @param filePaht 数据字典文件路径
     * @return
     */
    private Map<String, String> getDataDictionaryContent(String filePaht) {
        File file = new File(filePaht);

        List<List<List<String>>> workContent = ExcelUtils.readExcelFile(file);

        Map<String, String> dataMap = new HashMap<String, String>();

        for (List<List<String>> sheetContent : workContent) {
            for (List<String> rowContent : sheetContent) {

                String key = "";
                String value = "";
                for (int i = 0; i < rowContent.size(); i++) {
                    String cellValue = rowContent.get(i);

                    if (i == 0) {
                        key = cellValue;
                    } else if (i == 1) {
                        value = cellValue;
                    }
                }

                dataMap.put(key, value);
            }
        }

        return dataMap;
    }
}
