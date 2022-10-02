package internet.com.services.product.impl;

import internet.com.entity.product.ProductCategory;
import internet.com.repository.product_repo.IProductCategoryRepository;
import internet.com.services.product.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService implements IProductCategoryService {

    @Autowired
    private IProductCategoryRepository iProductCategoryRepository;

    /**
     * Create by: TruongTX
     * Date create: 10/08/2022
     * function: findAll productCategory
     *
     */

    @Override
    public List<ProductCategory> findAll() {
        return iProductCategoryRepository.findAll();
    }
}
