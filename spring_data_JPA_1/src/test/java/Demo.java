import com.wlqk.core.dao.CustomerDao;
import com.wlqk.core.pojo.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * @Description:
 * @Author: zc
 * @CreateDate: 2019/2/19$ 20:46$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Demo {


    @Autowired
    private CustomerDao customerDao;

    @Test  //查找一个数据
    public void findOne(){
        //org.hibernate.LazyInitializationException: could not initialize proxy [com.wlqk.core.pojo.Customer#1] - no Session
        //使用EntityManager的find方法,是立即加载
        //Customer customer = customerDao.findById(6l).get();
        //Customer customer = customerDao.getOne(1L);
        Customer customer1 = customerDao.findById(1L).get();
        System.out.println(customer1);
        System.out.println(customerDao.getOne(1L));//懒加载报错
    }

    @Test  //添加数据
    public void testSave(){
        Customer customer = new Customer();
        customer.setCustName("离线嗯");
        customer.setCustAddress("山东");
        customer.setCustIndustry("土匪");
        customer.setCustLevel("vip");
        customerDao.save(customer);
    }

    @Test   //更新数据
    public void testUpdate(){
        Customer customer = customerDao.findById(1L).get();
        customer.setCustName("武松");
        customer.setCustAddress("清河县");
        customerDao.save(customer);
    }

    @Test
    public void testDelete(){
        Customer customer = new Customer();
        customer.setCustId(1L);
        customerDao.delete(customer);
    }

    @Test  //查询所有
    public void findAll(){
        List<Customer> list = customerDao.findAll();
        list.forEach(System.out::println);
    }

    @Test  //统计查询
    public void testCount(){
        long count = customerDao.count();
        System.out.println(count);
    }

    @Test  //判断数据是否存在
    public void testExit(){
        boolean b = customerDao.existsById(2L);
        System.out.println(b);
    }

    @Test  //分页查询数据
    public void testPage(){
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Customer> daoAll = customerDao.findAll(pageRequest);
        List<Customer> content = daoAll.getContent();
        content.forEach(System.out::println);
    }

    @Test  //排序
    public void testSort(){
        Sort sort = new Sort(Sort.Direction.DESC,"custId");
        customerDao.findAll(sort).forEach(System.out::println);
    }

    @Test  //分页排序
    public void testSOrtPage(){
        Sort sort = new Sort(Sort.Direction.DESC,"custId");
        PageRequest pageRequest = PageRequest.of(0, 1);
        customerDao.findAll(pageRequest).forEach(System.out::println);
    }

}
