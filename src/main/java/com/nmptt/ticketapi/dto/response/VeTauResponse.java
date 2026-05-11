package com.nmptt.ticketapi.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VeTauResponse {
    private Integer id;
    private String maVe;
    private Integer idKhachHang;
    private Integer idLichTrinh;
    private Integer idGhe;
    private Integer idNhanVien;
    private String ngayDatVe;
    private double giaVe;
    private String trangThai;
}
