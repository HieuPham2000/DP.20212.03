package entity.order;

import calculator.shippingfee.ShippingFeeCalculatorStrategy;
import controller.SessionInformation;
import entity.cart.Cart;
import entity.cart.CartItem;
import entity.order.state.OrderState;
import entity.shipping.DeliveryInfo;
import views.screen.ViewsConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {

    private int shippingFees;
    private int subtotal;
    private int tax;
    private List orderMediaList;
    private DeliveryInfo deliveryInfo;
    private OrderState state;
    private ShippingFeeCalculatorStrategy strategy;

    public Order(Cart cart, ShippingFeeCalculatorStrategy strategy) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (Object object : SessionInformation.cartInstance.getListMedia()) {
            CartItem cartItem = (CartItem) object;
            OrderItem orderItem = new OrderItem(cartItem.getMedia(),
                    cartItem.getQuantity(),
                    cartItem.getPrice());
            orderItems.add(orderItem);
        }
        this.orderMediaList = Collections.unmodifiableList(orderItems);
        this.subtotal = cart.calSubtotal();
        this.tax = (int) (ViewsConfig.PERCENT_VAT/100) * subtotal;
        this.strategy = strategy;
    }

    public List getListOrderMedia() {
        return this.orderMediaList;
    }

    public int getShippingFees() {
        if (deliveryInfo == null) {
            throw new RuntimeException("Order has no delivery information");
        }
        return this.shippingFees;
    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
        this.shippingFees = strategy.calculate(this);
    }

    public int getSubtotal() {
        return subtotal;
    }

    public int getTotal() {
        return this.subtotal + this.tax + this.shippingFees;
    }

    public String getProvince() {
        return this.deliveryInfo.getProvince();
    }

    public String getAddress() {
        return this.deliveryInfo.getAddress();
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void payOrder() {
        state.payOrder();
    }

    public void cancelOrder() {
        state.cancelOrder();
    }

}
