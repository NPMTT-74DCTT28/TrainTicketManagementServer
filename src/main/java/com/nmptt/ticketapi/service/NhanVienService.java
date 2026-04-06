package com.nmptt.ticketapi.service;

import com.nmptt.ticketapi.dto.request.NhanVienRequest;
import com.nmptt.ticketapi.dto.response.NhanVienResponse;

import java.util.List;

public interface NhanVienService {
    List<NhanVienResponse> getAllNhanVien();

    NhanVienResponse getNhanVienByMa(String maNhanVien);

    NhanVienResponse createNhanVien(NhanVienRequest nhanVienRequest);
}
