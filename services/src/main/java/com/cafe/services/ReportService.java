package com.cafe.services;

import com.cafe.api.dao.IGoodsDao;
import com.cafe.api.dao.IOrderDao;
import com.cafe.api.dao.IStorageDao;
import com.cafe.api.services.IReportService;
import com.cafe.model.Goods;
import com.cafe.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class ReportService implements IReportService {

    @Autowired
    private IOrderDao orderDao;

    @Autowired
    private IGoodsDao goodsDao;

    @Autowired
    private IStorageDao storageDao;

    @Override
    public Double getSumOfSalesByPeriod(LocalDateTime start, LocalDateTime end) {
        List<Order> orders = orderDao.getListByPeriod(start, end);
        return orders.stream().flatMap(order -> order.getGoods()
                .stream()).mapToDouble(Goods::getSellPrice).sum();
    }

    @Override
    public Integer getQuantitySoldGoods(Long id) {
        List<Order> orders = orderDao.getAll();
//        Integer sum = orders.stream()
//                .flatMap(order -> order.getGoods().stream().
        return null;
    }



}
