package com.nmptt.ticketapi.service;

import com.nmptt.ticketapi.dto.request.TuyenDuongRequest;
import com.nmptt.ticketapi.dto.response.TuyenDuongResponse;

import java.util.List;

public interface TuyenDuongService {
    List<TuyenDuongResponse> getAllTuyenDuong();

    TuyenDuongResponse getTuyenDuongById(Integer id);

    TuyenDuongResponse createTuyenDuong(TuyenDuongRequest tuyenDuong);

    TuyenDuongResponse updateTuyenDuong(TuyenDuongRequest tuyenDuong);

    void deleteTuyenDuong(Integer id);

    List<TuyenDuongResponse> searchTuyenDuong(String search);
}
