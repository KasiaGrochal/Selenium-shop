package pages.categories;

import handlers.FormatTextHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class CategoriesPage extends BasePage {
    public CategoriesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".h1")
    private WebElement categoryName;

    @FindBy(css = ".total-products")
    private WebElement productsCountInfo;


    public String getCategoryName(){
        return getText(categoryName);
    }

    public String getTotalProductsInfo(){
        return getText(productsCountInfo);
    }

    public Integer getTotalProductsInfoAsInt(){
        return FormatTextHandler.getIntFromString(getTotalProductsInfo());
    }


}
