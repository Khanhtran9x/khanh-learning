package internet.com.controller;

import internet.com.dto.record_dto.RecordDTO;
import internet.com.entity.payment.Payment;
import internet.com.entity.record.Record;
import internet.com.services.payment.IPaymentService;
import internet.com.services.record.IRecordService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PaymentController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private IRecordService recordService;

    /**
     * Create: LuanND
     * Date: 09/08/2022
     * function getAllPayment select item on database return Page
     *
     * @param page: page set for pagination
     */
    @GetMapping("/display")
    public ResponseEntity<Page<Payment>> getAllPayment (@RequestParam(name = "page", defaultValue = "0") int page) {
        Page<Payment> listPayment = paymentService.getAllPayment(PageRequest.of(page , 5));
        if (listPayment.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(listPayment , HttpStatus.OK);
    }

    /**
     * Create: LuanND
     * Date: 09/08/2022
     * function getAllPaymentList select item on database return List
     */
    @GetMapping("/list")
    public ResponseEntity<List<Payment>> getAllPaymentList() {
        List<Payment> listPayment = paymentService.getAllPaymentList();
        if (listPayment.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(listPayment, HttpStatus.OK);
    }

    /**
     * Create: LuanND
     * Date: 09/08/2022
     * function: getPaymentById select item on database with conditional id
     * param: id filter item same with id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable("id") Integer id) {
        Optional<Payment> payment = paymentService.getPaymentById(id);
        if (!payment.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(payment.get(), HttpStatus.OK);
    }


    /**
     * Create: LuanND
     * Date: 09/08/2022
     * function: savePayment create new record on database
     *
     * @param recordDTO is data to save on database
     */
    @PostMapping("/create")
    public ResponseEntity<Payment> savePayment(@RequestBody @Valid RecordDTO recordDTO) {
        Record record = this.recordService.findById(recordDTO.getId());
        Payment payment = new Payment();
        payment.setRecord(record);
        payment.setPaymentCode("ORD" + System.currentTimeMillis());
        paymentService.savePayment(payment);
        Payment newPayment = this.paymentService.findByCode(payment.getPaymentCode());
        return new ResponseEntity<>(newPayment, HttpStatus.CREATED);
    }

    /**
     * Create: LuanND
     * Date: 09/08/2022
     * function: setStatePayment change state payment
     *
     * @param id is data to set state payment on database
     */
    @GetMapping("/changes/{id}")
    public ResponseEntity<?> setStatePayment(@PathVariable("id") Integer id) {
        paymentService.editPayment(paymentService.getPaymentById(id).get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
