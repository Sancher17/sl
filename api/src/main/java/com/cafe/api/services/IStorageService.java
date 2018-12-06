package com.cafe.api.services;

import com.cafe.model.Order;
import com.cafe.model.Storage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IStorageService extends IService<Storage>  {

    Storage getQuantityByName(String name);

}
