package entity.shipping;

import calculator.shippingfee.EuclideanCalculator;
import calculator.shippingfee.ShippingFeeCalculatorContext;

public class DeliveryInfo {

	// content coupling: nên để private
    protected String name;
    protected String phone;
    protected String province;
    protected String address;
    protected String shippingInstructions;
    protected ShippingFeeCalculatorContext shippingFeeCalculatorContext;

    public DeliveryInfo(String name, String phone, String province, String address, String shippingInstructions, ShippingFeeCalculatorContext shippingFeeCalculatorContext) {
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.address = address;
        this.shippingInstructions = shippingInstructions;
        this.shippingFeeCalculatorContext = shippingFeeCalculatorContext;
    }

    // SRP: Việc tính phí ship nên tách ra, vì có yêu cầu thay đổi cách tính. Đây không phải nhiệm vụ của class này.
    // OCP: Phụ thuộc trực tiếp distanceCalculator. Sau này có yêu cầu thay đổi cách tính thì phải vào sửa trực tiếp.
    // pthieu 18.4.2022
    public int calculateShippingFee() {
        shippingFeeCalculatorContext.setStrategy(new EuclideanCalculator());
        return shippingFeeCalculatorContext.calculate(address, province);
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getProvince() {
        return province;
    }

    public String getAddress() {
        return address;
    }

    public String getShippingInstructions() {
        return shippingInstructions;
    }
}
