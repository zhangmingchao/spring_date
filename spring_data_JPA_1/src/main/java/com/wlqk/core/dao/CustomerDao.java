package com.wlqk.core.dao;

import com.wlqk.core.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: zc
 * @CreateDate: 2019/2/19$ 21:07$
 */
public interface CustomerDao extends JpaRepository<Customer,Long> , JpaSpecificationExecutor<Customer> {

    //@Query 使用jpql的方式查询。
    @Query(value="from Customer")
    public List<Customer> findAllCustomer();

    //@Query 使用jpql的方式查询。?1代表参数的占位符，其中1对应方法中的参数索引
    //JDBC style parameters (?) are not supported for JPA queries. ？号旁边必须添加所以
    @Query(value="from Customer where custName = ?1")
    public Customer findCustomer(String custName);

    @Query(value="update Customer set custName = ?1 where custId = ?2")
    @Modifying   //不加这个注解org.hibernate.hql.internal.QueryExecutionRequestException: Not supported for DML operations
    @Transactional  //不加这个注解报的错误javax.persistence.TransactionRequiredException: Executing an update/delete query
    public void updateCustomer(String custName,Long custId);


    /**
     * nativeQuery : 使用本地sql的方式查询
     */
    @Query(value="select * from cst_customer",nativeQuery=true)
    public List<Customer> findSql();
}
