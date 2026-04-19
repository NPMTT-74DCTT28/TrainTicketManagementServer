package com.nmptt.ticketapi.repository;

import com.nmptt.ticketapi.entity.LoaiToa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LoaiToaRepository extends JpaRepository<LoaiToa, Integer> {
    List<LoaiToa> findByTenLoaiLikeOrHeSoGia(String tenLoai, BigDecimal heSoGia);

    boolean existsByTenLoaiAndIdNot(String tenLoai, Integer id);

    boolean existsByTenLoai(String tenLoai);
}