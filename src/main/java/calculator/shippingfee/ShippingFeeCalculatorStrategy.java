package calculator.shippingfee;

import calculator.distance.DistanceCalculatorInterface;
import entity.order.Order;

public abstract class ShippingFeeCalculatorStrategy {

    protected DistanceCalculatorInterface distanceCalculator;

    public ShippingFeeCalculatorStrategy(DistanceCalculatorInterface distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    public abstract int calculate(Order order);

}
