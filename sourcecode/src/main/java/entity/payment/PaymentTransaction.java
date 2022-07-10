package entity.payment;

public class PaymentTransaction {
	private String errorCode;
	// Vi phạm OCP: Phụ thuộc trực tiếp CreditCard, do có yêu cầu thêm phương thức thanh toán mới (DomesticCard)
	private PaymentCard card;
	private String transactionId;
	private String transactionContent;
	private int amount;
	private String createdAt;
	
	public PaymentTransaction(String errorCode, PaymentCard card, String transactionId, String transactionContent,
                              int amount, String createdAt) {
		super();
		this.errorCode = errorCode;
		this.card = card;
		this.transactionId = transactionId;
		this.transactionContent = transactionContent;
		this.amount = amount;
		this.createdAt = createdAt;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
}
