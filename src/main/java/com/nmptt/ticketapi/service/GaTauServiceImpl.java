package com.nmptt.ticketapi.service;

import com.nmptt.ticketapi.entity.GaTau;
import com.nmptt.ticketapi.exception.DuplicateDataException;
import com.nmptt.ticketapi.exception.ResourceNotFoundException;
import com.nmptt.ticketapi.repository.GaTauRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GaTauServiceImpl implements GaTauService {
    private final GaTauRepository gaTauRepository;

    @Override
    public List<GaTau> getAllGaTau() {
        return gaTauRepository.findAll();
    }

    @Override
    public GaTau createGaTau(GaTau gaTau) {
        checkTrung(gaTau);
        return gaTauRepository.save(gaTau);
    }

    @Override
    public GaTau updateGaTau(GaTau gaTau) {
        checkTrung(gaTau);
        return gaTauRepository.save(gaTau);
    }

    @Override
    public void deleteGaTau(Integer id) {
        GaTau gaTau = gaTauRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin ga tàu"));
        gaTauRepository.delete(gaTau);
    }

    @Override
    public List<GaTau> searchGaTau(String search) {
        String key = "%" + search + "%";
        return gaTauRepository.findByMaGaLikeOrTenGaLike(key, key);
    }

    private void checkTrung(GaTau gaTau) {
        if (gaTauRepository.existsByMaGaAndIdNot(gaTau.getMaGa(), gaTau.getId())) {
            throw new DuplicateDataException("Mã ga đã tồn tại!");
        }
        if (gaTauRepository.existsByTenGaAndIdNot(gaTau.getTenGa(), gaTau.getId())) {
            throw new DuplicateDataException("Tên ga đã tồn tại!");
        }
    }
}
