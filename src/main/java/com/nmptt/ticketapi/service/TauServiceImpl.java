package com.nmptt.ticketapi.service;

import com.nmptt.ticketapi.entity.Tau;
import com.nmptt.ticketapi.exception.DuplicateDataException;
import com.nmptt.ticketapi.exception.ResourceNotFoundException;
import com.nmptt.ticketapi.repository.TauRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TauServiceImpl implements TauService {
    private final TauRepository tauRepository;

    @Override
    public List<Tau> getAllTau() {
        return tauRepository.findAll();
    }


    @Override
    public Tau getTauById(Integer id) {
        return tauRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin tàu"));
    }

    @Override
    public Tau insertTau(Tau tau) {
        checkTrung(tau);
        return tauRepository.save(tau);
    }

    @Override
    public Tau updateTau(Tau tau) {
        checkTrung(tau);
        Tau Existing = tauRepository.findById(tau.getId()).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin tàu"));
        Existing.setId(tau.getId());
        Existing.setMaTau(tau.getMaTau());
        Existing.setTenTau(tau.getTenTau());
        return tauRepository.save(Existing);
    }

    @Override
    public void delete(Integer id) {
        Tau tau = tauRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin tàu"));
        tauRepository.delete(tau);
    }

    @Override
    public List<Tau> searchTau(String maTau, String tenTau) {
        return tauRepository.findByMaTauLikeOrTenTauLike(maTau, tenTau);
    }


    private void checkTrung(Tau tau) {
        if (tauRepository.existsByMaTauAndIdNot(tau.getMaTau(), tau.getId())) {
            throw new DuplicateDataException("Mã tàu đã tồn tại");
        }
        if (tauRepository.existsByTenTauAndIdNot(tau.getTenTau(), tau.getId())) {
            throw new DuplicateDataException("Tên tàu đã tồn tại");
        }
    }
}
