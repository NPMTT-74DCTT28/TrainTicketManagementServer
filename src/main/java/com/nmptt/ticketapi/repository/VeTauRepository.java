package com.nmptt.ticketapi.repository;

import com.nmptt.ticketapi.entity.VeTau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VeTauRepository extends JpaRepository<VeTau, Integer>, JpaSpecificationExecutor<VeTau> {
    VeTau findByMaVe(String maVe);

    boolean existsByMaVeAndIdNot(String maVe, Integer id);
}
