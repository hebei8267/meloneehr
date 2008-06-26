package generator;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import entity.FileInfo;

/**
 * @author kaka
 * 
 * 文件生成
 */
public interface IFileGenerator {
    /**
     * 代码文件生成
     * 
     * @param fileInfoList 文件信息类容列表
     * @param dataMap 数据字典Map
     * @throws IOException
     */
    public void createCodeFile(List<FileInfo> fileInfoList, Map<String, String> dataMap) throws IOException;
}
