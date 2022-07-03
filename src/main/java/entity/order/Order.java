package entity.order;

import calculator.shippingfee.EuclideanCalculator;
import calculator.shippingfee.ShippingFeeCalculatorContext;
import controller.SessionInformation;
import entity.cart.Cart;
import entity.cart.CartItem;
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
        this.shippingFeeCalculator = new ShippingFeeCalculatorContext(new EuclideanCalculator());
    }

    public List getListOrderMedia() {
        return this.orderMediaList;
    }

    public int getShippingFees() {
        if (deliveryInfo == null) return 0;
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

    public String getFromAddress() {
        return deliveryInfo.getFromAddress();
    }

    public String getToAddress() {
        return deliveryInfo.getAddress() + deliveryInfo.getProvince();
    }

}
