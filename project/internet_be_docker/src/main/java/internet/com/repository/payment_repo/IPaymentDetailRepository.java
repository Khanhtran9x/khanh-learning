package internet.com.repository.payment_repo;

import internet.com.entity.payment.PaymentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@Transactional
public interface IPaymentDetailRepository extends JpaRepository<PaymentDetail, Integer> {

    /**
     * Create by DuyNT
     * Create date: 12/08/2022
     * Function: Create payment detail when guest order product
     */
    @Modifying
    @Query(value = "INSERT INTO payment_detail (amount, payment_id, product_id) VALUES (:amount, :paymentId, :productId);",
            nativeQuery = true)
    void createPaymentDetail(@Param("amount") Integer amount,
                             @Param("paymentId") Integer paymentId,
                             @Param("productId") Integer productId);
}
