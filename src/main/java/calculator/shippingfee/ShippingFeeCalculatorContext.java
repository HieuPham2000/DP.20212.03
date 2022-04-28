package calculator.shippingfee;

public class ShippingFeeCalculatorContext {

    private ShippingFeeCalculatorStrategy strategy;

    public ShippingFeeCalculatorContext() {
    }

    public void setStrategy(ShippingFeeCalculatorStrategy strategy) {
        this.strategy = strategy;
    }

    public int calculate(String from, String to) {
        return strategy.calculate(from, to);
    }
}
