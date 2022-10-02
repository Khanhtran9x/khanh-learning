package internet.com.services.product;

import internet.com.entity.product.ProductCategory;

import java.util.List;

public interface IProductCategoryService {
    List<ProductCategory> findAll();
}
