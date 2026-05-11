package com.nmptt.ticketapi.repository;

import com.nmptt.ticketapi.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer>, JpaSpecificationExecutor<NhanVien> {

    Optional<NhanVien> findById(int id);

    Optional<NhanVien> findByMaNhanVien(String maNhanVien);

    boolean existsByMaNhanVienAndIdNot(String maNhanVien, int id);

    boolean existsBySdtAndIdNot(String sdt, int id);

    boolean existsByEmailAndIdNot(String email, int id);
}
