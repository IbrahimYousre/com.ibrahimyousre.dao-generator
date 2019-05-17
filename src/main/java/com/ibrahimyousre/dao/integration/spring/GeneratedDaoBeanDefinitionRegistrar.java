/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibrahimyousre.dao.integration.spring;

import com.ibrahimyousre.dao.integration.jpa.JpaGeneralDao;
import com.ibrahimyousre.dao.integration.jpa.JpaGeneralDaoImpl;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 *
 * @author Ibrahim Yousre (ib.yousre@gmail.com)
 */
class GeneratedDaoBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes annotationAttributes = new AnnotationAttributes(
                metadata.getAnnotationAttributes(EnableDaoGeneration.class.getName()));
        registerJpaGeneralImplementation(registry);
        registerDaos(annotationAttributes.getClassArray("daos"), registry);
    }

    private void registerJpaGeneralImplementation(BeanDefinitionRegistry registry)
            throws BeanDefinitionStoreException {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(JpaGeneralDaoImpl.class);
        beanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        registry.registerBeanDefinition(JpaGeneralDao.class.getSimpleName(), beanDefinition);
    }

    private void registerDaos(Class<?>[] daos, BeanDefinitionRegistry registry)
            throws BeanDefinitionStoreException {
        for (Class<?> daoClass : daos) {
            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanClass(JpaDaoFactoryBean.class);
            ConstructorArgumentValues constructorArgumentValues
                    = new ConstructorArgumentValues();
            constructorArgumentValues.addGenericArgumentValue(daoClass);
            beanDefinition.setConstructorArgumentValues(constructorArgumentValues);
            registry.registerBeanDefinition(daoClass.getSimpleName(), beanDefinition);
        }
    }

}
