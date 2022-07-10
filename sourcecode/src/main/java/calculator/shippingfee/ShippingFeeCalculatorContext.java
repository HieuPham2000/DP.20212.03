package calculator.shippingfee;

import entity.order.Order;

public class ShippingFeeCalculatorContext {

    private ShippingFeeCalculatorStrategy strategy;

    public ShippingFeeCalculatorContext(ShippingFeeCalculatorStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(ShippingFeeCalculatorStrategy strategy) {
        this.strategy = strategy;
    }

    public int calculate(Order order) {
        return strategy.calculate(order);
    }
}
