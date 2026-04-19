package com.nmptt.ticketapi.service;

import com.nmptt.ticketapi.entity.LoaiToa;

import java.math.BigDecimal;
import java.util.List;

public interface LoaiToaService {
    List<LoaiToa> getAllLoaiToa();

    LoaiToa getLoaiToaById(Integer id);

    LoaiToa insertLoaiToa(LoaiToa loaiToa);

    LoaiToa updateLoaiToa(LoaiToa loaiToa);

    void delete(Integer id);

    List<LoaiToa> searchLoaiToa(String tenLoai, BigDecimal heSoGia);
}