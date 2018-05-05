import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(DataProviderRunner.class)
public class OptimalPathServiceTest {

    private OptimalPathService optimalPathService;

    @Before
    public void initialize() {
        optimalPathService = new OptimalPathService();
    }

    @DataProvider
    public static Object[][] data() {
        return new Object[][] {
                { new ArrayList<String>(){{
                    add("x=>");
                    add("y=>z");
                    add("z=>");
                }}, "xzy" },
                { new ArrayList<String>(){{
                    add("u=>");
                    add("v=>w");
                    add("w=>z");
                    add("x=>u");
                    add("y=>v");
                    add("z=>");
                }}, "uzwvxy" },
                { new ArrayList<String>(){{
                    add("x=>");
                    add("y=>");
                    add("z=>");
                }}, "xyz" },
                { new ArrayList<String>(){{
                    add("x=>");
                }}, "x" }
        };
    }

    @Test
    @UseDataProvider("data")
    public void testPathCalculation(final List<String> input, final String expected) {
        Assert.assertEquals(optimalPathService.calculateOptimalPath(input), expected);
    }
}
