package com.nmptt.ticketapi.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GheResponse {
    private Integer id;
    private String soGhe;
    private Integer idToaTau;
}
