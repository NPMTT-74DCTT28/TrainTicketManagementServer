package com.nmptt.ticketapi.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String maNhanVien;
    private String matKhau;
}
