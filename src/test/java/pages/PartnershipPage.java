package pages;

import core.TestsCore;
import org.openqa.selenium.By;

public class PartnershipPage extends TestsCore {

    public By teacherRequestButton = By.xpath("//div[@class='teachers-invite-header container']//button[@data-modal-id='teachers-request']");
    public By teacherFullNameInput = By.xpath("//input[@name='fullname']");
    public By teacherPhoneInput = By.xpath("//input[@name='phone' and @data-title='Телефон']");
    public By teacherEmailInput = By.xpath("//input[@name='email' and @placeholder='Почта *']");
    public By beTeacherButton = By.xpath("//button[@class='new-button new-button_full new-button_blue teachers-invite__button js-modal-button-title']");
    public By successModalWindowOkButton = By.xpath
            ("//button[@class='new-button new-button_full new-button_blue teachers-invite__button js-close-modal']");

}
