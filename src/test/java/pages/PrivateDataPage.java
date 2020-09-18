package pages;

import core.TestsCore;
import org.openqa.selenium.By;

public class PrivateDataPage extends TestsCore {

    public By nameInput = By.xpath("//input[@name='fname']");
    public By nameLatinInput = By.xpath("//input[@name='fname_latin']");
    public By surnameInput = By.xpath("//input[@name='lname']");
    public By surnameLatinInput = By.xpath("//input[@name='lname_latin']");
    public By nameForBlogInput = By.xpath("//input[@name='blog_name']");
    public By birthDateInput = By.xpath("//input[@name='date_of_birth']");
    public By countryDropdownButton = By.xpath("//input[@name='country']/../div");
    public By countryFirstInDropdown = By.xpath("//button[@title='Россия']");
    public By cityDropdownButton = By.xpath("//input[@name='city']/../div");
    public By cityMoscowInDropdown = By.xpath("//button[@title='Москва']");
    public By englishLevelDropdownButton = By.xpath("//input[@name='english_level']");
    public By englishElementaryInDropdown = By.xpath("//button[@title='Элементарный уровень (Elementary)']");
    public By emailInput = By.xpath("(//input[@name='email'])[1]");
    public By phoneInput = By.xpath("(//input[@name='phone'])[1]");
    public By addContactLink = By.xpath("//button[contains(@class, 'js-lk-cv-custom-select-add')]");

    public By addContact1Dropdown = By.xpath("//input[@name='contact-0-service']/../div");
    public By addContact1Input = By.xpath("//input[@id='id_contact-0-value']");
    public By addContact1Type = By.cssSelector("[title='Facebook']");

    public By addContact2Dropdown = By.xpath("//input[@name='contact-1-service']/../div");
    public By addContact2Input = By.xpath("//input[@id='id_contact-1-value']");
    public By addContact2Type = By.cssSelector("[data-num='1'] [title='VK']");

    public By genderDropdownButton = By.xpath("//select[@name='gender']");
    public By genderMaleInDropdown = By.xpath("//option[@value='m']");
    public By companyInput = By.xpath("//input[@name='company']");
    public By jobTitleInput = By.xpath("//input[@name='work']");

    public By saveDataButton = By.xpath("//button[@name='continue']");
    public By successTitle = By.xpath("//span[@class='success']");
    public By errorMessage = By.xpath("//p[@class='input-line__error']");

    public By avatarInput = By.xpath("//input[@class='file-in-button js-avatar-file']");
    public By avatarInputAccept = By.xpath("//button[@class='button button_blue js-choose-crop']");
    public By avatarPicture = By.xpath("//div[@class='settings-photo']//div[contains(@style, 'url') " +
            "and contains(@class,'ic-blog-default-avatar')]");
}
