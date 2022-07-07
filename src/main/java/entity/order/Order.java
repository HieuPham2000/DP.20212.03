package entity.order;

import calculator.distance.EuclideanCalculator;
import calculator.shippingfee.DistanceBaseStrategy;
import calculator.shippingfee.ShippingFeeCalculatorContext;
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
    private ShippingFeeCalculatorContext shippingFeeCalculator;
    private OrderState state;

    public Order(Cart cart) {
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
        ShippingFeeCalculatorStrategy strategy = new DistanceBaseStrategy(new EuclideanCalculator());
        this.shippingFeeCalculator = new ShippingFeeCalculatorContext(strategy);
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
        this.shippingFees = shippingFeeCalculator.calculate(this);
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
