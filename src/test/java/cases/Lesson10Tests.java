package cases;

import core.TestCore;
import org.junit.Test;
import pages.HeaderMenuPage;
import pages.LoginPage;
import pages.PersonalCabinetMenuPage;
import pages.PrivateDataPage;

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

        PrivateDataPage privateDataPage = new PrivateDataPage();
        sendKeys(privateDataPage.nameInput, "testName");
        sendKeys(privateDataPage.nameLatinInput, "testName2");
        sendKeys(privateDataPage.surnameInput, "test");
        sendKeys(privateDataPage.surnameLatinInput, "test");
        sendKeys(privateDataPage.nameForBlogInput, "test");
        sendKeys(privateDataPage.birthDateInput, "14.07.1987");


        waitStatic(10000);
    }

}
