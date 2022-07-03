package entity.payment;

import java.util.Map;

public abstract class PaymentCard {

    protected String owner;
    protected String cardCode;

    public abstract Map<String, Object> toTransactionMap();
}
