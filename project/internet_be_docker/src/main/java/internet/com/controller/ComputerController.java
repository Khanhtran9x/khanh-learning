package internet.com.controller;

import internet.com.dto.computer_dto.ComputerDTO;
import internet.com.dto.computer_dto.ComputerListDto;
import internet.com.entity.computer.Computer;
import internet.com.entity.computer.ComputerType;
import internet.com.services.computer.IComputerService;
import internet.com.services.computer.impl.ComputerTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/computers")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ComputerController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IComputerService computerService;

    @Autowired
    private ComputerTypeService computerTypeService;

    /**
     * Create by: TuanHD
     * Date Create: 09/08/2022
     * funtion: Create item in computer
     * @param computerDTO
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<Object> createComputer(@RequestBody @Valid ComputerDTO computerDTO) {
        Computer computer = modelMapper.map(computerDTO, Computer.class);
        computerService.createComputer(computer);
        return new ResponseEntity<>(computer, HttpStatus.CREATED);
    }

    /**
     * Create by: TuanHD
     * Date Create: 09/08/2022
     * funtion: Edit item in computer
     * @param computerDTO
     * @return
     */
    @PatchMapping(value = "/edit/{id}")
    public ResponseEntity<Computer> editComputer(@PathVariable("id") Integer id,@RequestBody @Valid ComputerDTO computerDTO){
        Computer computer = modelMapper.map(computerDTO, Computer.class);
        computerService.updateComputer(id,computer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Create by: TuanHD
     * Date Create: 10/08/2022
     * funtion: findById item in computer
     * @param id
     * @return
     */
    @GetMapping(value = "/list/{id}")
    public ResponseEntity<Computer> findById(@PathVariable("id") Integer id){
        Computer computer= computerService.findById(id);
        if (computer == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(computer,HttpStatus.OK);
    }

    /**
     * Created by: PhucNQ
     * Date created: 09/08/2022
     * Function: findAll
     */

    @GetMapping("/{page}")
    public ResponseEntity<Object> findAll(@PathVariable("page") Integer page,
                                     @RequestParam( name = "code") String code,
                                     @RequestParam( name = "location") String location,
                                     @RequestParam( name = "start") String start,
                                     @RequestParam( name = "end") String end,
                                     @RequestParam( name = "typeId") String typeId,
                                     @RequestParam( name = "status") String status) {
        Sort sort = Sort.by("id").ascending();
        Page<ComputerListDto> computers = computerService.findAll(PageRequest.of(page, 4, sort),code,location,start, end,typeId, status);
        if(computers != null){
            return new ResponseEntity<>(computers, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Created by: PhucNQ
     * Date created: 09/08/2022
     * Function: delete
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTicket(@PathVariable("id") Integer id){
        computerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Create by: TuanHD
     * Date Create: 11/08/2022
     * funtion: findAllComputerType item in computerType
     * @return
     */
    @GetMapping(value = "/list/computer-type")
    public ResponseEntity<List<ComputerType>> getListComputerType() {
        List<ComputerType> computerType = computerTypeService.findAll();
        if (computerType.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(computerType, HttpStatus.OK);
    }

    /**
     * Create by: HoangHN
     * Date Create: 17/08/2022
     * funtion: return Computer
     * @return
     */
    @GetMapping(value = "/returnComputer/{id}")
    public ResponseEntity<?> returnComputer(@PathVariable("id") Integer id) {
        computerService.setActiveStatus(id,1);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Create by: TuanHD
     * Date Create: 11/08/2022
     * funtion: checkCode
     * @return
     */
    @GetMapping("/check/{code}")
    public  ResponseEntity<?> checkCode(@PathVariable("code") String code){
        return new ResponseEntity<>(computerService.existsCode(code), HttpStatus.OK);
    }

    /**
     * Create by: TuanHD
     * Date Create: 11/08/2022
     * funtion: checkLocation
     * @return
     */
    @GetMapping("/checkLocation/{location}")
    public  ResponseEntity<?> checkLocation(@PathVariable("location") String location){
        return new ResponseEntity<>(computerService.existsLocation(location), HttpStatus.OK);
    }
}
