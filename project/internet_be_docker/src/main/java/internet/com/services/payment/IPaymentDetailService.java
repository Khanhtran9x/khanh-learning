package internet.com.services.payment;

import internet.com.entity.payment.PaymentDetail;

import java.util.Optional;

public interface IPaymentDetailService {

    /**
     * Create by DuyNT
     * Create date: 12/08/2022
     * Function: Get value of payment detail by id
     */
    Optional<PaymentDetail> findPaymentDetailById(Integer id);

    /**
     * Create by DuyNT
     * Create date: 12/08/2022
     * Function: Save payment detail when guest order product
     */
    void savePaymentDetail(PaymentDetail paymentDetail);
}
