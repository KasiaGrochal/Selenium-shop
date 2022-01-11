package basketStore;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrderLine {

    private Product product;
    private int quantity;
    private BigDecimal totalCost;


    public OrderLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.totalCost = product.getPrice().multiply(BigDecimal.valueOf(getQuantity()));
    }

    public void updateTotalCost(){
        this.totalCost = product.getPrice().multiply(BigDecimal.valueOf(getQuantity()));
    }

}
