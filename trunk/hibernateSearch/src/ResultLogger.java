import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.p6spy.engine.logging.appender.FileLogger;

public class ResultLogger extends FileLogger {

    public ResultLogger() {
    }

    public void logSQL(int connectionId, String now, long elapsed, String category, String prepared, String sql) {
        if ("resultset".equals(category)) {
            return;
        }
        String logEntry = "[" + now + "]" + category + "-->" + sql + "\r\n";

        OutputStreamWriter filewriter = null;
        try {
            filewriter = new OutputStreamWriter(new FileOutputStream("sql.log", true), "UTF-8");

            filewriter.write(logEntry);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (filewriter != null) {
                    filewriter.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
