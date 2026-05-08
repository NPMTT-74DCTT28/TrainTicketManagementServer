package com.nmptt.ticketapi.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VeTauRequest {
    private int id;
    private String maVe;
    private int idKhachHang;
    private int idLichTrinh;
    private int idGhe;
    private int idNhanVien;
    private LocalDateTime ngayDatVe;
    private double giaVe;
    private String trangThai;
}
