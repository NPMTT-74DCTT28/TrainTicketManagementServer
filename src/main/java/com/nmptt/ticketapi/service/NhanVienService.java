package com.nmptt.ticketapi.service;

import com.nmptt.ticketapi.dto.request.ChangePasswordRequest;
import com.nmptt.ticketapi.dto.request.LoginRequest;
import com.nmptt.ticketapi.dto.request.NhanVienRequest;
import com.nmptt.ticketapi.dto.response.NhanVienResponse;

import java.util.List;

public interface NhanVienService {
    List<NhanVienResponse> getAllNhanVien();

    NhanVienResponse getNhanVienById(int id);

    NhanVienResponse createNhanVien(NhanVienRequest nhanVienRequest);

    NhanVienResponse updateNhanVien(NhanVienRequest nhanVienRequest);

    void deleteNhanVien(int id);

    NhanVienResponse login(LoginRequest request);

    void changePassword(ChangePasswordRequest request);

    List<NhanVienResponse> searchNhanVien(String keyword, String gioiTinh, String vaiTro);
}
