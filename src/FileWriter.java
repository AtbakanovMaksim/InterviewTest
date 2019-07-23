import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileWriter {

    public void writeToFile(String outputPath, List<String> newList) {
        Path path = Paths.get(outputPath);
        String resultFile = createStringFromList(newList);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(resultFile);
        } catch (IOException e) {
            System.out.println("Cannot write to a file [" + outputPath + "]!");
            e.printStackTrace();
        }
    }

    private String createStringFromList(List<String> newList) {
        StringBuffer sb = new StringBuffer();
        for (String i : newList) {
            sb.append(i);
            sb.append("\n");
        }
        return sb.toString();
    }
}
