import java.io.File;
import java.io.IOException;

public class PathValidator {
    boolean isFilenameValid(final String aFileName) {
        final File aFile = new File(aFileName);
        boolean isValid = true;
        try {
            if (aFile.createNewFile()) {
                aFile.delete();
            }
        } catch (IOException e) {
            isValid = false;
        }
        return isValid;
    }
}
