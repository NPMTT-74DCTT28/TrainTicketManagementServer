package com.nmptt.ticketapi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToaTauRequest {
    private Integer id;
    private String maToa;
    private Integer idTau;
    private Integer idLoaiToa;
}