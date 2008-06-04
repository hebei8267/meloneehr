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

        List<FileInfo> fileInfoList = utils.getFileInfoList("C:/cn");

        for (Object object : fileInfoList) {
            System.out.println(object);
        }
    }

    public List<FileInfo> getFileInfoList(String path) {
        List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
        getFileInfoList(path, fileInfoList);
        return fileInfoList;
    }

    private void getFileInfoList(String path, List<FileInfo> fileInfoList) {
        try {

            File file = new File(path);
            if (file.isFile()) {// 文件
                FileInfo fileInfo = new FileInfo();

                fileInfo.setFileName(file.getName());
                fileInfo.setFilePath(file.getParent());

                fileInfoList.add(fileInfo);
            } else {// 文件夹
                String[] files = file.list();

                for (String fileName : files) {
                    getFileInfoList(path + "/" + fileName, fileInfoList);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
