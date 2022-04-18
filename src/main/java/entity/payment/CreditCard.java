package entity.payment;

/**
 * @author
 */
// Vi phạm OCP: Sau này yêu cầu thêm phương thức thanh toán mới (DomesticCard) => nên tạo lớp abstract Card
public class CreditCard {

    private String cardCode;
    private String owner;
    private String dateExpired;
    protected int cvvCode;

    public CreditCard(String cardCode, String owner, String dateExpired, int cvvCode) {
        this.cardCode = cardCode;
        this.owner = owner;
        this.dateExpired = dateExpired;
        this.cvvCode = cvvCode;
    }
}
