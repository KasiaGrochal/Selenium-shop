package basketStore;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


public class Basket {

    public List<OrderLine> getBasketLists() {
        return basketLists;
    }

    public List<OrderLine> basketLists;
    public int totalQuantity;
    public BigDecimal totalCost;

    public Basket() {
        basketLists = new ArrayList<>();
        totalQuantity = getBasketTotalQuantity();
        totalCost =getBasketTotalCost();
    }


    public void updateBasketTotalCost(){
        this.totalCost = getBasketTotalCost();
    }

    public void addProductToBasket(String productName, BigDecimal price, int quantity){
        for (OrderLine orderLine: basketLists){
            if (isProductAlreadyInTheBasket(productName,orderLine.getProduct())){
                orderLine.setQuantity(orderLine.getQuantity()+quantity);
                orderLine.setTotalCost(BigDecimal.valueOf(orderLine.getQuantity()).multiply(orderLine.getProduct().getPrice()));
                return;
            }
        }
        addNewProductToBasket(new Product(productName,price),quantity);

    }

    public boolean isProductAlreadyInTheBasket(String productName, Product product){
        return product.getName().equals(productName);
    }

    public void addNewProductToBasket(Product product, int quantity){
        basketLists.add(new OrderLine(product,quantity));
    }

    public void addOrderLineToBasket(OrderLine orderLine){
        basketLists.add(orderLine);
    }


    public BigDecimal getBasketTotalCost(){
        BigDecimal total = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP);
        for (OrderLine orderLine: basketLists){
            total = total.add(orderLine.getTotalCost());
        }
        return total;
    }

    public int getBasketTotalQuantity(){
        int total = 0;
        for (OrderLine orderLine: basketLists){
            total +=orderLine.getQuantity();
        }
        return total;
    }

    public void deleteOrderLine(int orderLineIndex){
        basketLists.remove(basketLists.get(orderLineIndex));
    }

    public Integer getOrderLineQuantity(int orderLineIndex){
        return getBasketLists().get(orderLineIndex).getQuantity();
    }


}
