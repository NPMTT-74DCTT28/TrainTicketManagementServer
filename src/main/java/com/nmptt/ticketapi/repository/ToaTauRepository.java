package com.nmptt.ticketapi.repository;

import com.nmptt.ticketapi.entity.ToaTau;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ToaTauRepository extends JpaRepository<ToaTau, Integer> {
    Optional<ToaTau> findById(Integer id);
    List<ToaTau> findByMaToaContaining(String maToa);
    boolean existsByMaToaAndIdTau_IdAndIdNot(String maToa, Integer idTau, Integer idNot);
}