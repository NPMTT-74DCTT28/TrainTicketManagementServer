package com.nmptt.ticketapi.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ToaTauResponse {
    private Integer id;
    private String maToa;
    private Integer idTau;
    private Integer idLoaiToa;
}