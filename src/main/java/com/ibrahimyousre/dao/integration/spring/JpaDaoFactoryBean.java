/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibrahimyousre.dao.integration.spring;

import com.ibrahimyousre.dao.Dao;
import com.ibrahimyousre.dao.generation.SmartDaoFactory;
import com.ibrahimyousre.dao.integration.jpa.JpaGeneralDao;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.GenericTypeResolver;

/**
 *
 * @author Ibrahim Yousre (ib.yousre@gmail.com)
 */
class JpaDaoFactoryBean implements FactoryBean, ApplicationContextAware {

    private final Class daoClass;
    private final Class entityClass;

    private ApplicationContext applicationContext;

    public JpaDaoFactoryBean(Class daoClass) {
        this.daoClass = daoClass;
        Class<?>[] typeArguments = GenericTypeResolver
                .resolveTypeArguments(daoClass, Dao.class);
        this.entityClass = typeArguments[0];
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object getObject() throws Exception {
        JpaGeneralDao jpaGeneralDao = applicationContext.getBean(JpaGeneralDao.class);
        jpaGeneralDao.setEntityClass(entityClass);
        return SmartDaoFactory.create(daoClass, jpaGeneralDao);
    }

    @Override
    public Class getObjectType() {
        return daoClass;
    }

}
