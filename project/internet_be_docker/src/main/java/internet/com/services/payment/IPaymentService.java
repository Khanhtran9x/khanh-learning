package internet.com.services.payment;

import internet.com.entity.payment.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IPaymentService {
    Page<Payment> getAllPayment (Pageable pageable);

    List<Payment> getAllPaymentList ();

    Optional<Payment> getPaymentById (Integer id);

    void savePayment (Payment payment);

    void editPayment (Payment payment);

    Payment findByCode(String code);
}
