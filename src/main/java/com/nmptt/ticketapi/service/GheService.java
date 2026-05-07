package com.nmptt.ticketapi.service;

import com.nmptt.ticketapi.dto.request.GheRequest;
import com.nmptt.ticketapi.dto.response.GheResponse;

import java.util.List;

public interface GheService {
    List<GheResponse> getAllGhe();
    GheResponse getGheById(Integer id);
    GheResponse createGhe(GheRequest gheRequest);
    GheResponse updateGhe(GheRequest gheRequest);
    void deleteGhe(Integer id);
    List<GheResponse> searchGhe(String keyword);
}