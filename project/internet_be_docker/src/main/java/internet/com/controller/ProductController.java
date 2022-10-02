package internet.com.controller;
import internet.com.entity.payment.Payment;
import internet.com.entity.product.Product;
import internet.com.entity.product.ProductCategory;
import internet.com.entity.product.product_dto.IProductDTO;
import internet.com.entity.product.product_dto.ProductDTO;
import internet.com.services.payment.IPaymentService;
import internet.com.services.product.IProductCategoryService;
import internet.com.services.product.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductCategoryService productCategoryService;

    @Autowired
    private IPaymentService paymentService;

    /**
     * Create by: TruongTX
     * Date create: 10/08/2022
     * function: findAll and Search product
     */
    @GetMapping("/list")
    public ResponseEntity<Page<IProductDTO>> showListProduct(
            @RequestParam(name = "name") String name, @RequestParam(defaultValue = "0") int page
    ) {
        Sort sort = Sort.by("quantity").ascending();

        Page<IProductDTO> productList = productService.findAll(name, PageRequest.of(page, 8, sort));
        if (productList.isEmpty()) {
            return new ResponseEntity<>(productList, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    /**
     * Create by: TruongTX
     * Date create: 10/08/2022
     * function: findAll ProductCategory
     */

    @GetMapping("/listCategory")
    public ResponseEntity<List<ProductCategory>> showListProductCategory() {
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        if (productCategoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productCategoryList, HttpStatus.OK);
    }

    /**
     * Create by: TruongTX
     * Date create: 10/08/2022
     * function: delete Product
     */

    @DeleteMapping("/list/delete/{id}")
    public ResponseEntity<?> deleteByIdProduct(@PathVariable Integer id) {

        if (id == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Create by: TruongTX
     * Date create: 11/08/2022
     * function: create product
     */
    @PostMapping("/list/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO){
        productService.create(productDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @GetMapping("{id}")
//    public ResponseEntity<Product> findId (@PathVariable Integer id){
//        Product product = productService.findByIdProduct(id);
//        return new ResponseEntity<>(product,HttpStatus.OK);
//    }

    // Không cần phải chạy phương thức findById
    // Chỉ cần gửi @RequestBody qua cho PostMan là đc
    // FindById sẽ đc xử lí ngay trên câu query của update

    /**
     * Create by: TruongTX
     * Date create: 09/08/2022
     * function: update product
     */
    @PatchMapping("/list/update/{id}")
    public ResponseEntity<?> updateProduct (@PathVariable Integer id,@RequestBody Product product){
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);

        productService.updateProduct(productDTO.getCode(),
                                    productDTO.getNameProduct(),
                                    productDTO.getQuantity(),
                                    productDTO.getUnit(),
                                    productDTO.getPrices(),
                                    productDTO.getImageUrl(),
                                    productDTO.getIdProductCategory(),
                                    productDTO.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Create by: DuyNT
     * Date create: 14/08/2022
     * function: get list product from category option for ordering
     */
    @GetMapping("/order")
    public ResponseEntity<List<Product>> listProductForOrder() {
        List<Product> productList = productService.getListProductForOrdering();
        if (productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    /**
     * Create by: DuyNT
     * Date create: 14/08/2022
     * function: get list product from category option for ordering
     */
    @GetMapping("/order/{id}")
    public ResponseEntity<List<Product>> showListProductByCategoryId(@PathVariable Integer id) {
        List<Product> productList = productService.findProductByCategoryId(id);
        if (productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    /**
     * Create by: DuyNT
     * Date create: 14/08/2022
     * function: get product from DB by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> loadProductInfoById(@PathVariable Integer id) {
        Product product = productService.findByIdProduct(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Created: LuanND
     * Date: 17/08/2022
     * @param id is ID of payment Object
     * @return response about execute set data from list service of customer
     */
    @GetMapping("/data/{id}")
    public ResponseEntity<String> setDataProductOrder(@PathVariable Integer id) {
        Optional<Payment> payment = this.paymentService.getPaymentById(id);
        if (!payment.isPresent()) return new ResponseEntity<>("Không thành công" ,HttpStatus.NOT_FOUND);
        this.productService.setDataProductOrder(payment.get());
        return new ResponseEntity<>("Thành công" ,HttpStatus.NOT_FOUND);
    }
}
