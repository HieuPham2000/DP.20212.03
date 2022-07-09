package subsystem;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.payment.PaymentCard;
import entity.payment.PaymentTransaction;

/**
 * The {@code InterbankInterface} class is used to communicate with the
 * {@link InterbankSubsystem InterbankSubsystem} to make transaction
 * 
 * @author hieud
 * Vi phạm DIP: phụ thuộc trực tiếp lớp chi tiết CreditCard
 */
public interface InterbankInterface {

	/**
	 * Pay order, and then return the payment transaction
	 * 
	 * @param card     - the credit card used for payment
	 * @param amount   - the amount to pay
	 * @param contents - the transaction contents
	 * @return {@link PaymentTransaction PaymentTransaction} - if the
	 *         payment is successful
	 * @throws PaymentException      if responded with a pre-defined error code
	 * @throws UnrecognizedException if responded with an unknown error code or
	 *                               something goes wrong
	 */
	PaymentTransaction payOrder(PaymentCard card, int amount, String contents)
			throws PaymentException, UnrecognizedException;

	/**
	 * Refund, and then return the payment transaction
	 * 
	 * @param card     - the credit card which would be refunded to
	 * @param amount   - the amount to refund
	 * @param contents - the transaction contents
	 * @return {@link PaymentTransaction PaymentTransaction} - if the
	 *         payment is successful
	 * @throws PaymentException      if responded with a pre-defined error code
	 * @throws UnrecognizedException if responded with an unknown error code or
	 *                               something goes wrong
	 */

	/*
	 Vi phạm ISP do trong tương lai có thể phải thay đổi hệ thống thanh toán khác không có cơ chế hoàn tiền (refund method)
	*/
	PaymentTransaction refund(PaymentCard card, int amount, String contents)
			throws PaymentException, UnrecognizedException;

}
