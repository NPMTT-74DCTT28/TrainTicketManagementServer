package com.nmptt.ticketapi.service;

import com.nmptt.ticketapi.dto.request.ToaTauRequest;
import com.nmptt.ticketapi.dto.response.ToaTauResponse;

import java.util.List;

public interface ToaTauService {
    List<ToaTauResponse> getAllToaTau();
    ToaTauResponse getToaTauById(Integer id);
    ToaTauResponse createToaTau(ToaTauRequest toaTauRequest);
    ToaTauResponse updateToaTau(ToaTauRequest toaTauRequest);
    void deleteToaTau(Integer id);
    List<ToaTauResponse> searchToaTau(String search);
}