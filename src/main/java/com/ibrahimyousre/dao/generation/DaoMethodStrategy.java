/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibrahimyousre.dao.generation;

/**
 *
 * @author Ibrahim Yousre (ib.yousre@gmail.com)
 */
public interface DaoMethodStrategy {

    Object execute(Object... args);
}
