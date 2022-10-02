package internet.com.services.computer;

import internet.com.dto.computer_dto.ComputerListDto;
import internet.com.entity.computer.Computer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IComputerService {

    /**
     * Created by: TuanHD
     * Date created: 09/08/2022
     * Function: findById
     */
    Computer findById(Integer id);

    /**
     * Created by: TuanHD
     * Date created: 09/08/2022
     * Function: createComputer
     */
    void createComputer(Computer computer);

    /**
     * Created by: TuanHD
     * Date created: 09/08/2022
     * Function: updateComputer
     */
    void updateComputer(Integer id,Computer computer);

    /**
     * Created by: PhucNQ
     * Date created: 09/08/2022
     * Function: findAll
     */
    Page<ComputerListDto> findAll(Pageable pageable,
                                  String code,
                                  String location,
                                  String start,
                                  String end,
                                  String typeId,
                                  String status);

    /**
     * Created by: PhucNQ
     * Date created: 09/08/2022
     * Function: findAll
     */
    void delete(Integer id);

    /**
     * Created by: HoangHN
     * Date created: 14/08/2022
     */
    List<Computer> findUnusedComputer();

    /**
     * Created by: HoangHN
     * Date created: 14/08/2022
     */
    void setActiveStatus(Integer id, Integer status);

    /**
     * Created by: TuanHD
     * Date created: 16/08/2022
     * Function: existsCode
     */
    Boolean existsCode(String code);

    /**
     * Created by: TuanHD
     * Date created: 16/08/2022
     * Function: existsLocation
     */
    Boolean existsLocation(String location);

}



