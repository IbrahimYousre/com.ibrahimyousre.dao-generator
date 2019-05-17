/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibrahimyousre.dao.generation;

import com.ibrahimyousre.dao.CrudDao;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * @author Ibrahim Yousre (ib.yousre@gmail.com)
 */
public class SmartDaoFactory {

    public static <T, K extends Serializable, D extends CrudDao<T, K>> D create(Class<D> daoClass,
            GeneralDao<T, K> generalDao) {
        return (D) Proxy.newProxyInstance(daoClass.getClassLoader(),
                new Class[]{daoClass},
                new DaoInvocationHandler(generalDao));
    }

    static class DaoInvocationHandler implements InvocationHandler {

        private final DaoMethodStrategyFactory methodStrategyFactory;

        private DaoInvocationHandler(GeneralDao genericDaoImpl) {
            this.methodStrategyFactory = new DaoMethodStrategyFactory(genericDaoImpl);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            DaoMethodStrategy methodStrategy
                    = methodStrategyFactory.getDaoMethodStrategy(method, args);
            return methodStrategy.execute(args);
        }
    }
}
