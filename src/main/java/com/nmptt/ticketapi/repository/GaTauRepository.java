package com.nmptt.ticketapi.repository;

import com.nmptt.ticketapi.entity.GaTau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GaTauRepository extends JpaRepository<GaTau, Integer> {
    Optional<GaTau> findById(Integer id);

    List<GaTau> findByMaGaLikeOrTenGaLike(String maGa, String tenGa);

    boolean existsByMaGaAndIdNot(String maGa, Integer id);

    boolean existsByTenGaAndIdNot(String tenGa, Integer id);
}
