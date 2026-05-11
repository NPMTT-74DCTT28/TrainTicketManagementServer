package com.nmptt.ticketapi.service;

import com.nmptt.ticketapi.entity.Tau;

import java.util.List;

public interface TauService {
    List<Tau> getAllTau();

    Tau getTauById(Integer id);

    Tau insertTau(Tau tau);

    Tau updateTau(Tau tau);

    void delete(Integer id);

    List<Tau> searchTau(String maTau, String tenTau);

}
