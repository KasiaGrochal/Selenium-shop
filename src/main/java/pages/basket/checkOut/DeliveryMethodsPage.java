package pages.basket.checkOut;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import pages.BasePage;

public class DeliveryMethodsPage extends BasePage {

    public DeliveryMethodsPage(WebElement deliveryMethod, WebDriver driver) {
        super(driver);
        DefaultElementLocatorFactory parent = new DefaultElementLocatorFactory(deliveryMethod);
        PageFactory.initElements(parent, this);
    }


    @FindBy(css = ".carrier-name")
    private WebElement deliveryName;

    @FindBy(css = "div")
    private WebElement deliveryRadioButton;

    public String getDeliveryMethodName() {
        return getText(deliveryName);
    }

    public void clickOnDeliveryMethod() {
        clickOnCheckBox(deliveryRadioButton);
    }


}
