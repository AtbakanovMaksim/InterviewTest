import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {

    public List<String> readFile(String inputPath) {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(inputPath))) {
            list = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Cannot read file [" + inputPath + "]!");
            e.printStackTrace();
        }
        return list;
    }
}
