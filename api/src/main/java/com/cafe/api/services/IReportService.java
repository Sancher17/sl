package com.cafe.api.services;

import java.time.LocalDateTime;

public interface IReportService {

    Double getSumOfSalesByPeriod(LocalDateTime start, LocalDateTime end);

}
