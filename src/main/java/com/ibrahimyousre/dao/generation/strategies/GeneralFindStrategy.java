/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibrahimyousre.dao.generation.strategies;

import com.ibrahimyousre.dao.generation.DaoMethodStrategy;
import com.ibrahimyousre.dao.generation.GeneralDao;
import com.ibrahimyousre.utils.MapUtils;
import java.util.List;

/**
 *
 * @author Ibrahim Yousre (ib.yousre@gmail.com)
 */
public class GeneralFindStrategy implements DaoMethodStrategy {

    private final GeneralDao generalDao;
    private final String[] attributes;
    private final boolean isSingleResult;

    public GeneralFindStrategy(GeneralDao generalDao, String queryString, boolean isSingleResult) {
        this.generalDao = generalDao;
        attributes = queryString.substring("findBy".length()).split("And");
        for (int i = 0; i < attributes.length; i++) {
            attributes[i] = Character.toLowerCase(attributes[i].charAt(0))
                    + attributes[i].substring(1);
        }
        this.isSingleResult = isSingleResult;
    }

    @Override
    public Object execute(Object... args) {
        List result = generalDao.findByAttributes(MapUtils.zip(attributes, args));
        if (isSingleResult) {
            return result.get(0);
        } else {
            return result;
        }
    }

}
