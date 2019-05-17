/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibrahimyousre.dao.generation.strategies;

import com.ibrahimyousre.dao.generation.DaoMethodStrategy;
import com.ibrahimyousre.dao.generation.GeneralDao;

/**
 *
 * @author Ibrahim Yousre (ib.yousre@gmail.com)
 */
public class FindAllStrategy implements DaoMethodStrategy {

    private final GeneralDao generalDao;

    public FindAllStrategy(GeneralDao generalDao) {
        this.generalDao = generalDao;
    }

    @Override
    public Object execute(Object... args) {
        return generalDao.findAll();
    }

}
