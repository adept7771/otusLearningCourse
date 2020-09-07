package cases;

import core.TestCore;
import org.junit.Test;
import org.openqa.selenium.Keys;
import pages.HeaderMenuPage;
import pages.LoginPage;
import pages.PersonalCabinetMenuPage;
import pages.PrivateDataPage;
import utils.Generators;

public class Lesson10Tests extends TestCore {

    @Test
    public void testFillPersonalContacts() {
        get("http://otus.ru");

        clickWithWait(new HeaderMenuPage().loginButton);
        LoginPage loginPage = new LoginPage();
        sendKeys(loginPage.emailInput, "dmitry.potapov@btsdigital.kz");
        sendKeys(loginPage.passwordInput, "NAd1R7IyOiFc");
        clickWithWait(loginPage.loginButton);
        clickWithWait(new HeaderMenuPage().myCoursesButton);
        clickWithWait(new PersonalCabinetMenuPage().aboutMySelfButton);

        PrivateDataPage privateDataPage = new PrivateDataPage();
        String name = "name " + Generators.genString(5, true, true, true,
                true, false);
        sendKeys(privateDataPage.nameInput, name);

        String nameLatin = "latinName " + Generators.genString(5, true, true, true,
                true, false);
        sendKeys(privateDataPage.nameLatinInput, nameLatin);

        String surname = "surName " + Generators.genString(5, true, true, true,
                true, false);
        sendKeys(privateDataPage.surnameInput, surname);

        String surnameLat = "surNameLat " + Generators.genString(5, true, true, true,
                true, false);
        sendKeys(privateDataPage.surnameLatinInput, surnameLat);

        String blogName = "blogName " + Generators.genString(5, true, true, true,
                true, false);
        sendKeys(privateDataPage.nameForBlogInput, blogName);

        sendKeys(privateDataPage.birthDateInput, "14.07.1987");
        sendKeys(privateDataPage.birthDateInput, Keys.RETURN);

        scrollToElement(privateDataPage.emailInput);

        clickWithWait(privateDataPage.addContact1Dropdown);
        clickWithWait(privateDataPage.addContact1Type);
        String testSocial = "testSocial " + Generators.genString(5, true, true, true,
                true, false);
        sendKeys(privateDataPage.addContact1Input, testSocial);
        if (!isElementVisible(privateDataPage.addContact2Input, 2L)) {
            clickWithWait(privateDataPage.addContactLink);
        }
        clickWithWait(privateDataPage.addContact2Dropdown);
        clickWithWait(privateDataPage.addContact2Type);

        String testSocial2 = "testSocial2 " + Generators.genString(5, true, true, true,
                true, false);
        sendKeys(privateDataPage.addContact2Input, testSocial2);

        sendKeys(privateDataPage.companyInput, "test");
        sendKeys(privateDataPage.jobTitleInput, "test");

        clickWithWait(privateDataPage.saveDataButton);
    }
}
