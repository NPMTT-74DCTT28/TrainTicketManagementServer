package com.nmptt.ticketapi.service;

import com.nmptt.ticketapi.dto.request.ToaTauRequest;
import com.nmptt.ticketapi.dto.response.ToaTauResponse;
import com.nmptt.ticketapi.entity.LoaiToa;
import com.nmptt.ticketapi.entity.Tau;
import com.nmptt.ticketapi.entity.ToaTau;
import com.nmptt.ticketapi.exception.DuplicateDataException;
import com.nmptt.ticketapi.exception.ResourceNotFoundException;
import com.nmptt.ticketapi.repository.ToaTauRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ToaTauServiceImpl implements ToaTauService {

    private final ToaTauRepository toaTauRepository;

    @Override
    public List<ToaTauResponse> getAllToaTau() {
        return toaTauRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ToaTauResponse getToaTauById(Integer id) {
        ToaTau toaTau = toaTauRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy toa tàu!"));
        return mapToResponse(toaTau);
    }

    @Override
    public ToaTauResponse createToaTau(ToaTauRequest toaTauRequest) {
        checkTrung(toaTauRequest);

        Tau tau = new Tau();
        tau.setId(toaTauRequest.getIdTau());

        LoaiToa loaiToa = new LoaiToa();
        loaiToa.setId(toaTauRequest.getIdLoaiToa());

        ToaTau toaTau = ToaTau.builder()
                .maToa(toaTauRequest.getMaToa())
                .idTau(tau)
                .idLoaiToa(loaiToa)
                .build();

        return mapToResponse(toaTauRepository.save(toaTau));
    }

    @Override
    public ToaTauResponse updateToaTau(ToaTauRequest toaTauRequest) {


        checkTrung(toaTauRequest);

        ToaTau toaTau = toaTauRepository.findById(toaTauRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy toa tàu!"));

        Tau tau = new Tau();
        tau.setId(toaTauRequest.getIdTau());

        LoaiToa loaiToa = new LoaiToa();
        loaiToa.setId(toaTauRequest.getIdLoaiToa());

        toaTau.setMaToa(toaTauRequest.getMaToa());
        toaTau.setIdTau(tau);
        toaTau.setIdLoaiToa(loaiToa);

        return mapToResponse(toaTauRepository.save(toaTau));
    }

    @Override
    public void deleteToaTau(Integer id) {
        ToaTau toaTau = toaTauRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy toa tàu!"));
        toaTauRepository.delete(toaTau);
    }

    @Override
    public List<ToaTauResponse> searchToaTau(String search) {
        return toaTauRepository.findByMaToaContaining(search)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private void checkTrung(ToaTauRequest toaTauRequest) {
        if (toaTauRepository.existsByMaToaAndIdTau_IdAndIdNot(toaTauRequest.getMaToa(), toaTauRequest.getIdTau(), toaTauRequest.getId())) {
            throw new DuplicateDataException("Toa đã tồn tại!");
        }
    }

    private ToaTauResponse mapToResponse(ToaTau toaTau) {
        return ToaTauResponse.builder()
                .id(toaTau.getId())
                .maToa(toaTau.getMaToa())
                .idTau(toaTau.getIdTau().getId())
                .idLoaiToa(toaTau.getIdLoaiToa().getId())
                .build();
    }
}