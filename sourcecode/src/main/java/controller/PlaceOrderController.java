package controller;

import calculator.distance.DistanceCalculatorInterface;
import calculator.distance.EuclideanCalculator;
import calculator.shippingfee.DistanceBaseStrategy;
import common.exception.InvalidDeliveryInfoException;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.shipping.DeliveryInfo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * This class controls the flow of place order usecase in our AIMS project
 * @author nguyenlm
 */
// Vi phạm SRP: Các phương thức validate không phải nhiệm vụ của lớp này
// Khi có yêu cầu thay đổi cách validate
// OCP: Phụ thuộc trực tiếp distanceCalculator. Sau này có yêu cầu thay đổi cách tính thì phải vào sửa trực tiếp.
public class PlaceOrderController extends BaseController {

    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

    /**
     * This method checks the availability of product when user click PlaceOrder button
     * @throws SQLException
     */
    public void placeOrder() throws SQLException {
        SessionInformation.cartInstance.checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    public Order createOrder() throws SQLException {
        DistanceCalculatorInterface distanceCalculator = new EuclideanCalculator();
        return new Order(SessionInformation.cartInstance, new DistanceBaseStrategy(distanceCalculator));
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
     // OCP: Phụ thuộc trực tiếp distanceCalculator. Sau này có yêu cầu thay đổi cách tính thì phải vào sửa trực tiếp.
    public DeliveryInfo processDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        Validator.validateDeliveryInfo(info);
        DeliveryInfo deliveryInfo = new DeliveryInfo(
                String.valueOf(info.get("name")),
                String.valueOf(info.get("phone")),
                String.valueOf(info.get("province")),
                String.valueOf(info.get("address")),
                String.valueOf(info.get("instructions")));
        System.out.println(deliveryInfo.getProvince());
        return deliveryInfo;
    }
    
}
