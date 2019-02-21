package com.wlqk.core.teset;

import com.wlqk.core.dao.CustomerDao;
import com.wlqk.core.pojo.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    @Test
    public void findOne(){
        //使用EntityManager的find方法,是立即加载
        //Customer customer = customerDao.findById(6l).get();
        Customer customer = customerDao.getOne(6L);

        System.out.println(customer);
    }
}
