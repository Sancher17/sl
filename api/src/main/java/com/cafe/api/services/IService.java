package com.cafe.api.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IService <T> {

   void add(T t);

   void update(T t);

   void delete(Long id);

   T getById(Long id);

   List<T> getAll();

   T getByName(String name);
}
