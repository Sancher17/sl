package com.cafe.api.services;

import com.cafe.api.dao.GenericDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IService <T> {

   void add(T t);

   void update(T t);

   void delete(T  t);

   T getById(Long id);

   List<T> getAll();

}
