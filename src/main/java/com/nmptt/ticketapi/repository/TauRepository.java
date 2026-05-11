package com.nmptt.ticketapi.repository;

import com.nmptt.ticketapi.entity.Tau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TauRepository extends JpaRepository<Tau, Integer> {
    List<Tau> findByMaTauLikeOrTenTauLike(String maTau, String tenTau);

    boolean existsByMaTauAndIdNot(String maTau, Integer id);

    boolean existsByTenTauAndIdNot(String tenTau, Integer id);
}
