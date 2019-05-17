/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibrahimyousre.dao.generation;

import com.ibrahimyousre.dao.CrudDao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ibrahim Yousre (ib.yousre@gmail.com)
 * @param <Entity> entity type
 * @param <Id> id type
 */
public interface GeneralDao<Entity, Id extends Serializable> extends CrudDao<Entity, Id> {

    List<Entity> findByAttributes(Map<String, Object> attributes);
}
