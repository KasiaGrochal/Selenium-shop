package categories;

import model.Pages;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CategoriesTest extends Pages {

    @Test
    @Tag("categories")
    @Tag("regressionSmall")
    void openEachCategoryAndValidateDisplayedInformation() {
        List<WebElement> listOfCategories = topMenuPage.getListOfCategories();

        for (int i = 0; i < listOfCategories.size(); i++) {
            WebElement currentCategory = listOfCategories.get(i);
            String categoryName = topMenuPage.getText(currentCategory);
            topMenuPage.
                    clickOnCategory(currentCategory);
            assertThat(categoriesPage.getCategoryName(), equalTo(categoryName));
            assertThat(filtersPage.isFilterBoxVisible(), equalTo(true));
            assertThat(productsGridPage.getAmountOfDisplayedProducts(), equalTo(categoriesPage.getTotalProductsInfoAsInt()));
        }
    }

    @Test
    @Tag("subcategories")
    @Tag("regressionSmall")
    void openEachSubCategoryAndValidateDisplayedInformation() {
        List<WebElement> listOfCategories = topMenuPage.getListOfCategories();

        for (int i = 0; i < listOfCategories.size(); i++) {
            for (int j = 1; j < topMenuPage.getListOfSubcategories(i).size(); j++) {
                WebElement currentCategory = listOfCategories.get(i);
                List<WebElement> listOfSubCategories = topMenuPage.getListOfSubcategories(i);

                topMenuPage.
                        moveToCategoryName(currentCategory);
                WebElement currentSubCategory = listOfSubCategories.get(j);
                String subCategoryName = currentSubCategory.getText();

                topMenuPage.click(currentSubCategory);
                assertThat(categoriesPage.getCategoryName(), equalTo(subCategoryName));
                assertThat(filtersPage.isFilterBoxVisible(), equalTo(true));
                assertThat(productsGridPage.getAmountOfDisplayedProducts(), equalTo(categoriesPage.getTotalProductsInfoAsInt()));

                topMenuPage.moveToCategoryName(topMenuPage.getListOfCategories().get(i));
            }
        }
    }
}
