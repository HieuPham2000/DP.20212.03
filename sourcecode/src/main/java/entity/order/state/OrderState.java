package entity.order.state;

import entity.order.Order;

public abstract class OrderState {

    protected Order order;

    public OrderState(Order order) {
        this.order = order;
    }

    public void payOrder() {
        throw new RuntimeException("This state is not support operator");
    }

    public void cancelOrder() {
        throw new RuntimeException("This state is not support operator");
    }

}
