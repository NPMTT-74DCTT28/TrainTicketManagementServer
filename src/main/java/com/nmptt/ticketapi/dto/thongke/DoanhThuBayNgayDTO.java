package com.nmptt.ticketapi.dto.thongke;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DoanhThuBayNgayDTO {
    private LocalDate ngay;
    private double doanhThu;
}
