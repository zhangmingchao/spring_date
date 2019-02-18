import com.wlqk.core.pojo.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @Description:
 * @Author: zc
 * @CreateDate: 2019/2/18$ 17:41$
 */
public class Demo1 {
    public static void main(String[] args) {
        //创建EntityManagerFactory，管理实体类管理器
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myJpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //得到事务管理器
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
        //创建一个Customer对象
        Customer customer = new Customer();
        customer.setCustAddress("燕ren");
        customer.setCustLevel("3");
        customer.setCustSource("4758");
        customer.setCustName("张翼德");
        entityManager.persist(customer);
        System.out.println(customer.getCustId());
        //关闭事务
        transaction.commit();
    }
}
