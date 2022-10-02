package internet.com.services.statistic.impl;

import internet.com.dto.statistic_dto.StatisticByAccount;
import internet.com.dto.statistic_dto.StatisticByComputer;
import internet.com.dto.statistic_dto.StatisticByMonth;
import internet.com.repository.statistic.StatisticRepository;
import internet.com.services.statistic.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    StatisticRepository statisticRepository;

    /**
     * Created by: HoangLH
     * Date created: 10/8/2022
     * function: Get value statistic by computer from Database to return value.
     *
     * @return
     */
    @Override
    public List<StatisticByComputer> getStatisticByComputer(String startDate, String endDate) {
        return statisticRepository.getStatisticByComputer(startDate, endDate);
    }

    /**
     * Created by: HoangLH
     * Date created: 10/8/2022
     * function: Get value statistic by month from Database to return value.
     *
     * @return
     */
    @Override
    public List<StatisticByMonth> getStatisticByMonth(String startDate, String endDate) {
        return statisticRepository.getStatisticByMonth(startDate, endDate);
    }
    /**
     * Created by: HoangLH
     * Date created: 10/8/2022
     * function: Get value statistic by month from Database to return value.
     *
     * @return
     */
    @Override
    public List<StatisticByAccount> getStatisticByAccount(String startDate, String endDate) {
        return statisticRepository.getStatisticByAccount(startDate, endDate);
    }
}
