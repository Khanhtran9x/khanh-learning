package internet.com.entity.payment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import internet.com.entity.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "payment_detail")
public class PaymentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer amount;

    @ManyToOne(targetEntity = Payment.class)
    @JsonBackReference
    private Payment payment;

    @ManyToOne(targetEntity = Product.class)
    private Product product;

    public PaymentDetail() {
    }

    public PaymentDetail(Integer id, Payment payment, Product product) {
        this.id = id;
        this.payment = payment;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
