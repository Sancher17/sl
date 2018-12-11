package com.cafe.controllers.reports;


import com.cafe.api.services.IGoodsService;
import com.cafe.api.services.IOrderService;
import com.cafe.api.services.IReportService;
import com.cafe.api.services.IStorageService;
import com.cafe.dto.orders.OrderDto;
import com.cafe.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    IReportService reportService;
    @Autowired
    IStorageService storageService;
    @Autowired
    IGoodsService goodsService;
    @Autowired
    IOrderService orderService;

    @GetMapping("/sum")
    public String getSumOfSalesByPeriod(
            @RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate startDate,
            @RequestParam("endDate")  @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endDate) {
        Double sum = reportService.getSumOfSalesByPeriod(startDate.atStartOfDay(), endDate.atStartOfDay());
        return format("Сумма продаж за период c %s по %s , сумма: %f", startDate, endDate, sum);
    }

    @GetMapping("/goods")
    public String handler(@RequestParam("id") Long id) {
        int quantity;
        quantity = reportService.getQuantitySoldGoods(id);
        Goods goods = goodsService.getById(id);
        return format("Количество: %d проданных товаров: %s ", quantity, goods.getNameGoods());
    }

    @GetMapping("/user")
    public List<OrderDto> getListOrdersForUser(@RequestParam("id") Long id){
        return orderService.getListOrdersForUser(id).stream()
                .map(OrderDto::new).collect(Collectors.toList());
    }



}
