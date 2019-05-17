/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibrahimyousre.dao.generation;

import com.ibrahimyousre.dao.generation.strategies.DeleteByIdStrategy;
import com.ibrahimyousre.dao.generation.strategies.DeleteStrategy;
import com.ibrahimyousre.dao.generation.strategies.FindAllStrategy;
import com.ibrahimyousre.dao.generation.strategies.FindByIdStrategy;
import com.ibrahimyousre.dao.generation.strategies.GeneralFindStrategy;
import com.ibrahimyousre.dao.generation.strategies.GetCountStrategy;
import com.ibrahimyousre.dao.generation.strategies.InsertStrategy;
import com.ibrahimyousre.dao.generation.strategies.UpdateStrategy;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 *
 * @author Ibrahim Yousre (ib.yousre@gmail.com)
 */
public class DaoMethodStrategyFactory {

    private final GeneralDao generalDao;

    public DaoMethodStrategyFactory(GeneralDao generalDao) {
        this.generalDao = generalDao;
    }

    public DaoMethodStrategy getDaoMethodStrategy(Method method, Object... args) {
        switch (method.getName()) {
            case "insert":
                return new InsertStrategy(generalDao);
            case "update":
                return new UpdateStrategy(generalDao);
            case "delete":
                return new DeleteStrategy(generalDao);
            case "deleteById":
                return new DeleteByIdStrategy(generalDao);
            case "findById":
                return new FindByIdStrategy(generalDao);
            case "findAll":
                return new FindAllStrategy(generalDao);
            case "getCount":
                return new GetCountStrategy(generalDao);
            default:
                if (!method.getName().startsWith("findBy")) {
                    String msg = "Can't provide this operation: " + method.toString();
                    throw new UnsupportedOperationException(msg);
                }
                boolean isSingleResult = !Collection.class.isAssignableFrom(method.getReturnType());
                return new GeneralFindStrategy(generalDao, method.getName(), isSingleResult);
        }
    }
}
