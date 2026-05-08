package com.nmptt.ticketapi.repository;

import com.nmptt.ticketapi.entity.LichTrinh;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LichTrinhRepository extends JpaRepository<LichTrinh, Integer> {
    Optional<LichTrinh> findById(Integer id);

    List<LichTrinh> findByMaLichTrinhLike(String maLichTrinh);

    boolean existsByMaLichTrinhAndIdNot(String maLichTrinh, Integer id);


}
