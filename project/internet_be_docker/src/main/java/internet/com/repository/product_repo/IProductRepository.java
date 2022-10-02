package internet.com.repository.product_repo;

import internet.com.entity.product.Product;
import internet.com.entity.product.product_dto.IProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface IProductRepository extends JpaRepository<Product, Integer> {
    /**
     * Create by: TruongTX
     * Date create: 10/08/2022
     * Query: findAll and search product
     */
    @Query(value = "select p.id as id, product_code as code, product_name as nameProduct, quantity as quantity, " +
            "unit as unit, prices as prices, image_url as imageUrl, delete_status as deleteStatus, p.id_product_category as idProductCategory, pc.product_category_name as productCategoryName" +
            " from product p right join product_category as pc on pc.id = p.id_product_category  where delete_status = 0 and product_name like %:name%"
            , nativeQuery = true, countQuery = "select count(*) from product p  join product_category pc on pc.id = p.id_product_category where delete_status = 0 and product_name like %:name%")
    Page<IProductDTO> findAll(@Param("name") String name, Pageable pageable);

//    @Query(value = "select id, product_code, product_name, quantity as quantity, " +
//            "unit as unit, prices as prices, image_url, delete_status ,id_product_category  " +
//            "from product where delete_status = 0 and product_name like %:name%"
//            , nativeQuery = true,countQuery = "select count(*) from product where delete_status = 0 and product_name like %:name%")
//


    /**
     * Create by: TruongTX
     * Date create: 10/08/2022
     * Query: delete product
     *
     * @param id-
     */

    @Modifying
    @Query(value = "update product set delete_status = 1 where id = :id", nativeQuery = true)
    void delete(@Param("id") Integer id);

    /**
     * Create by: TruongTX
     * Date create: 11/08/2022
     * Query: create product
     */
    @Modifying
    @Query(value = "insert into product (product_code,product_name,quantity, unit, prices, image_url, id_product_category, delete_status)" +
            " values (:code,:name,:quantity,:unit,:prices,:imageUrl,:idProductCategory,0)", nativeQuery = true)
    void createProduct(@Param("code") String code,
                       @Param("name") String name,
                       @Param("quantity") Integer quantity,
                       @Param("unit") String unit,
                       @Param("prices") Integer prices,
                       @Param("imageUrl") String imageUrl,
                       @Param("idProductCategory") Integer idProductCategory);


    /**
     * Create by: TruongTX
     * Date create: 11/08/2022
     * Query: findById product
     *
     * @param id-
     */
    @Query(value = "select id , product_code, product_name, quantity,unit, prices, image_url, delete_status," +
            "id_product_category from product where id = :id", nativeQuery = true)
    Product findByIdProduct(@Param("id") int id);

    /**
     * Create by: TruongTX
     * Date create: 11/08/2022
     * Query: update product
     */
    @Modifying
    @Query(value = "update product set product_code = :code, product_name = :name, quantity = :quantity,unit = :unit," +
            " prices = :prices, image_url = :imageUrl,id_product_category = :idProductCategory,delete_status = 0 where id = :id", nativeQuery = true)
    void updateProduct(@Param("code") String code,
                       @Param("name") String name,
                       @Param("quantity") Integer quantity,
                       @Param("unit") String unit,
                       @Param("prices") Integer prices,
                       @Param("imageUrl") String imageUrl,
                       @Param("idProductCategory") Integer idProductCategory,
                       @Param("id") Integer id);

    /**
     * Create by: DuyNT
     * Date create: 14/08/2022
     * Query: get list product from DB by product category id
     */
    @Query(value = "SELECT * FROM product", nativeQuery = true)
    List<Product> getAllProductForOrdering();

    /**
     * Create by: DuyNT
     * Date create: 14/08/2022
     * Query: get list product from DB by product category id
     */
    @Query(value = "SELECT * FROM product WHERE id_product_category = :id", nativeQuery = true)
    List<Product> getListByCategoryId(@Param("id") Integer id);
}
