package com.nmptt.ticketapi.repository;

import com.nmptt.ticketapi.entity.Ghe;
import com.nmptt.ticketapi.entity.ToaTau;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GheRepository extends JpaRepository<Ghe, Integer> {
    Optional<Ghe> findById(Integer id);
    List<Ghe> findBySoGheLikeOrIdToaTau(String soGhe, Integer idToaTau);

    boolean existsBySoGheAndIdNot(String soGhe, Integer id);

    boolean existsByIdToaTau(Integer idToaTau, Integer id);
}
