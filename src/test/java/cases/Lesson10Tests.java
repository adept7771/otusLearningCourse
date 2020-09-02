package cases;

import core.TestCore;
import org.junit.Test;
import org.openqa.selenium.Keys;
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
        sendKeys(privateDataPage.birthDateInput, Keys.RETURN);

        waitStatic(1000);

        moveToElement(privateDataPage.countryDropdownButton);
        clickWithWait(privateDataPage.countryDropdownButton);
        clickWithWait(privateDataPage.countryFirstInDropdown);
        clickWithWait(privateDataPage.cityDropdownButton);
        clickWithWait(privateDataPage.cityMoscowInDropdown);
        clickWithWait(privateDataPage.englishLevelDropdownButton);
        clickWithWait(privateDataPage.englishElementaryInDropdown);
        sendKeys(privateDataPage.emailInput, "test@mail.ru");
        sendKeys(privateDataPage.phoneInput, "+77774732669");

        clickWithWait(privateDataPage.addContactLink);
        clickWithWait(privateDataPage.addContact2Dropdown);
        clickWithWait(privateDataPage.addContact2Type);
        sendKeys(privateDataPage.addContact2Input, "testSocial");

        clickWithWait(privateDataPage.addContactLink);
        clickWithWait(privateDataPage.addContact3Dropdown);
        clickWithWait(privateDataPage.addContact3Type);
        sendKeys(privateDataPage.addContact3Input, "testSocial2");

        clickWithWait(privateDataPage.genderDropdownButton);
        clickWithWait(privateDataPage.genderMaleInDropdown);

        sendKeys(privateDataPage.companyInput, "test");
        sendKeys(privateDataPage.jobTitleInput, "test");

        waitStatic(10000);
    }

}
