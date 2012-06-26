import java.util.List;

/**
 * Class that exports its information in CSV format.
 */
public class ClassDefinitionExporter {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java ClassDefinitionExporter inputFile.asta outputFile.csv");
            return;
        }
        String inputFile = args[0];
        String outputFile = args[1];

        try {
            ClassDefinitionBuilder converter = new ClassDefinitionBuilder(inputFile);
            List contents = converter.getContents();

            CsvWriter writer = new CsvWriter(contents, outputFile);
            writer.write();

            System.out.println("Done");
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("Failed");
        }
    }
}
