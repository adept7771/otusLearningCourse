package steps;

import core.TestsCore;
import io.cucumber.java.en.Then;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import pages.HeaderMenuPage;
import pages.LessonsPage;
import pages.LoginPage;
import pages.PrivateDataPage;
import utils.Utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class PersonalCabinetSteps extends TestsCore {

    @Then("I can login with correct login and password")
    public void authorisationPositive() {
        LoginPage loginPage = new LoginPage();
        clickWithWait(new HeaderMenuPage().loginButton);
        sendKeys(loginPage.emailInput, "dmitry.potapov@btsdigital.kz");
        sendKeys(loginPage.passwordInput, "NAd1R7IyOiFc");
        clickWithWait(loginPage.loginButton);
    }

    @Then("I cant login with incorrect login and password")
    public void authorisationNegative() {
        LoginPage loginPage = new LoginPage();
        clickWithWait(new HeaderMenuPage().loginButton);
        sendKeys(loginPage.emailInput, "test@btsdigital.kz");
        sendKeys(loginPage.passwordInput, "test");
        clickWithWait(loginPage.loginButton);
    }

    @Then("I can change username to {string}")
    public void changeUsername(String username) {
        sendKeys(new PrivateDataPage().nameInput, username);
    }

    @Then("I can change latinUserName to {string}")
    public void changeLatinUserName(String username) {
        sendKeys(new PrivateDataPage().nameLatinInput, username);
    }

    @Then("I save new personal data")
    public void savePersonalData() {
        clickWithWait(new PrivateDataPage().saveDataButton);
    }


    @Then("I check personal data of {string} and {string} at personal data page")
    public void checkPersonalData(String username, String latinUserName) {
        PrivateDataPage privateDataPage = new PrivateDataPage();
        Assert.assertEquals(getText(privateDataPage.nameInput), username);
        Assert.assertEquals(getText(privateDataPage.nameLatinInput), latinUserName);
    }

    @Then("I check status of page object lesson")
    public void iCheckStatusOfPageObjectLesson() {
        Assert.assertFalse("Anna not checked PageObject home task",
                isElementExists(new LessonsPage().timerIconForPageObjectLesson, 5L));
    }

    @Then("I download avatar from personal cabinet")
    public void iDownloadAvatar() {
        String pathToSaveAvatar = getAttribute(new PrivateDataPage().avatarPicture, "outerHTML");
        String finalServerPathOfAvatar = pathToSaveAvatar.substring(
                pathToSaveAvatar.indexOf("https:"), pathToSaveAvatar.indexOf(".png") + 4);
        try (BufferedInputStream in = new BufferedInputStream(new URL(finalServerPathOfAvatar).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(
                     Utils.getPathToProjectRoot() + "\\src\\test\\resources\\downloadedImages\\kage.png"
             )) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException ignored) {
        }
    }

    @Then("I compare current avatar with uploaded one include file difference")
    public void iCompareCurrentAvatarWithUploadedOne() {
        String pathToDownloadedImage = Utils.getPathToProjectRoot() + "\\src\\test\\resources\\downloadedImages\\kage.png";
        String pathToUploadImage = Utils.getPathToProjectRoot() + "\\src\\test\\resources\\images\\kage.png";
        long downloadedFileSize = new File(pathToDownloadedImage).length();
        long uploadedFileSize = new File(pathToUploadImage).length();
        Assert.assertTrue("Difference between avatars greater then 3999 bytes",
                (downloadedFileSize - uploadedFileSize) < 4000L);
    }

    @Then("I can change avatar to test avatar")
    public void iCanChangeAvatarToTestAvatar() {
        PrivateDataPage privateDataPage = new PrivateDataPage();
        String pathToUploadImage = Utils.getPathToProjectRoot() + "\\src\\test\\resources\\images\\kage.png";
        sendKeysWOWaitVisibility(privateDataPage.avatarInput, pathToUploadImage);
        clickWithWait(privateDataPage.avatarInputAccept);
    }

    @Then("I see authorisation error")
    public void iSeeAuthorisationError() {
        Assert.assertTrue(isElementVisible(new LoginPage().authorisationError, 2L));
    }

    @Then("I see error message in personal data page field validation")
    public void iSeeErrorMessageInPersonalDataPageFieldValidation() {
        Assert.assertTrue(isElementVisible(new PrivateDataPage().errorMessage, 10L));
    }
}
