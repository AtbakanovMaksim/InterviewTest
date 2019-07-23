import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

public class PathValidatorTest {
        @DataProvider(name = "addMethodDataProvider")
        public Object[][] dataProvider() {
        return new Object[][] {
                { "well.txt", true },
                { "well well.txt", true },
                { "test.T*T", false },
                { "test|.TXT", false },
                { "te?st.TXT", false },
                { "testTXT/^", false },
                { "z://prn.TXT", true },
                { "", false }, };
        }

        @Test(dataProvider = "addMethodDataProvider")
        public void isFilenameValidTest(String a, boolean b) {
            PathValidator validator = new PathValidator();
            Assert.assertEquals(validator.isFilenameValid(a), b);
        }
}
