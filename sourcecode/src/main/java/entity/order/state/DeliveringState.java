package entity.order.state;

import entity.order.Order;

public class DeliveringState extends OrderState {

    public DeliveringState(Order order) {
        super(order);
    }

    @Override
    public void cancelOrder() {
        this.order.setState(new CancelledState(this.order));
    }

}
