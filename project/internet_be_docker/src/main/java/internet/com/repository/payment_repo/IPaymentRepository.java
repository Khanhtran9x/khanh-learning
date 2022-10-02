package internet.com.repository.payment_repo;

import internet.com.entity.game.Game;
import internet.com.entity.payment.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface IPaymentRepository extends JpaRepository<Payment, Integer> {
    @Query(value = "select * from payment", nativeQuery = true)
    Page<Payment> getAllPagePayment(Pageable pageable);

    @Query(value = "SELECT * FROM payment", nativeQuery = true)
    List<Payment> getAllPaymentList ();

    @Query(value = "SELECT * FROM payment WHERE id = :id", nativeQuery = true)
    Optional<Payment> getPaymentById (@Param("id") Integer id);

    @Modifying
    @Query(value = "INSERT INTO payment(payment_code, record_id, total_pay, payment_status) VALUES (:pCode, :iRecord, :total, :status)", nativeQuery = true)
    void savePayment (@Param("pCode") String pCode , @Param("iRecord") Integer iRecord ,
                      @Param("total") Integer total , @Param("status") Integer status);

    @Modifying
    @Query(value = "UPDATE payment SET payment_code = :pCode, record_id = :iRecord, total_pay = :total, payment_status = :status WHERE id = :id", nativeQuery = true)
    void editPayment (@Param("id") Integer id , @Param("pCode") String pCode , @Param("iRecord") Integer iRecord ,
                      @Param("total") Integer total , @Param("status") Integer status);

    @Query(value = "SELECT * FROM payment WHERE payment_code = :code", nativeQuery = true)
    Optional<Payment> getByPaymentCode (@Param("code") String paymentCode);
}
