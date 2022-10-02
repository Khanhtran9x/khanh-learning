package internet.com.services.payment.impl;

import internet.com.entity.payment.Payment;
import internet.com.repository.payment_repo.IPaymentRepository;
import internet.com.services.payment.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService implements IPaymentService {
    @Autowired
    private IPaymentRepository paymentRepository;

    @Override
    public Page<Payment> getAllPayment (Pageable pageable) {
        return paymentRepository.getAllPagePayment(pageable);
    }

    @Override
    public List<Payment> getAllPaymentList() {
        return paymentRepository.getAllPaymentList();
    }

    @Override
    public Optional<Payment> getPaymentById(Integer id) {
        return paymentRepository.getPaymentById(id);
    }

    @Override
    public void savePayment(Payment payment) {
        paymentRepository.savePayment(payment.getPaymentCode(), payment.getRecord().getId(), payment.getTotalPay(), payment.getPaymentStatus());
    }

    @Override
    public void editPayment(Payment payment) {
        payment.setPaymentStatus(1);
        paymentRepository.editPayment(payment.getId(), payment.getPaymentCode(), payment.getRecord().getId(), payment.getTotalPay(), payment.getPaymentStatus());
    }

    @Override
    public Payment findByCode(String code) {
        return this.paymentRepository.getByPaymentCode(code).get();
    }


}
