package internet.com.controller;

import internet.com.dto.payment_dto.PaymentDetailDTO;
import internet.com.entity.payment.PaymentDetail;
import internet.com.services.payment.IPaymentDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/paymentDetail")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PaymentDetailController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IPaymentDetailService paymentDetailService;

    /**
     * Create by DuyNT
     * Create date: 12/08/2022
     * Function: Get value of payment detail by id from DB
     */
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDetail> findById(@PathVariable("id") Integer id) {
        Optional<PaymentDetail> paymentDetail = this.paymentDetailService.findPaymentDetailById(id);
        if (!paymentDetail.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(paymentDetail.get(), HttpStatus.OK);
    }

    /**
     * Create by DuyNT
     * Create date: 12/08/2022
     * Function: create payment detail and save into DB when guest order product
     */
    @PostMapping("/create")
    public ResponseEntity<PaymentDetail> createPaymentDetail(@RequestBody @Valid PaymentDetailDTO paymentDetailDTO) {
        PaymentDetail paymentDetail = modelMapper.map(paymentDetailDTO, PaymentDetail.class);
        paymentDetailService.savePaymentDetail(paymentDetail);
        return new ResponseEntity<>(paymentDetail, HttpStatus.CREATED);
    }

}
