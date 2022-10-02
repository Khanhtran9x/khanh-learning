package internet.com.entity.product.product_dto;

import javax.validation.constraints.Pattern;

public class ProductDTO {
    private int id;

    @Pattern(regexp = "")
    private String code;

    private String nameProduct;

    private Integer quantity;

    private String unit;

    private Integer prices;

    private String imageUrl;

    private int deleteStatus = 0;

    private Integer idProductCategory;

    public ProductDTO() {
    }

    public ProductDTO(int id, String code, String nameProduct, Integer quantity, String unit, Integer prices, String imageUrl, int deleteStatus, Integer idProductCategory) {
        this.id = id;
        this.code = code;
        this.nameProduct = nameProduct;
        this.quantity = quantity;
        this.unit = unit;
        this.prices = prices;
        this.imageUrl = imageUrl;
        this.deleteStatus = deleteStatus;
        this.idProductCategory = idProductCategory;
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

    public Integer getIdProductCategory() {
        return idProductCategory;
    }

    public void setIdProductCategory(Integer idProductCategory) {
        this.idProductCategory = idProductCategory;
    }
}
