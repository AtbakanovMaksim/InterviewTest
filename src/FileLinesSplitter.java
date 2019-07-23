import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileLinesSplitter {
    private FileReader reader = new FileReader();
    private FileWriter writer = new FileWriter();
    private PathValidator validator = new PathValidator();
    private List<String> oldList = new ArrayList<>();
    private List<String> newList = new ArrayList<>();

    public List<String> getOldList() {
        return oldList;
    }

    public void setOldList(List<String> oldList) {
        this.oldList = oldList;
    }

    public List<String> getNewList() {
        return newList;
    }

    public void setNewList(List<String> newList) {
        this.newList = newList;
    }

    public String selectCases(String inputPath, int... numberOfLines) throws IOException {
        Integer numberOfLines1 = numberOfLines.length > 0 ? numberOfLines[0] : 10;
        if (validator.isFilenameValid(inputPath)) {
            String outputPath = createOutputPath(inputPath);
            setOldList(reader.readFile(inputPath));
            if (numberOfLines1 > getOldList().size()) {
                throw new IOException("Cannot replace [" + numberOfLines1 + "] lines! Input file too short");
            }
            System.out.println("Input file contains " + getOldList().size() + " lines");
            int counter = countContent(getOldList());
            System.out.println("Sum of all numbers in file: " + counter);
            setNewList(splitFile(oldList, numberOfLines1));
            writer.writeToFile(outputPath, newList);
            writer.writeToFile(inputPath, oldList);
            return outputPath;
        } else {
            throw new IOException("Invalid file path!");
        }
    }

    private List<String> splitFile(List<String> fileContent, int numberOfExtractedLines) {
        Random random = new Random();
        List<String> list = new ArrayList<>();
        list.add(fileContent.get(0));
        for (int i = 0; i < numberOfExtractedLines; i++) {
            int tmp = random.nextInt(fileContent.size() - 1) + 1;//to avoid getting first line
            list.add(fileContent.get(tmp));
            fileContent.remove(tmp);
        }
        return list;
    }

    private String createOutputPath(String path) {
        String[] tmp = path.split("\\.");
        tmp[tmp.length - 2] = tmp[tmp.length - 2] + "_res";
        String result = "";
        for (int i = 0; i < tmp.length - 1; i++) {
            tmp[i] = tmp[i] + ".";
        }
        for (int i = 0; i < tmp.length; i++) {
            result = result + tmp[i];
        }
        return result;
    }

    private Integer countContent(List<String> input) {
        int result = 0;
        ArrayList<String> colomnsList = new ArrayList<>();
        ArrayList<String> cellsList = new ArrayList<>();
        for (String line : input) {
            String[] colomns = line.split("\\|");
            for (int j = 0; j < colomns.length; j++) {
                colomnsList.add(colomns[j].trim());
            }
        }
        for (String i : colomnsList) {
            String[] cells = i.split(" ");
            for (int j = 0; j < cells.length; j++) {
                cellsList.add(cells[j].trim());
            }
        }
        for (String i : cellsList) {
            if (i.matches("\\d+"))
                result = result + Integer.parseInt(i);
        }
        return result;
    }
}
