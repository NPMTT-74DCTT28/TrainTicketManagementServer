package com.nmptt.ticketapi.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LichTrinhRequest {
    private Integer id;
    private String maLichTrinh;
    private Integer idTau;
    private Integer idTuyenDuong;
    private LocalDateTime ngayDi;
    private LocalDateTime ngayDen;
    private String trangThai;
}
