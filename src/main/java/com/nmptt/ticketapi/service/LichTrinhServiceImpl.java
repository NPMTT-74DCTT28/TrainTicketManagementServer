package com.nmptt.ticketapi.service;

import com.nmptt.ticketapi.dto.request.LichTrinhRequest;
import com.nmptt.ticketapi.dto.response.LichTrinhResponse;
import com.nmptt.ticketapi.entity.LichTrinh;
import com.nmptt.ticketapi.entity.Tau;
import com.nmptt.ticketapi.entity.TuyenDuong;
import com.nmptt.ticketapi.exception.DuplicateDataException;
import com.nmptt.ticketapi.exception.ResourceNotFoundException;
import com.nmptt.ticketapi.repository.LichTrinhRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LichTrinhServiceImpl implements LichTrinhService {
    private final LichTrinhRepository lichTrinhRepository;

    @Override
    public List<LichTrinhResponse> getAllLichTrinh() {
        return lichTrinhRepository.findAll()
                .stream().map(this::maptoResponse).collect(Collectors.toList());
    }

    @Override
    public LichTrinhResponse getLichTrinhById(Integer id) {
        LichTrinh lichTrinh = lichTrinhRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin lịch trình!"));
        return maptoResponse(lichTrinh);
    }

    @Override
    public LichTrinhResponse createLichTrinh(LichTrinhRequest lichTrinhRequest) {
        checkTrung(lichTrinhRequest);
        Tau idTau = new Tau();
        idTau.setId(lichTrinhRequest.getIdTau());
        TuyenDuong idTuyenDuong = new TuyenDuong();
        idTuyenDuong.setId(lichTrinhRequest.getIdTuyenDuong());
        LichTrinh lichTrinh = LichTrinh.builder()
                .id(lichTrinhRequest.getId())
                .maLichTrinh(lichTrinhRequest.getMaLichTrinh())
                .idTau(idTau)
                .idTuyenDuong(idTuyenDuong)
                .ngayDi(lichTrinhRequest.getNgayDi())
                .ngayDen(lichTrinhRequest.getNgayDen())
                .trangThai(lichTrinhRequest.getTrangThai())
                .build();
        return maptoResponse(lichTrinhRepository.save(lichTrinh));
    }

    @Override
    public LichTrinhResponse updateLichTrinh(LichTrinhRequest lichTrinhRequest) {
        checkTrung(lichTrinhRequest);
        LichTrinh lichTrinh = lichTrinhRepository.findById(lichTrinhRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy lịch trình!"));
        Tau idTau = new Tau();
        idTau.setId(lichTrinhRequest.getIdTau());
        TuyenDuong idTuyenDuong = new TuyenDuong();
        idTuyenDuong.setId(lichTrinhRequest.getIdTuyenDuong());
        lichTrinh.setId(lichTrinhRequest.getId());
        lichTrinh.setMaLichTrinh(lichTrinhRequest.getMaLichTrinh());
        lichTrinh.setIdTau(idTau);
        lichTrinh.setIdTuyenDuong(idTuyenDuong);
        lichTrinh.setNgayDen(lichTrinhRequest.getNgayDi());
        lichTrinh.setNgayDen(lichTrinhRequest.getNgayDen());
        lichTrinh.setTrangThai(lichTrinhRequest.getTrangThai());
        return maptoResponse(lichTrinhRepository.save(lichTrinh));
    }


    @Override
    public void deleteLichTrinh(Integer id) {
        LichTrinh lichTrinh = lichTrinhRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy lịch trình!"));
        lichTrinhRepository.delete(lichTrinh);
    }

    @Override
    public List<LichTrinhResponse> searchLichTrinh(String search) {
        String key = "%" + search + "%";
        return lichTrinhRepository.findByMaLichTrinhLike(key)
                .stream().map(this::maptoResponse).collect(Collectors.toList());
    }

    private void checkTrung(LichTrinhRequest lichTrinhRequest) {
        if (lichTrinhRepository.existsByMaLichTrinhAndIdNot(lichTrinhRequest.getMaLichTrinh(), lichTrinhRequest.getId())) {
            throw new DuplicateDataException("Mã Lịch Trình đã tồn tại!");
        }
    }

    private LichTrinhResponse maptoResponse(LichTrinh lichTrinh) {
        return LichTrinhResponse.builder()
                .id(lichTrinh.getId())
                .maLichTrinh(lichTrinh.getMaLichTrinh())
                .idTau(lichTrinh.getIdTau().getId())
                .idTuyenDuong(lichTrinh.getIdTuyenDuong().getId())
                .ngayDi(lichTrinh.getNgayDi())
                .ngayDen(lichTrinh.getNgayDen())
                .trangThai(lichTrinh.getTrangThai())
                .build();


    }
}
