package pages.commons;

import configuration.WebListener;
import handlers.FormatTextHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.internal.EventFiringMouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;
import pages.user.AccountPage;
import pages.user.LoginPage;

import java.util.List;

public class TopMenuPage extends BasePage {
    Logger logger = LoggerFactory.getLogger(TopMenuPage.class);

    private EventFiringMouse eventFiringMouse;
    private WebListener webListener;

    public TopMenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".user-info span")
    private WebElement loginButton;

    @FindBy(css = ".account span")
    private WebElement accountLoggedInFullName;

    @FindBy(css = ".user-info a")
    private List<WebElement> userInfo;

    @FindBy(css = ".ui-autocomplete-input")
    private WebElement searchBox;

    @FindBy(css = "[class='material-icons search']")
    private WebElement searchIcon;

    @FindBy(css = ".ui-menu-item")
    private List<WebElement> searchDropdownList;

    @FindBy(css = ".ui-menu-item")
    private WebElement elementFromDropdown;

    @FindBy(css = "#category-9")
    private WebElement categoryArt;

    @FindBy(css = "#_desktop_cart .cart-products-count")
    private WebElement basketProductsCount;


    @FindBy(css = "#top-menu>li")
    private List<WebElement> categoriesList;

    public TopMenuPage clickOnCategory(WebElement category){
        click(category);
        return this;
    }


    public List<WebElement> getListOfSubcategories(int indexOfCategory) {
        return getListOfCategories().get(indexOfCategory).findElements(By.cssSelector("div>ul>li>a"));
    }

    public TopMenuPage clickOnBasketIcon() {
        waitForElementToBeClickableFluent(basketProductsCount);
        click(basketProductsCount);
        return this;
    }

    public Integer getBasketProductCount() {
        webDriverwait.until(x->!getText(basketProductsCount).isEmpty());
        return FormatTextHandler.getIntFromString(getText(basketProductsCount));
    }

    public List<WebElement> getListOfCategories() {
        List<WebElement> listOfCategories = categoriesList;
        return listOfCategories;
    }

    public TopMenuPage goToRandomCategory() {
        waitForWebElementToBeClickable(categoryArt);
        click(getRandomWebElementFromList(getListOfCategories()));
        return this;
    }

    public TopMenuPage moveToCategoryName(WebElement category) {
        action.moveToElement(category).build().perform();
        logger.info("moved to category: {}", category.getText());
        return this;
    }


    public boolean checkIfSearchedProductIsOnTheDropdownList(String productName) {
        waitForWebElementToBeVisable(elementFromDropdown);
        for (WebElement webElement : searchDropdownList) {
            if (getText(webElement).contains(productName)) {
                return true;
            }
        }
        return false;
    }

    public TopMenuPage clickOnSearchIcon() {
        click(searchIcon);
        return this;
    }


    public TopMenuPage typeInSearchBox(String text) {
        send(searchBox, text);
        return this;
    }

    public LoginPage goToLoginPage() {
        click(loginButton);
        return new LoginPage(driver);
    }

    public boolean isUserLoggedIn() {
        return userInfo.size() > 1;
    }

    public String getDisplayedUserName() {
        String displayed = accountLoggedInFullName.getText();
        logger.info("Displayed username is: {}", displayed);
        return displayed;
    }

    public AccountPage clickOnAccountNameButton() {
        accountLoggedInFullName.click();
        logger.info("Clicked on account name button. Navigating to Account Page");
        return new AccountPage(driver);
    }


}
