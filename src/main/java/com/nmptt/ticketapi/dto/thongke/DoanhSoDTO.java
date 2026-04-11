package com.nmptt.ticketapi.dto.thongke;

import lombok.Data;

@Data
public class DoanhSoDTO {
    private String maNhanVien;
    private String hoTen;
    private int soVeBan;
    private double doanhSo;
}
