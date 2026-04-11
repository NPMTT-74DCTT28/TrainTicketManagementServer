package com.nmptt.ticketapi.repository;

import com.nmptt.ticketapi.entity.TuyenDuong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TuyenDuongRepository extends JpaRepository<TuyenDuong, Integer> {
    Optional<TuyenDuong> findById(Integer id);
    List<TuyenDuong> findByMaTuyenLikeOrTenTuyenLike(String maTuyen, String tenTuyen);
    boolean existsByMaTuyenAndIdNot(String maTuyen, Integer id);
    boolean existsByTenTuyenAndIdNot(String tenTuyen, Integer id);
}