package internet.com.services.record.impl;

import internet.com.dto.user_dto.response.JWTResponseCustomer;
import internet.com.entity.computer.Computer;
import internet.com.entity.record.Record;
import internet.com.repository.computer_repo.IComputerRepository;
import internet.com.repository.record_repo.IRecordRepository;
import internet.com.services.computer.IComputerService;
import internet.com.services.customer.ICustomerService;
import internet.com.services.record.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class RecordService implements IRecordService {

    private String startTime;
    Integer remainingTime = 0;
    @Autowired
    IRecordRepository iRecordRepository;
    @Autowired
    ICustomerService iCustomerService;
    @Autowired
    IComputerService iComputerService;
    @Autowired
    IComputerRepository iComputerRepository;

    /**
     * Create by: HoangHN
     * Date Create: 15/08/2022
     * funtion: create Record
     *
     * @param customerId
     * @return
     */
    @Override
    public JWTResponseCustomer createRecord(Integer customerId) {
        remainingTime = iCustomerService.getRemainingTime(customerId); //2022-04-01 20:15:15
        startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        Calendar dateTime = Calendar.getInstance();
        dateTime.add(Calendar.SECOND, remainingTime);
        String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTime.getTime());

        List<Computer> computerList = iComputerService.findUnusedComputer();
        Integer computerId = computerList.get(0).getId();
        iComputerService.setActiveStatus(computerList.get(0).getId(), 0);

        iRecordRepository.createRecord(startTime, endTime,
                computerId, customerId);
        List<Record> recordList = getListRecordByCustomerId(customerId);
        JWTResponseCustomer jwtResponseCustomer = new JWTResponseCustomer();
        jwtResponseCustomer.setComputerInUse(computerList.get(0).getId());
        jwtResponseCustomer.setMessage("Đăng nhập thành công");
        jwtResponseCustomer.setStartTime(startTime);
        jwtResponseCustomer.setEndTime(endTime);
        jwtResponseCustomer.setComputerId(computerList.get(0).getId());
        jwtResponseCustomer.setRecordId(recordList.get(recordList.size() - 1).getId());

        return jwtResponseCustomer;

    }

    /**
     * Create by: HoangHN
     * Date Create: 15/08/2022
     * funtion: set EndTime
     *
     * @param id
     * @param endTime
     */
    @Override
    public void setEndTime(Integer id, String endTime) {
        iRecordRepository.setEndTime(id, endTime);
    }

    /**
     * Create by: HoangHN
     * Date Create: 15/08/2022
     * funtion: get List Record By Customer Id for login
     *
     * @param id
     * @return
     */
    @Override
    public List<Record> getListRecordByCustomerId(Integer id) {
        return iRecordRepository.getListRecordByCustomerId(id);
    }

    /**
     * Create by: DuyNT
     * Date Create: 15/08/2022
     * funtion: get Record By record Id in DB
     *
     * @param id
     * @return Record
     */
    @Override
    public Record findById(Integer id) {
        return this.iRecordRepository.getRecordByRecordId(id);
    }


}