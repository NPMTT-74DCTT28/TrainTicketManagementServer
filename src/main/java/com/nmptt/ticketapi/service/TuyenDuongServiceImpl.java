package com.nmptt.ticketapi.service;

import com.nmptt.ticketapi.dto.request.TuyenDuongRequest;
import com.nmptt.ticketapi.dto.response.TuyenDuongResponse;
import com.nmptt.ticketapi.entity.GaTau;
import com.nmptt.ticketapi.entity.TuyenDuong;
import com.nmptt.ticketapi.exception.DuplicateDataException;
import com.nmptt.ticketapi.exception.ResourceNotFoundException;
import com.nmptt.ticketapi.repository.TuyenDuongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TuyenDuongServiceImpl implements TuyenDuongService {
    private final TuyenDuongRepository tuyenDuongRepository;

    @Override
    public List<TuyenDuongResponse> getAllTuyenDuong() {
        return tuyenDuongRepository.findAll()
                .stream().map(this::maptoResponse).collect(Collectors.toList());
    }

    @Override
    public TuyenDuongResponse createTuyenDuong(TuyenDuongRequest tuyenDuongRequest) {
        checkTrung(tuyenDuongRequest);
        GaTau gadi = new GaTau();
        gadi.setId(tuyenDuongRequest.getIdGaDi());
        GaTau gaden = new GaTau();
        gaden.setId(tuyenDuongRequest.getIdGaDen());
        TuyenDuong tuyenDuong = TuyenDuong.builder()
                .id(tuyenDuongRequest.getId())
                .maTuyen(tuyenDuongRequest.getMaTuyen())
                .tenTuyen(tuyenDuongRequest.getTenTuyen())
                .idGaDi(gadi)
                .idGaDen(gaden)
                .khoangCachKm(tuyenDuongRequest.getKhoangCachKm())
                .giaCoBan(tuyenDuongRequest.getGiaCoBan())
                .build();
        return maptoResponse(tuyenDuongRepository.save(tuyenDuong));
    }

    @Override
    public TuyenDuongResponse updateTuyenDuong(TuyenDuongRequest tuyenDuongRequest) {
        checkTrung(tuyenDuongRequest);
        TuyenDuong tuyenDuong = tuyenDuongRepository.findById(tuyenDuongRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm tháy tuyến đường!"));
        GaTau gadi = new GaTau();
        gadi.setId(tuyenDuongRequest.getIdGaDi());
        GaTau gaden = new GaTau();
        gaden.setId(tuyenDuongRequest.getIdGaDen());
        tuyenDuong.setId(tuyenDuongRequest.getId());
        tuyenDuong.setMaTuyen(tuyenDuongRequest.getMaTuyen());
        tuyenDuong.setTenTuyen(tuyenDuongRequest.getTenTuyen());
        tuyenDuong.setIdGaDi(gadi);
        tuyenDuong.setIdGaDen(gaden);
        tuyenDuong.setKhoangCachKm(tuyenDuongRequest.getKhoangCachKm());
        tuyenDuong.setGiaCoBan(tuyenDuongRequest.getGiaCoBan());
        return maptoResponse(tuyenDuongRepository.save(tuyenDuong));
    }

    @Override
    public void deleteTuyenDuong(Integer id) {
        TuyenDuong tuyenDuong = tuyenDuongRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Tuyến Đường!"));
        tuyenDuongRepository.delete(tuyenDuong);
    }

    @Override
    public List<TuyenDuongResponse> searchTuyenDuong(String search) {
        String key = "%" + search + "%";
        return tuyenDuongRepository.findByMaTuyenLikeOrTenTuyenLike(key, key)
                .stream().map(this::maptoResponse).collect(Collectors.toList());
    }

    private void checkTrung(TuyenDuongRequest tuyenDuongRequest) {
        if (tuyenDuongRepository.existsByMaTuyenAndIdNot(tuyenDuongRequest.getMaTuyen(), tuyenDuongRequest.getId())) {
            throw new DuplicateDataException("Mã tuyến đã tồn tại!");
        }
        if (tuyenDuongRepository.existsByTenTuyenAndIdNot(tuyenDuongRequest.getTenTuyen(), tuyenDuongRequest.getId())) {
            throw new DuplicateDataException("Tên tuyến đã tồn tại!");
        }
    }

    private TuyenDuongResponse maptoResponse(TuyenDuong tuyenDuong) {
        return TuyenDuongResponse.builder()
                .id(tuyenDuong.getId())
                .maTuyen(tuyenDuong.getMaTuyen())
                .tenTuyen(tuyenDuong.getTenTuyen())
                .idGaDi(tuyenDuong.getIdGaDi().getId())
                .idGaDen(tuyenDuong.getIdGaDen().getId())
                .khoangCachKm(tuyenDuong.getKhoangCachKm())
                .giaCoBan(tuyenDuong.getGiaCoBan())
                .build();
    }

}
