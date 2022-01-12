package orderSummary;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderSummary {
    private String orderDate;
    private BigDecimal totalPrice;
    private String paymentMethod;
    private String orderStatus;

    public OrderSummary(String orderDate, BigDecimal totalPrice, String paymentMethod, String orderStatus) {
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.orderStatus = orderStatus;
    }
}
