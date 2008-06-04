package domain;

import cn.hb.core.bean.BaseBean;

/**
 * @author kaka
 * 
 * 文件信息类容
 */
public class FileInfo extends BaseBean {

    private static final long serialVersionUID = 2602313105984510524L;

    private String filePath;
    private String fileName;

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
