package file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import domain.FileInfo;

/**
 * @author kaka
 * 
 * 得到指定路径下所有文件信息（包含子文件）
 */
public class FindFilesUtils {
    public static void main(String[] args) {
        FindFilesUtils utils = new FindFilesUtils();

        List<FileInfo> fileInfoList = utils.getFileInfoList("C:\\11\\uml-src\\");

        for (Object object : fileInfoList) {
            System.out.println(object);
        }
    }

    public final static String JAVA_FILE = ".java";
    public final static String HIDDEN_FILE = ".";
    public final static String SEPARATOR = "\\";

    public List<FileInfo> getFileInfoList(String path) {
        List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
        getFileInfoList(path, fileInfoList);
        return fileInfoList;
    }

    private void getFileInfoList(String path, List<FileInfo> fileInfoList) {
        try {

            File file = new File(path);
            if (file.isFile()) {// 文件
                if (file.getName().endsWith(JAVA_FILE)) {// java文件

                    FileInfo fileInfo = new FileInfo();

                    fileInfo.setFileName(file.getName().replace(JAVA_FILE, ""));
                    fileInfo.setFilePath(file.getParent());

                    fileInfoList.add(fileInfo);
                }
            } else {// 文件夹
                String[] files = file.list();
                for (String fileName : files) {
                    if (!fileName.startsWith(HIDDEN_FILE)) {// 非隐藏文件夹
                        getFileInfoList(path + SEPARATOR + fileName, fileInfoList);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
