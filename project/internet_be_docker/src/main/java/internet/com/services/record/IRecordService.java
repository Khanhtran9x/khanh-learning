
package internet.com.services.record;

import internet.com.dto.user_dto.response.JWTResponseCustomer;
import internet.com.entity.record.Record;

import java.util.List;

public interface IRecordService {

    JWTResponseCustomer createRecord(Integer customerId);

    void setEndTime(Integer id, String endTime);

    List<Record> getListRecordByCustomerId(Integer id);

    Record findById(Integer id);
}

