package internet.com.dto.payment_dto;

import internet.com.entity.payment.Payment;
import internet.com.entity.product.Product;

public class PaymentDetailDTO {
    private Integer id;
    private Integer amount;
    private Payment payment;
    private Product product;

    public PaymentDetailDTO() {
    }

    public PaymentDetailDTO(Integer id, Integer amount, Payment payment, Product product) {
        this.id = id;
        this.amount = amount;
        this.payment = payment;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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
}
