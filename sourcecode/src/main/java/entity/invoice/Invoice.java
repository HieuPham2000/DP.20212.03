package entity.invoice;

import entity.order.Order;

public class Invoice {

    private Order order;
    private int amount;
    
    public Invoice(Order order) {
        this.order = order;
        this.amount = order.getTotal();
    }

    public Order getOrder() {
        return order;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void payOrder() {
        order.payOrder();
    }

    public void cancelOrder() {
        order.cancelOrder();
    }

}