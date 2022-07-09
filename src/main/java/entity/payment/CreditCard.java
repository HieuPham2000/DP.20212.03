package entity.payment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author
 */
// Vi phạm OCP: Sau này yêu cầu thêm phương thức thanh toán mới (DomesticCard) => nên tạo lớp abstract Card
public class CreditCard extends PaymentCard {

    private String dateExpired;
    private int cvvCode;

    public CreditCard(String cardCode, String owner, String dateExpired, int cvvCode) {
        this.cardCode = cardCode;
        this.owner = owner;
        this.dateExpired = dateExpired;
        this.cvvCode = cvvCode;
    }

    @Override
    public Map<String, Object> toTransactionMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("owner", this.owner);
        map.put("dateExpired", this.dateExpired);
        map.put("cvvCode", this.cvvCode);
        map.put("cardCode", this.cardCode);
        
        return map;
    }
}
