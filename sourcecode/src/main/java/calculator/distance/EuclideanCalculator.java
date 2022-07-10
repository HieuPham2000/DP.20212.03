package calculator.distance;

import org.example.DistanceCalculator;

public class EuclideanCalculator implements DistanceCalculatorInterface {

    private DistanceCalculator calculator;

    public EuclideanCalculator() {
        calculator = new DistanceCalculator();
    }

    @Override
    public int calculate(String address, String province) {
        return calculator.calculateDistance(address, province);
    }

}
