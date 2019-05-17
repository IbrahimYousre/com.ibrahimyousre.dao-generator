/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibrahimyousre.dao.integration.jpa;

import com.ibrahimyousre.dao.generation.GeneralDao;
import java.io.Serializable;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ibrahim Yousre (ib.yousre@gmail.com)
 * @param <Entity> entity type
 * @param <Id> id type
 */
public interface JpaGeneralDao<Entity, Id extends Serializable> extends GeneralDao<Entity, Id> {

    @Transactional
    @Override
    void insert(Entity obj);

    @Transactional
    @Override
    void update(Entity obj);

    @Transactional
    @Override
    void delete(Entity obj);

    @Transactional
    @Override
    void deleteById(Id id);

    void setEntityClass(Class<Entity> clazz);
}
