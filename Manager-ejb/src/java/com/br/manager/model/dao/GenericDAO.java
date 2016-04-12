package com.br.manager.model.dao;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Lucas Brasilio dos Santos
 * @since 03-07-2016
 * @version 1.0
 * 
 */
public interface GenericDAO<E> extends Serializable {
    
    //CREATE
    public boolean insert(E e);
    //READ
    public ArrayList<E> read();
    //UPDATE
    public boolean update(int pk, E e);
    //DELETE
    public boolean delete(int pk);
    
}
