package internet.com.services.computer;

import internet.com.entity.computer.ComputerType;

import java.util.List;

public interface IComputerTypeService {

    /**
     * Created by: TuanHD
     * Date created: 09/08/2022
     * Function: findAllComputerType
     */
    List<ComputerType> findAll();
}