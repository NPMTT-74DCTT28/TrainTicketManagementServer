package com.nmptt.ticketapi.service;

import com.nmptt.ticketapi.dto.request.GheRequest;
import com.nmptt.ticketapi.dto.response.GheResponse;
import com.nmptt.ticketapi.entity.Ghe;
import com.nmptt.ticketapi.entity.ToaTau;
import com.nmptt.ticketapi.exception.DuplicateDataException;
import com.nmptt.ticketapi.exception.ResourceNotFoundException;
import com.nmptt.ticketapi.repository.GheRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GheServiceImpl implements GheService {
    private final GheRepository gheRepository;

    @Override
    public List<GheResponse> getAllGhe() {
        return gheRepository.findAll()
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public GheResponse getGheById(Integer id) {
        Ghe ghe = gheRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin ghế!"));
        return mapToResponse(ghe);
    }

    @Override
    public GheResponse createGhe(GheRequest gheRequest) {
        checkTrung(gheRequest);
        ToaTau toaTau = new ToaTau();
        toaTau.setId(gheRequest.getIdToaTau());

        Ghe ghe = Ghe.builder()
                .id(gheRequest.getId())
                .soGhe(gheRequest.getSoGhe())
                .idToaTau(toaTau)
                .build();
        return mapToResponse(gheRepository.save(ghe));
    }

    @Override
    public GheResponse updateGhe(GheRequest gheRequest) {
        Ghe ghe = gheRepository.findById(gheRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy ghế!"));

        checkTrung(gheRequest);

        ToaTau toaTau = new ToaTau();
        toaTau.setId(gheRequest.getIdToaTau());

        ghe.setSoGhe(gheRequest.getSoGhe());
        ghe.setIdToaTau(toaTau);

        return mapToResponse(gheRepository.save(ghe));
    }

    @Override
    public void deleteGhe(Integer id) {
        Ghe ghe = gheRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy ghế!"));
        gheRepository.delete(ghe);
    }

    @Override
    public List<GheResponse> searchGhe(String keyword) {
        String search = "%" + keyword + "%";
        return gheRepository.findBySoGheLike(search)
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private void checkTrung(GheRequest gheRequest) {
        if (gheRepository.existsBySoGheAndIdNot(gheRequest.getSoGhe(), gheRequest.getId())) {
            throw new DuplicateDataException("Số ghế này đã tồn tại trong hệ thống!");
        }
    }

    private GheResponse mapToResponse(Ghe ghe) {
        return GheResponse.builder()
                .id(ghe.getId())
                .soGhe(ghe.getSoGhe())
                .idToaTau(ghe.getIdToaTau().getId())
                .build();
    }
}