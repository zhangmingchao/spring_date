import com.wlqk.core.dao.CustomerDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description:
 * @Author: zc
 * @CreateDate: 2019/2/19$ 22:47$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JPQLDemo {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void findAllCustomer(){
        customerDao.findAllCustomer().forEach(System.out::println);
    }

    @Test
    public void updateCustomer(){
        //增删改方法，如果不再接口中加入Transactionl 注解，就这调用方法上加上Transactionl 和 commit注解
        customerDao.updateCustomer("李思源",2L);
    }

    @Test   //使用sql语句的查询
    public void findSQL(){
        customerDao.findSql().forEach(System.out::println);
    }

}
