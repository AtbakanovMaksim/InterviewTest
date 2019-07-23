import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FileLinesSplitter fls = new FileLinesSplitter();
            String resultPath = fls.selectCases("D:\\Java\\test-interviewEPAM\\Resources\\textfile.txt");
        } catch (IOException e) {
            System.out.println("Something go wrong! Sorry! :)");
            e.printStackTrace();
        }
    }
}
