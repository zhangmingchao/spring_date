package com.wlqk.core.test;

import com.wlqk.core.pojo.Customer;
import com.wlqk.core.utils.JpaUtil;
import com.wlqk.core.utils.JpaUtil_1;
import org.hibernate.usertype.CompositeUserType;
import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Currency;
import java.util.List;

/**
 * @Description:  使用Jpa进行curd操作
 * @Author: zc
 * @CreateDate: 2019/2/18$ 18:48$
 */
public class Demo2 {
    @Test  //添加数据
    public void addCustomer(){
        Customer customer = new Customer();
        customer.setCustName("宋徽宗");
        customer.setCustLevel("1");
        customer.setCustAddress("东京汴梁");
        EntityManager entityManager = JpaUtil.getEntityManager();
        //事务管理器
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            entityManager.persist(customer);
            System.out.println(customer.getCustId());
            //提交事务
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
    }

    @Test //改
    public void update(){
        //先查在该
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            //先查在gai
            Customer customer = entityManager.find(Customer.class, 1L);
            customer.setCustAddress("河北石家庄");
            Customer merge = entityManager.merge(customer);
            System.out.println(merge);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
    }

    @Test  //查询数据
    public void find(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        Customer customer = entityManager.find(Customer.class, 1L);
        //System.out.println(customer);
    }

    @Test
    public void getReference(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        Customer reference = entityManager.getReference(Customer.class, 1L);
        System.out.println("------------");
        System.out.println(reference);
    }

    @Test  //删
    public void remove(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            //先查询在删除
            Customer customer = entityManager.find(Customer.class, 1L);
            entityManager.remove(customer);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Test  //查询全部
    public void findAll(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        Query query = entityManager.createQuery("from Customer ");
        List<Customer> resultList = query.getResultList();
        resultList.forEach(System.out::println);
    }

    @Test  //分页查询
    public void findPage(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        Query query = entityManager.createQuery("from Customer ");
        query.setFirstResult(1);   //first 从0开始
        query.setMaxResults(2);
        List resultList = query.getResultList();
        resultList.forEach(System.out::println);
    }

    @Test   //待条件查询
    public void findCriteria(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        Query query = entityManager.createQuery("from Customer where custName like ?"); //不能书写成Customer.custName
        query.setParameter(1,"%师%");
        List resultList = query.getResultList();
        resultList.forEach(System.out::println);
    }
    @Test  //排序查询
    public void findBySolr(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        Query query = entityManager.createQuery("from Customer order by ? desc"); //orderBy 不需要where
        query.setParameter(1,"custId");  //列名
        List resultList = query.getResultList();
        resultList.forEach(System.out::println);
    }

    @Test
    public void findByIn(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        Query query = entityManager.createQuery("from Customer where custId in (2,3,4)");
        List resultList = query.getResultList();
        resultList.forEach(System.out::println);
    }

    @Test   //统计查询
    public void findCount(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        Query query = entityManager.createQuery("select count(*) from Customer where custId > 2");
        List resultList = query.getResultList();
        resultList.forEach(System.out::println);
    }

    @Test
    public void findCustName(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        Query query = entityManager.createQuery("select custName from Customer");
        List resultList = query.getResultList();
        resultList.forEach(System.out::println);
    }


    //使用jpql修改用户名
    @Test
    public void updateByJpql(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            Query query = entityManager.createQuery("update Customer  set custAddress = ? where custName = ?");
            query.setParameter(1,"河北邢台");
            query.setParameter(2,"李师师");
            int i = query.executeUpdate();
            System.out.println(i);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
    }

    //使用jpql删除用户名
    @Test
    public void deleteByJpql(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            Query query = entityManager.createQuery("delete Customer where custId = ?");
            query.setParameter(1,3L);
            int i = query.executeUpdate();
            System.out.println(i);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
    }

    //使用sql语句进行增删改查
    @Test
    public void sqlQueryById(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        Query nativeQuery = entityManager.createNativeQuery("select * from customer where custId = ?",Customer.class);
        nativeQuery.setParameter(1,4L);
        Object singleResult = nativeQuery.getSingleResult();
        System.out.println(singleResult);
    }

    @Test
    public void sqlQueryByName(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        Query nativeQuery = entityManager.createNativeQuery("select * from customer where custName like ? ",Customer.class);
        nativeQuery.setParameter(1,"%师%");
        List<Customer> resultList = nativeQuery.getResultList();
        resultList.forEach(System.out::println);
    }

    @Test
    public void sqlUpdate(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            Query nativeQuery = entityManager.createNativeQuery("update Customer set custName = '赵梓山' where custId = ?");
            nativeQuery.setParameter(1,4L);
            int i = nativeQuery.executeUpdate();
            System.out.println(i);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
    }
    @Test
    public void sqlDelete(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            Query nativeQuery = entityManager.createNativeQuery("delete from customer where custId = ?");
            nativeQuery.setParameter(1,4L);
            int i = nativeQuery.executeUpdate();
            System.out.println(i);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
    }
}
