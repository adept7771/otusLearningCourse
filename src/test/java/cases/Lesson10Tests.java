package cases;

import core.TestCore;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
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
        String name = "name" + Generators.genString(5, true, true, false,
                false, false);
        sendKeys(privateDataPage.nameInput, name);

        String nameLatin = "latinName" + Generators.genString(5, true, true, false,
                false, false);
        sendKeys(privateDataPage.nameLatinInput, nameLatin);

        String surname = "surName" + Generators.genString(5, true, true, false,
                false, false);
        sendKeys(privateDataPage.surnameInput, surname);

        String surnameLat = "surNameLat" + Generators.genString(5, true, true, false,
                false, false);
        sendKeys(privateDataPage.surnameLatinInput, surnameLat);

        String blogName = "blogName" + Generators.genString(5, true, true, false,
                false, false);
        sendKeys(privateDataPage.nameForBlogInput, blogName);

        String birthDate = "14.07.1987";
        sendKeys(privateDataPage.birthDateInput, birthDate);
        sendKeys(privateDataPage.birthDateInput, Keys.RETURN);

        clickWithWait(privateDataPage.countryDropdownButton);
        clickWithWait(privateDataPage.countryFirstInDropdown);

        scrollToElement(privateDataPage.birthDateInput);
        clickWithWait(2L, privateDataPage.cityDropdownButton);
        try {
            clickWithWait(2L, privateDataPage.cityMoscowInDropdown);
        }
        catch (TimeoutException e){
            clickWithWait(privateDataPage.cityDropdownButton);
            clickWithWait(2L, privateDataPage.cityMoscowInDropdown);
        }

        scrollToElement(privateDataPage.emailInput);

        clickWithWait(privateDataPage.addContact1Dropdown);
        clickWithWait(privateDataPage.addContact1Type);
        String testSocial = "testSocial" + Generators.genString(5, true, true, true,
                false, false);
        sendKeys(privateDataPage.addContact1Input, testSocial);
        if (!isElementVisible(privateDataPage.addContact2Input, 1L)) {
            clickWithWait(privateDataPage.addContactLink);
        }
        clickWithWait(privateDataPage.addContact2Dropdown);
        clickWithWait(privateDataPage.addContact2Type);

        String testSocial2 = "testSocial2" + Generators.genString(5, true, true, true,
                false, false);
        sendKeys(privateDataPage.addContact2Input, testSocial2);

        String company = "company" + Generators.genString(5, true, true, true,
                false, false);
        sendKeys(privateDataPage.companyInput, company);

        String jobTitle = "jobTitle" + Generators.genString(5, true, true, true,
                false, false);
        sendKeys(privateDataPage.jobTitleInput, jobTitle);

        clickWithWait(privateDataPage.saveDataButton);
        clickWithWait(privateDataPage.successTitle);

        webDriver.close();
        get("http://otus.ru");

        clickWithWait(new HeaderMenuPage().loginButton);
        loginPage = new LoginPage();
        sendKeys(loginPage.emailInput, "dmitry.potapov@btsdigital.kz");
        sendKeys(loginPage.passwordInput, "NAd1R7IyOiFc");
        clickWithWait(loginPage.loginButton);
        clickWithWait(new HeaderMenuPage().myCoursesButton);
        clickWithWait(new PersonalCabinetMenuPage().aboutMySelfButton);

        Assert.assertEquals("Name is different then defined", name, getText(privateDataPage.nameInput));
        Assert.assertEquals("NameLatin is different then defined", nameLatin, getText(privateDataPage.nameLatinInput));
        Assert.assertEquals("Surname is different then defined", surname, getText(privateDataPage.surnameInput));
        Assert.assertEquals("SurNameLatin is different then defined", surnameLat, getText(privateDataPage.surnameLatinInput));
        Assert.assertEquals("BlogName is different then defined", blogName, getText(privateDataPage.nameForBlogInput));
        Assert.assertEquals("BirthDate is different then defined", birthDate, getText(privateDataPage.birthDateInput));
        Assert.assertEquals("First social link is different then defined", testSocial, getText(privateDataPage.addContact1Input));
        Assert.assertEquals("Second social link is different then defined", testSocial2, getText(privateDataPage.addContact2Input));
        Assert.assertEquals("Company name is different then defined", company, getText(privateDataPage.companyInput));
        Assert.assertEquals("Job title is different then defined", jobTitle, getText(privateDataPage.jobTitleInput));
    }
}
