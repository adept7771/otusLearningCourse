package cases;

import core.TestCore;
import org.junit.Test;
import pages.HeaderMenuPage;

public class Lesson10Tests extends TestCore {

    @Test
    public void fillPersonalContactsTest(){
        get("http://otus.ru");

        HeaderMenuPage headerMenuPage = new HeaderMenuPage();

        clickWithWait(headerMenuPage.loginButton);

        waitStatic(10000);
    }

}
