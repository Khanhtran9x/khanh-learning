package internet.com.services.statistic;

import internet.com.dto.statistic_dto.StatisticByAccount;
import internet.com.dto.statistic_dto.StatisticByComputer;
import internet.com.dto.statistic_dto.StatisticByMonth;

import java.util.List;

public interface StatisticService {
    /**
     * Created by: HoangLH
     * Date created: 10/8/2022
     * function: Get value statistic by computer from Database to return value.
     *
     * @return
     */
    List<StatisticByComputer> getStatisticByComputer(String startDate, String endDate);
    /**
     * Created by: HoangLH
     * Date created: 10/8/2022
     * function: Get value statistic by month from Database to return value.
     *
     * @return
     */
    List<StatisticByMonth> getStatisticByMonth(String startDate, String endDate);
    /**
     * Created by: HoangLH
     * Date created: 12/8/2022
     * function: Get value statistic by account from Database to return value.
     *
     * @return
     */
    List<StatisticByAccount> getStatisticByAccount(String startDate, String endDate);
}
