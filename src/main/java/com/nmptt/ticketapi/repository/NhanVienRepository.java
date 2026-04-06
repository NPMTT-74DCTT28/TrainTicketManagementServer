package com.nmptt.ticketapi.repository;

import com.nmptt.ticketapi.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
    Optional<NhanVien> findByMaNhanVien(String maNhanVien);

    Optional<NhanVien> findBySdt(String sdt);

    Optional<NhanVien> findByEmail(String email);
}
