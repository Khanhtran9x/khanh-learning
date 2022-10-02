package internet.com.controller;


import internet.com.dto.statistic_dto.StatisticByAccount;
import internet.com.dto.statistic_dto.StatisticByComputer;
import internet.com.dto.statistic_dto.StatisticByMonth;
import internet.com.services.statistic.StatisticService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("statistic")
@CrossOrigin(origins = "http://localhost:4200")
public class StatisticController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    StatisticService statisticService;

    /**
     * Created by: HoangLH
     * Date created: 10/8/2022
     * function: Get value statistic by computer from Database to return value.
     *
     * @return
     */
    @GetMapping("/by-computer/{startTime}/{endTime}")
    public ResponseEntity<List<StatisticByComputer>> getStatisticByComputer(@PathVariable String startTime, @PathVariable String endTime) {
        if (LocalDate.parse(startTime).plusDays(1).isAfter(LocalDate.parse(endTime))) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<StatisticByComputer> statisticByComputerList = statisticService.getStatisticByComputer(startTime, endTime);

        if (statisticByComputerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(statisticByComputerList, HttpStatus.OK);
    }

    /**
     * Created by: HoangLH
     * Date created: 10/8/2022
     * function: Get value statistic by month from Database to return value.
     *
     * @return
     */
    @GetMapping("by-month/{startTime}/{endTime}")
    public ResponseEntity<List<StatisticByMonth>> getStatisticByMonth(@PathVariable String startTime, @PathVariable String endTime) {
        if (LocalDate.parse(startTime).plusDays(1).isAfter(LocalDate.parse(endTime))) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<StatisticByMonth> statisticByMonthList = statisticService.getStatisticByMonth(startTime, endTime);
        if (statisticByMonthList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(statisticByMonthList, HttpStatus.OK);
    }

    /**
     * Created by: HoangLH
     * Date created: 11/8/2022
     * function: Get value statistic by account from Database to return value.
     *
     * @return
     */
    @GetMapping("by-account/{startTime}/{endTime}")
    public ResponseEntity<List<StatisticByAccount>> getStatisticByAccount(@PathVariable String startTime, @PathVariable String endTime) {
        if (LocalDate.parse(startTime).plusDays(1).isAfter(LocalDate.parse(endTime))) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<StatisticByAccount> statisticByAccounts = statisticService.getStatisticByAccount(startTime, endTime);
        if (statisticByAccounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(statisticByAccounts, HttpStatus.OK);
    }
}
