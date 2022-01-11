package pages.commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class FooterPage extends BasePage {
    public FooterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#link-product-page-prices-drop-1")
    private WebElement pricesDropButton;

    @FindBy(css = "[title='Orders']")
    private WebElement orders;

    public FooterPage clickOnPricesDropButton(){
        click(pricesDropButton);
        return this;
    }

    public FooterPage clickOnOrdersButton(){
        click(orders);
        return this;
    }
}
