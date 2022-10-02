package internet.com.dto.payment_dto;
import internet.com.entity.record.Record;

public class PaymentDTO {
    private Integer id;
    private String paymentCode;
    private Record record;
    private Integer paymentStatus = 0;
    private Integer totalPay = 0;

    public PaymentDTO () {
    }

    public PaymentDTO (Integer id , String paymentCode , Record record , Integer paymentStatus , Integer totalPay) {
        this.id = id;
        this.paymentCode = paymentCode;
        this.record = record;
        this.paymentStatus = paymentStatus;
        this.totalPay = totalPay;
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getPaymentCode () {
        return paymentCode;
    }

    public void setPaymentCode (String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public Record getRecord () {
        return record;
    }

    public void setRecord (Record record) {
        this.record = record;
    }

    public Integer getPaymentStatus () {
        return paymentStatus;
    }

    public void setPaymentStatus (Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getTotalPay () {
        return totalPay;
    }

    public void setTotalPay (Integer totalPay) {
        this.totalPay = totalPay;
    }
}
