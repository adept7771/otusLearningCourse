package cases;

import core.TestCore;
import org.junit.Test;
import pages.HeaderMenuPage;
import pages.LoginPage;
import pages.PersonalCabinetMenuPage;

public class Lesson10Tests extends TestCore {

    @Test
    public void testFillPersonalContacts(){
        get("http://otus.ru");

        clickWithWait(new HeaderMenuPage().loginButton);
        LoginPage loginPage = new LoginPage();
        sendKeys(loginPage.emailInput, "dmitry.potapov@btsdigital.kz");
        sendKeys(loginPage.passwordInput, "NAd1R7IyOiFc");
        clickWithWait(loginPage.loginButton);
        clickWithWait(new HeaderMenuPage().myCoursesButton);
        clickWithWait(new PersonalCabinetMenuPage().aboutMySelfButton);

        waitStatic(10000);
    }

}
