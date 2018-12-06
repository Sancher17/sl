package com.cafe.controllers.reports;


import com.cafe.api.services.IReportService;
import com.cafe.api.services.IStorageService;
import com.cafe.dto.storage.StorageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    IReportService reportService;

    @Autowired
    IStorageService storageService;


    @GetMapping()
    public Double getSumOfSalesByPeriod(
            @RequestParam(value = "startDate")@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
            @RequestParam(value = "endDate")@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime endDate) {
        return reportService.getSumOfSalesByPeriod(startDate, endDate);
    }

    @GetMapping("/name/{name}")
    public String handler(@PathVariable("name") String name) {
        return name.toUpperCase();
    }


    @GetMapping(value = "/all")
    public List<StorageDto> getAll() {
        return storageService.getAll()
                .stream().map(StorageDto::new)
                .collect(Collectors.toList());
    }




}
