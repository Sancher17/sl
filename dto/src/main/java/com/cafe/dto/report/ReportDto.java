package com.cafe.dto.report;

public class ReportDto {

    private Double summ;

    public ReportDto(Double sumOfSalesByPeriod) {

    }

    public Double getSumm() {
        return summ;
    }

    public void setSumm(Double summ) {
        this.summ = summ;
    }
}
