package pages;

import org.openqa.selenium.By;

public class PrivateDataPage {

    public By nameInput = By.xpath("//input[@name='fname']");
    public By nameLatinInput = By.xpath("//input[@name='fname_latin']");
    public By surnameInput = By.xpath("//input[@name='lname']");
    public By surnameLatinInput = By.xpath("//input[@name='lname_latin']");
    public By nameForBlogInput = By.xpath("//input[@name='blog_name']");
    public By birthDateInput = By.xpath("//input[@name='date_of_birth']");
    public By countryDropdownButton = By.xpath("(//span[contains(text(), 'Не указано')])[1]");
    public By countryFirstInDropdown = By.xpath("(//button[@data-value='1'])[1]");
    public By cityDropdownButton = By.xpath("//input[@name='city']");
    public By cityMoscowInDropdown = By.xpath("(//button[@data-value='317'])[1]");
    public By englishLevelDropdownButton = By.xpath("//input[@name='english_level']");
    public By englishElementaryInDropdown = By.xpath("//button[@title='Элементарный уровень (Elementary)']");
    public By emailInput = By.xpath("(//input[@name='email'])[1]");
    public By phoneInput = By.xpath("(//input[@name='phone'])[1]");
    public By addContactLink = By.xpath("//button[contains(@class, 'js-lk-cv-custom-select-add')]");

    public By addContact2Dropdown = By.xpath("(//span[text()='Способ связи'])[1]");
    public By addContact2Input = By.xpath("//input[@id='id_contact-2-value']");
    public By addContact2Type = By.xpath("(//button[@title='Facebook'])[3]");

    public By addContact3Dropdown = By.xpath("(//span[text()='Способ связи'])[2]");
    public By addContact3Input = By.xpath("//input[@id='id_contact-3-value']");
    public By addContact3Type = By.xpath("(//button[@title='Skype'])[4]");

    public By genderDropdownButton = By.xpath("//select[@name='gender']");
    public By genderMaleInDropdown = By.xpath("//option[@value='m']");
    public By companyInput = By.xpath("//input[@name='company']");
    public By jobTitleInput = By.xpath("//input[@name='work']");
}
