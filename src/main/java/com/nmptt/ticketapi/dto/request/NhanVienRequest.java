package com.nmptt.ticketapi.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NhanVienRequest {
    private int id;
    private String maNhanVien;
    private String matKhau;
    private String hoTen;
    private LocalDate ngaySinh;
    private String gioiTinh;
    private String sdt;
    private String email;
    private String diaChi;
    private String vaiTro;
}
