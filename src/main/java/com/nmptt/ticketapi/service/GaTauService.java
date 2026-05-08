package com.nmptt.ticketapi.service;

import com.nmptt.ticketapi.entity.GaTau;

import java.util.List;

public interface GaTauService {
    List<GaTau> getAllGaTau();

    GaTau getGaTauById(Integer id);

    GaTau createGaTau(GaTau gaTau);

    GaTau updateGaTau(GaTau gaTau);

    void deleteGaTau(Integer id);

    List<GaTau> searchGaTau(String search);
}
