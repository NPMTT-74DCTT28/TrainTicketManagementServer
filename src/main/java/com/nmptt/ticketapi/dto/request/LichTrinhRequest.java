package com.nmptt.ticketapi.dto.request;

import com.nmptt.ticketapi.entity.Tau;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
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
