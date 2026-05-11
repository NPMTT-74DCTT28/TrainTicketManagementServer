package com.nmptt.ticketapi.repository;

import com.nmptt.ticketapi.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Integer>, JpaSpecificationExecutor<KhachHang> {
    Optional<KhachHang> findById(int id);

    Optional<KhachHang> findByCccd(String cccd);

    boolean existsBySdtAndIdNot(String sdt, Integer id);

    boolean existsByCccdAndIdNot(String cccd, Integer id);
}
