package com.nmptt.ticketapi.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class NhanVienResponse {
    private String maNhanVien;
    private String hoTen;
    private LocalDate ngaySinh;
    private String gioiTinh;
    private String sdt;
    private String email;
    private String vaiTro;
}
