import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

/**
 * �^����ꂽ���e���ACSV�`���ŏo�͂���N���X�B
 * Class that exports given contents in CSV format. 
 */
public class CsvWriter {

    private String separator = ",";

    /**
     * �o�͂���csv�`���̃Z�p���[�^��ݒ肷��B
     * Set CSV format separator to export.
     * @param separator �Z�p���[�^
     */
    public void setSeparator(String separator) {
        this.separator = separator;
    }

    private List contents;

    private String fileName;

    /**
     * @param contents CSV�o�͂�����e�iList�Ɋi�[���ꂽString��List�j
     *                 Contents to export (String List stored in List)
     * @param filePath �o�͂���t�@�C���p�X
     *                 File Path to export 
     */
    public CsvWriter(List contents, String filePath) {
        this.contents = contents;
        this.fileName = filePath;
    }

    /**
     * �t�@�C���ɏo�͂���B
     * Export to File.
     * @throws IOException
     */
    public void write() throws IOException {
        File csvFile = new File(fileName);
        Writer writer = new BufferedWriter(new FileWriter(csvFile));
        writeAllLines(writer);
        writer.close();
    }

    private void writeAllLines(Writer writer) throws IOException {
        for (Iterator iter = contents.iterator(); iter.hasNext();) {
            List line = (List)iter.next();
            for (Iterator iterator = line.iterator(); iterator.hasNext();) {
                String cell = (String)iterator.next();
                writer.write("\"" + cell + "\"");
                if (iterator.hasNext()) {
                    writer.write(separator);
                }
            }
            writer.write("\n");
        }
    }
}
