package com.wlqk.core.pojo;

import javax.persistence.*;

/**
 * @Description:
 * @Author: zc
 * @CreateDate: 2019/2/18$ 15:05$
 */
@Table    //但前实体类和数据库中的表进行映射,如果当前实体类对应的表不存在，则创建的表就是当前实体类的名字,name属性指定对应的数据库中的表
@Entity   //声明当前类是一个实体类
public class Customer {

    @Id                     //表明当前字段是主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)       //声明主键的规则,使用数据库的主键自增
    private long custId;

    @Column               //column 中的name属性表明是数据库中的那一列 如果不配置，pojo的属性名和表的列名一致
    private String custName;

    @Column
    private String custSource;

    @Column
    private String custIndustry;

    @Column
    private String custLevel;

    @Column
    private String custAddress;
    @Column
    private String custPhone;

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custPhone='" + custPhone + '\'' +
                '}';
    }
}
