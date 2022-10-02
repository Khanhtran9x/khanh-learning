package internet.com.services.employee.impl;

import internet.com.entity.employee.Position;
import internet.com.repository.employee_repo.IPositionRepository;
import internet.com.services.employee.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PositionService implements IPositionService {
    @Autowired
    private IPositionRepository positionRepository;
    @Override
    public List<Position> positionList() {
        return positionRepository.findAll();
    }
}
