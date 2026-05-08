package com.nmptt.ticketapi.service;

import com.nmptt.ticketapi.dto.request.LichTrinhRequest;
import com.nmptt.ticketapi.dto.response.LichTrinhResponse;

import java.util.List;

public interface LichTrinhService {
    List<LichTrinhResponse> getAllLichTrinh();

    LichTrinhResponse getLichTrinhById(Integer id);

    LichTrinhResponse createLichTrinh(LichTrinhRequest lichTrinhRequest);

    LichTrinhResponse updateLichTrinh(LichTrinhRequest lichTrinhRequest);

    void deleteLichTrinh(Integer id);

    List<LichTrinhResponse> searchLichTrinh(String search);
}
