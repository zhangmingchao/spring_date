package com.wlqk.core.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @Description:
 * @Author: zc
 * @CreateDate: 2019/2/18$ 18:49$
 */
public class JpaUtil_1 {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");

    public EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
