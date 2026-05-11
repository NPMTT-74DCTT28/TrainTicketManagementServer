package com.nmptt.ticketapi.dto.request;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private int id;
    private String oldPassword;
    private String newPassword;
}
