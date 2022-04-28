package calculator.shippingfee;

import org.example.DistanceCalculator;

public class EuclideanCalculator implements ShippingFeeCalculatorStrategy {

    private DistanceCalculator distanceCalculator;

    @Override
    public int calculate(String from, String to) {
        int distance = distanceCalculator.calculateDistance(from, to);
        return (int) (distance * 1.2);
    }

}
