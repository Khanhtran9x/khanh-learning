package internet.com.controller;

import internet.com.entity.record.Record;
import internet.com.services.record.IRecordService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RecordController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IRecordService recordService;

    /**
     * Create by DuyNT
     * Create date: 15/08/2022
     * Function: Get value of record by id from DB
     */
    @GetMapping("/{id}")
    public ResponseEntity<Record> findById(@PathVariable("id") Integer id) {
        Record recordInfo = this.recordService.findById(id);
        if (recordInfo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recordInfo, HttpStatus.OK);
    }
}
