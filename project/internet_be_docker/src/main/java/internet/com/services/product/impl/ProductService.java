package internet.com.services.product.impl;

import internet.com.entity.payment.Payment;
import internet.com.entity.payment.PaymentDetail;
import internet.com.entity.product.Product;
import internet.com.entity.product.product_dto.IProductDTO;
import internet.com.entity.product.product_dto.ProductDTO;
import internet.com.repository.product_repo.IProductRepository;
import internet.com.services.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository iProductRepository;

    /**
     * Create by: TruongTX
     * Date create: 10/08/2022
     * function: findAll and Search product
     */
    @Override
    public Page<IProductDTO> findAll (String name , Pageable pageable) {
        return iProductRepository.findAll(name , pageable);
    }

    /**
     * Create by: TruongTX
     * Date create: 09/08/2022
     * function: delete product
     */

    @Override
    public void delete (Integer id) {
        iProductRepository.delete(id);
    }

    /**
     * Create by: TruongTX
     * Date create: 11/08/2022
     * function: create product
     */
    @Override
    public void create (ProductDTO productDTO) {
        iProductRepository.createProduct(productDTO.getCode() ,
                productDTO.getNameProduct() ,
                productDTO.getQuantity() ,
                productDTO.getUnit() ,
                productDTO.getPrices() ,
                productDTO.getImageUrl() ,
                productDTO.getIdProductCategory());
    }

    /**
     * Create by: TruongTX
     * Date create: 11/08/2022
     * function: findById product
     */
    @Override
    public Product findByIdProduct (Integer id) {
        return iProductRepository.findByIdProduct(id);
    }

    /**
     * Create by: TruongTX
     * Date create: 11/08/2022
     * function: update product
     */
    @Override
    public void updateProduct (String code , String name , Integer quantity , String unit , Integer prices , String imageUrl , Integer idProductCategory , Integer id) {
        iProductRepository.updateProduct(code , name , quantity , unit , prices , imageUrl , idProductCategory , id);
    }

    @Override
    public void save (Product product) {
        iProductRepository.save(product);
    }

    /**
     * Create by: DuyNT
     * Date create: 14/08/2022
     * function: get all product for ordering
     */
    @Override
    public List<Product> getListProductForOrdering () {
        return this.iProductRepository.getAllProductForOrdering();
    }

    /**
     * Create by: DuyNT
     * Date create: 14/08/2022
     * function: get list product from category option for ordering
     */
    @Override
    public List<Product> findProductByCategoryId (Integer id) {
        return this.iProductRepository.getListByCategoryId(id);
    }

    /**
     * Created: LuanND
     * Date: 17/08/2022
     * Description: Execute decrease quantity of product when customer order success
     * @param payment is bill to process
     */
    @Override
    public void setDataProductOrder (Payment payment) {
        Product product;
        List<PaymentDetail> listPaymentDetail = payment.getPaymentDetailList();
        List<Product> listProduct = iProductRepository.getAllProductForOrdering();
        for (PaymentDetail paymentDetail : listPaymentDetail) {
            for (Product value : listProduct) {
                if (paymentDetail.getProduct().getId() == value.getId()) {
                    product = paymentDetail.getProduct();
                    product.setQuantity(product.getQuantity() - paymentDetail.getAmount());
                    iProductRepository.updateProduct(product.getCode() ,
                            product.getNameProduct() ,
                            product.getQuantity() ,
                            product.getUnit() ,
                            product.getPrices() ,
                            product.getImageUrl() ,
                            product.getProductCategory().getId() ,
                            product.getId());
                }
            }
        }
    }


}
