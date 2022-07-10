package entity.order.state;

import entity.order.Order;

public class PaymentPendingState extends OrderState {

    public PaymentPendingState(Order order) {
        super(order);
    }

    @Override
    public void payOrder() {
        this.order.setState(new DeliveringState(this.order));
    }

    @Override
    public void cancelOrder() {
        this.order.setState(new CancelledState(this.order));
    }
}
