package calculator.shippingfee;

import calculator.distance.DistanceCalculatorInterface;
import entity.order.Order;

public class DistanceBaseStrategy extends ShippingFeeCalculatorStrategy {

    public DistanceBaseStrategy(DistanceCalculatorInterface distanceCalculator) {
        super(distanceCalculator);
    }

    @Override
    public int calculate(Order order) {
        String address = order.getAddress();
        String province = order.getProvince();
        return distanceCalculator.calculate(address, province);
    }

}
