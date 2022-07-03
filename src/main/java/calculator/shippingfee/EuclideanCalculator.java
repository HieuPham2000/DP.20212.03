package calculator.shippingfee;

import entity.order.Order;
import org.example.DistanceCalculator;

public class EuclideanCalculator implements ShippingFeeCalculatorStrategy {

    private DistanceCalculator distanceCalculator;

    @Override
    public int calculate(Order order) {
        String from = order.getFromAddress();
        String to = order.getToAddress();
        int distance = distanceCalculator.calculateDistance(from, to);
        return (int) (distance * 1.2);
    }

}
