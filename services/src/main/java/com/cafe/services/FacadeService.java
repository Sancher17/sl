package com.cafe.services;

import com.cafe.api.services.ICategoryService;
import com.cafe.api.services.IGoodsService;
import com.cafe.api.services.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FacadeService {

    @Autowired
    IGoodsService goodsService;

    @Autowired
    ICategoryService categoryService;

    @Autowired
    IUsersService usersService;

}
