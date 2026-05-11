package com.nmptt.ticketapi.service;

import com.nmptt.ticketapi.entity.LoaiToa;
import com.nmptt.ticketapi.exception.DuplicateDataException;
import com.nmptt.ticketapi.exception.ResourceNotFoundException;
import com.nmptt.ticketapi.repository.LoaiToaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoaiToaServiceImpl implements LoaiToaService {
    private final LoaiToaRepository loaiToaRepository;

    @Override
    public List<LoaiToa> getAllLoaiToa() {
        return loaiToaRepository.findAll();
    }

    @Override
    public LoaiToa getLoaiToaById(Integer id) {
        return loaiToaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin loại toa"));
    }

    @Override
    public LoaiToa insertLoaiToa(LoaiToa loaiToa) {
        checkTrung(loaiToa);
        return loaiToaRepository.save(loaiToa);
    }

    @Override
    public LoaiToa updateLoaiToa(LoaiToa loaiToa) {
        LoaiToa existing = loaiToaRepository.findById(loaiToa.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin loại toa để cập nhật"));

        checkTrung(loaiToa);

        existing.setTenLoai(loaiToa.getTenLoai());
        existing.setHeSoGia(loaiToa.getHeSoGia());

        return loaiToaRepository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        LoaiToa loaiToa = loaiToaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin loại toa để xóa"));
        loaiToaRepository.delete(loaiToa);
    }

    @Override
    public List<LoaiToa> searchLoaiToa(String tenLoai, BigDecimal heSoGia) {
        return loaiToaRepository.findByTenLoaiLikeOrHeSoGia(tenLoai, heSoGia);
    }

    private void checkTrung(LoaiToa loaiToa) {
        if (loaiToa.getId() != null) {
            if (loaiToaRepository.existsByTenLoaiAndIdNot(loaiToa.getTenLoai(), loaiToa.getId())) {
                throw new DuplicateDataException("Tên loại toa đã tồn tại");
            }
        } else {
            // Nếu là thêm mới
            if (loaiToaRepository.existsByTenLoai(loaiToa.getTenLoai())) {
                throw new DuplicateDataException("Tên loại toa đã tồn tại");
            }
        }
    }
}
