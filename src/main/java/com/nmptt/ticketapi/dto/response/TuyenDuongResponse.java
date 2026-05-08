package com.nmptt.ticketapi.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class TuyenDuongResponse {
    private Integer id;
    private String maTuyen;
    private String tenTuyen;
    private Integer idGaDi;
    private Integer idGaDen;
    private Integer khoangCachKm;
    private BigDecimal giaCoBan;
}