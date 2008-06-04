package generator;

import java.io.IOException;
import java.util.List;

import domain.FileInfo;

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
     * @throws IOException
     */
    public void createCodeFile(List<FileInfo> fileInfoList) throws IOException;
}
