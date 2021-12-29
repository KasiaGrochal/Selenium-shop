package handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorHelper {

    private static Logger logger = LoggerFactory.getLogger(CalculatorHelper.class);

    public static boolean isDiscountCalculatedCorrectly(BigDecimal regularPrice, BigDecimal discountPrice) {
        BigDecimal discountAmount = regularPrice.multiply(BigDecimal.valueOf(0.2)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal discountShouldBe = regularPrice.subtract(discountAmount);
        logger.info("Discount price should be: {}, displayed discount price is: {}", discountShouldBe, discountPrice);
        return discountShouldBe.equals(discountPrice);
    }
}
