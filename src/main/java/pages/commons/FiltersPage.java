package pages.commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

public class FiltersPage extends BasePage {
    public FiltersPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#search_filters>p")
    private WebElement filterBox;

    @FindBy(css = ".facet-label")
    private List<WebElement> listOfFilters;

    public boolean isFilterBoxVisible(){
        return getTextFromObject(filterBox).equals(System.getProperty("filterBoxText"));
    }

}
