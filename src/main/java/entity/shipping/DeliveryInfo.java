package entity.shipping;

public class DeliveryInfo {

	// content coupling: nên để private
    private String name;
    private String phone;
    private String province;
    private String address;
    private String shippingInstructions;
    private String fromAddress = "Hà Nội";

    public DeliveryInfo(String name, String phone, String province, String address, String shippingInstructions) {
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.address = address;
        this.shippingInstructions = shippingInstructions;
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

    public String getFromAddress() {
        return fromAddress;
    }
}
