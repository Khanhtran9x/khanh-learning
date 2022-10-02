package internet.com.services.payment.impl;

import internet.com.entity.payment.PaymentDetail;
import internet.com.repository.payment_repo.IPaymentDetailRepository;
import internet.com.services.payment.IPaymentDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentDetailService implements IPaymentDetailService {

    @Autowired
    private IPaymentDetailRepository paymentDetailRepository;


    /**
     * Create by DuyNT
     * Create date: 12/08/2022
     * Function: Get value of payment detail by id
     */
    @Override
    public Optional<PaymentDetail> findPaymentDetailById(Integer id) {
        return this.paymentDetailRepository.findById(id);
    }

    /**
     * Create by DuyNT
     * Create date: 12/08/2022
     * Function: Create payment detail when guest order product
     */
    @Override
    public void savePaymentDetail(PaymentDetail paymentDetail) {
        this.paymentDetailRepository.createPaymentDetail(paymentDetail.getAmount(), paymentDetail.getPayment().getId(), paymentDetail.getProduct().getId());
    }
}
