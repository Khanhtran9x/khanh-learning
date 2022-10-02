package internet.com.services.employee;

import internet.com.entity.employee.Position;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IPositionService {
    /**
     *Create by LongNB
     * Date create: 09/08/2022
     * function: findAll position
     */
    List<Position> positionList();
}
