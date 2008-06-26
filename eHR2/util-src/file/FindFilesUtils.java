package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import entity.FileInfo;

import static constant.UtilConstant.JAVA_FILE;
import static constant.UtilConstant.SEPARATOR;
import static constant.UtilConstant.POINT;
import static constant.UtilConstant.HIDDEN_FILE;
import static constant.UtilConstant.CHARSET_NAME;

/**
 * @author kaka
 * 
 * 得到指定路径下所有文件信息（包含子文件）
 */
public class FindFilesUtils {

    public List<FileInfo> getFileInfoList(String path) {
        List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
        getFileInfoList(path, path, fileInfoList);
        return fileInfoList;
    }

    private void getFileInfoList(String rootPath, String path, List<FileInfo> fileInfoList) {
        try {

            File file = new File(path);
            if (file.isFile()) {// 文件
                if (file.getName().endsWith(JAVA_FILE)) {// java文件

                    FileInfo fileInfo = new FileInfo();

                    fileInfo.setFileName(file.getName().replace(JAVA_FILE, ""));
                    fileInfo.setFilePath(file.getParent().replace(rootPath, "").replace(SEPARATOR, POINT));
                    fileInfo.setFileContent(getFileContent(file));

                    fileInfoList.add(fileInfo);
                }
            } else {// 文件夹
                String[] files = file.list();
                for (String fileName : files) {
                    if (!fileName.startsWith(HIDDEN_FILE)) {// 非隐藏文件夹
                        getFileInfoList(rootPath, path + SEPARATOR + fileName, fileInfoList);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取得文件内容
     * 
     * @return
     * @throws IOException
     */
    private List<String> getFileContent(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), CHARSET_NAME));

        List<String> fileContent = new ArrayList<String>();

        String line = "";
        while ((line = reader.readLine()) != null) {
            fileContent.add(line);
        }
        return fileContent;
    }
}
