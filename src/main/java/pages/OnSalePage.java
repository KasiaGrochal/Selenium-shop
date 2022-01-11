package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OnSalePage extends BasePage{
    public OnSalePage(WebDriver driver) {
        super(driver);
    }

@FindBy(css = "#js-product-list-header")
    private WebElement header;

    public String getHeaderText(){
        return getText(header);
    }
}
