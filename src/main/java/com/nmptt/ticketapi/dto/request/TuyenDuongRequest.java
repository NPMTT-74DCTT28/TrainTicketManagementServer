package com.nmptt.ticketapi.dto.request;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class TuyenDuongRequest {
    private Integer id;
    private String maTuyen;
    private String tenTuyen;
    private Integer idGaDi;
    private Integer idGaDen;
    private Integer khoangCachKm;
    private BigDecimal giaCoBan;
}