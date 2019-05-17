/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibrahimyousre.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Ibrahim Yousre (ib.yousre@gmail.com)
 * @param <Entity> entity type
 * @param <Id> id type
 */
public interface CrudDao<Entity, Id extends Serializable> extends Dao<Entity, Id> {

    void insert(Entity obj);

    void update(Entity obj);

    void delete(Entity obj);

    void deleteById(Id id);

    Entity findById(Id id);

    List<Entity> findAll();

    long getCount();
}
