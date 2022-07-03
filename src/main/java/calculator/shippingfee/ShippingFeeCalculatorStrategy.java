package calculator.shippingfee;

import entity.order.Order;

public interface ShippingFeeCalculatorStrategy {

    int calculate(Order order);

}
