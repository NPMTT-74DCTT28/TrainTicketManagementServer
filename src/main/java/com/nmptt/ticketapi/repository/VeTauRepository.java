package com.nmptt.ticketapi.repository;

import com.nmptt.ticketapi.entity.VeTau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeTauRepository extends JpaRepository<VeTau, Integer> {
    List<VeTau> findAllByMaVeLike(String maVe);

    boolean existsByMaVeAndIdNot(String maVe, Integer id);
}
