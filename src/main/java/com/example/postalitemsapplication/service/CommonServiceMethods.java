package com.example.postalitemsapplication.service;

import java.util.List;

public interface CommonServiceMethods<T> {

    List<T> list();

    T getByID(Integer id);

    T save(T object);

    void delete(Integer id);

}
