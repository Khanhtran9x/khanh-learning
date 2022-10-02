package internet.com.repository.statistic;

import internet.com.dto.statistic_dto.StatisticByAccount;
import internet.com.dto.statistic_dto.StatisticByComputer;
import internet.com.dto.statistic_dto.StatisticByMonth;
import internet.com.entity.record.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface StatisticRepository extends JpaRepository<Record, Integer> {
    /**
     * Created by: HoangLH
     * Date created: 10/8/2022
     * function: Get value statistic by computer from Database to return value.
     *
     * @return
     */
    @Query(nativeQuery = true, value = "select sum(hour((timediff(start_time, end_time)))) as hour," +
            " computer_code as computer" +
            " from record join computer" +
            " on record.computer_id = computer.id " +
            " where (start_time > date(:startTime)) and (end_time < date(:endTime))" +
            " group by computer_id")
    List<StatisticByComputer> getStatisticByComputer(String startTime, String endTime);

    /**
     * Created by: HoangLH
     * Date created: 10/8/2022
     * function: Get value statistic by month from Database to return value.
     *
     * @return
     */
    @Query(nativeQuery = true,
            value = " select sv1.month, sv1.computer as computer, ifnull(sv2.service, 0) as service," +
                    " sv1.computer +ifnull(sv2.service, 0) as total from" +
                    " (select concat(month(start_time),'/', year(start_time)) as month," +
                    " sum(prices*amount) computer from payment_detail\n" +
                    " join product on payment_detail.product_id = product.id\n" +
                    " join payment on payment.id = payment_detail.payment_id\n" +
                    " join record on payment.record_id = record.id\n" +
                    " join computer on record.computer_id = computer.id\n" +
                    " join customer on record.customer_id = customer.id\n" +
                    " join `user` on customer.user_name = `user`.user_name" +
                    " join product_category on product.id_product_category = product_category.id\n" +
                    " where (product_category.id = 2 and ((start_time > date(:startTime))" +
                    " and (end_time < date(:endTime))))\n" +
                    " group by month) sv1 left join" +
                    " (select concat(month(start_time),'/', year(start_time)) as month," +
                    " sum(prices*amount) service from payment_detail\n" +
                    " join product on payment_detail.product_id = product.id\n" +
                    " join payment on payment.id = payment_detail.payment_id\n" +
                    " join record on payment.record_id = record.id\n" +
                    " join computer on record.computer_id = computer.id\n" +
                    " join customer on record.customer_id = customer.id\n" +
                    " join `user` on customer.user_name = `user`.user_name\n" +
                    " join product_category on product.id_product_category = product_category.id\n" +
                    " where (product_category.id != 2 and ((start_time > date(:startTime))" +
                    " and (end_time < date(:endTime))))\n" +
                    " group by month) sv2\n" +
                    " on sv1.month = sv2.month\n" +
                    " union\n" +
                    " select sv2.month, ifnull(sv1.computer, 0) as computer, sv2.service as service," +
                    " ifnull(sv1.computer, 0) + sv2.service as total from" +
                    " (select concat(month(start_time),'/', year(start_time)) as month," +
                    " sum(prices*amount) computer from" +
                    " payment_detail\n" +
                    " join product on payment_detail.product_id = product.id\n" +
                    " join payment on payment.id = payment_detail.payment_id\n" +
                    " join record on payment.record_id = record.id\n" +
                    " join computer on record.computer_id = computer.id\n" +
                    " join customer on record.customer_id = customer.id\n" +
                    " join `user` on customer.user_name = `user`.user_name\n" +
                    " join product_category on product.id_product_category = product_category.id\n" +
                    " where (product_category.id = 2 and ((start_time > date(:startTime))" +
                    " and (end_time < date(:endTime))))\n" +
                    " group by month) sv1 right join" +
                    " (select concat(month(start_time),'/', year(start_time)) as month," +
                    " sum(prices*amount) service from" +
                    " payment_detail\n" +
                    " join product on payment_detail.product_id = product.id\n" +
                    " join payment on payment.id = payment_detail.payment_id\n" +
                    " join record on payment.record_id = record.id\n" +
                    " join computer on record.computer_id = computer.id\n" +
                    " join customer on record.customer_id = customer.id\n" +
                    " join `user` on customer.user_name = `user`.user_name\n" +
                    " join product_category on product.id_product_category = product_category.id\n" +
                    " where (product_category.id != 2 and ((start_time > date(:startTime))" +
                    " and (end_time < date(:endTime))))\n" +
                    " group by month) sv2\n" +
                    " on sv1.month = sv2.month;")
    List<StatisticByMonth> getStatisticByMonth(String startTime, String endTime);

    /**
     * Created by: HoangLH
     * Date created: 12/8/2022
     * function: Get value statistic by month from Database to return value.
     *
     * @return
     */
    @Query(nativeQuery = true,
            value = " select `user`.user_name as account, sum(prices*amount) as revenue," +
                    " sum(hour((timediff(start_time, end_time)))) as hour from payment_detail\n" +
                    " join product on payment_detail.product_id = product.id\n" +
                    " join payment on payment.id = payment_detail.payment_id\n" +
                    " join record on payment.record_id = record.id\n" +
                    " join computer on record.computer_id = computer.id\n" +
                    " join customer on record.customer_id = customer.id\n" +
                    " join `user` on customer.user_name = `user`.user_name\n" +
                    " join product_category on product.id_product_category = product_category.id\n" +
                    " where (start_time > date(:startDate)) and (end_time < date(:endDate))" +
                    " group by account" +
                    " order by revenue" +
                    " limit 10")
    List<StatisticByAccount> getStatisticByAccount(String startDate, String endDate);
}
