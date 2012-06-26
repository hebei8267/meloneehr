import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

/**
 * Class that exports given contents in CSV format. 
 */
public class CsvWriter {

    private String separator = ",";

    /**
     * Set CSV format separator to export.
     * @param separator
     */
    public void setSeparator(String separator) {
        this.separator = separator;
    }

    private List contents;

    private String fileName;

    /**
     * @param contents Contents to export (String List stored in List)
     * @param filePath File Path to export 
     */
    public CsvWriter(List contents, String filePath) {
        this.contents = contents;
        this.fileName = filePath;
    }

    /**
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
