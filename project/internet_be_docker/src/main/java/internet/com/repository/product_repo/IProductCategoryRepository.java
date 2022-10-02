package internet.com.repository.product_repo;


import internet.com.entity.product.ProductCategory;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface IProductCategoryRepository extends PagingAndSortingRepository<ProductCategory, Integer> {

    /**
     * Create by: TruongTX
     * Date create: 09/08/2022
     * Query: findAll prductCategory
     *
     */
    @Query(value = "select id, product_category_name from product_category", nativeQuery = true)
    List<ProductCategory> findAll();
}
