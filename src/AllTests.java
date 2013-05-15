import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;



@RunWith(Suite.class)
@SuiteClasses({ AIPlayerTest.class, EuchreTest.class, PlayerTest.class, UtilsTest.class, CardTest.class, TrickTest.class, RoundTest.class, NullCardTest.class, EasyAIPlayerTest.class })
public class AllTests {

}
