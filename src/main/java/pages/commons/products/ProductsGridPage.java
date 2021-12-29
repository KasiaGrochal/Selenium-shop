package pages.commons.products;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductsGridPage extends BasePage {
    public ProductsGridPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product")
    private List<WebElement> listOfProducts;

    public Integer getAmountOfDisplayedProducts(){
        return getListOfProducts().size();
    }

    public ProductDetailsPage clickOnRandomProduct(){
        getRandomWebElementFromList(listOfProducts).click();
        return new ProductDetailsPage(driver);
    }


    public List<ProductBoxPage> getListOfProducts() {
        List<ProductBoxPage> listOfProductBox = new ArrayList<>();
        for (WebElement webElement : listOfProducts) {
            listOfProductBox.add(new ProductBoxPage(webElement, driver));
        }
        return listOfProductBox;
    }

    public String getRandomProductName() {
        List<ProductBoxPage> listOfProductBox = getListOfProducts();
        int randomNumber = new Random().nextInt(listOfProductBox.size());
        return listOfProductBox.get(randomNumber).getProductName();
    }

    public boolean isProductOnTheList(String productName) {
        List<ProductBoxPage> listOfProductBox = getListOfProducts();
        for (ProductBoxPage productBoxPage : listOfProductBox) {
            if (productBoxPage.getProductName().equals(productName)) {
                return true;
            }
        } return false;
    }
}

