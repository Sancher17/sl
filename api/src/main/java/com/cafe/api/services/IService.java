package com.cafe.api.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IService <T> {

   List<T> getAll();

}
