package internet.com.entity.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import internet.com.entity.payment.PaymentDetail;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_code")
    private String code;

    @Column(name = "product_name")
    private String nameProduct;

    private Integer quantity;

    private String unit;

    private Integer prices;

    private String imageUrl;

    //    @ColumnDefault("0")
    private int deleteStatus = 0;

    @ManyToOne
    @JoinColumn(name = "id_product_category", referencedColumnName = "id")
    private ProductCategory productCategory;

    @JsonBackReference
    @OneToMany(mappedBy = "product")
    private Set<PaymentDetail> paymentDetailSet;

    public Product() {
    }

    public Product(int id,
                   String code,
                   String nameProduct,
                   Integer quantity,
                   String unit,
                   Integer prices,
                   String imageUrl,
                   int deleteStatus,
                   ProductCategory productCategory,
                   Set<PaymentDetail> paymentDetailSet) {
        this.id = id;
        this.code = code;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
        this.unit = unit;
        this.prices = prices;
        this.imageUrl = imageUrl;
        this.deleteStatus = deleteStatus;
        this.productCategory = productCategory;
        this.paymentDetailSet = paymentDetailSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getPrices() {
        return prices;
    }

    public void setPrices(Integer prices) {
        this.prices = prices;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Set<PaymentDetail> getPaymentDetailSet() {
        return paymentDetailSet;
    }

    public void setPaymentDetailSet(Set<PaymentDetail> paymentDetailSet) {
        this.paymentDetailSet = paymentDetailSet;
    }
}