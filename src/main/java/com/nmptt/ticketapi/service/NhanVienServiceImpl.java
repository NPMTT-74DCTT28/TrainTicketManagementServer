package com.nmptt.ticketapi.service;

import com.nmptt.ticketapi.dto.request.NhanVienRequest;
import com.nmptt.ticketapi.dto.response.NhanVienResponse;
import com.nmptt.ticketapi.entity.NhanVien;
import com.nmptt.ticketapi.exception.DuplicateDataException;
import com.nmptt.ticketapi.exception.ResourceNotFoundException;
import com.nmptt.ticketapi.repository.NhanVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NhanVienServiceImpl implements NhanVienService {
    private final NhanVienRepository nhanVienRepository;

    @Override
    public List<NhanVienResponse> getAllNhanVien() {
        List<NhanVien> staffs = nhanVienRepository.findAll();
        return staffs.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NhanVienResponse getNhanVienByMa(String maNhanVien) {
        NhanVien nhanVien = nhanVienRepository.findByMaNhanVien(maNhanVien)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy nhân viên với mã: " + maNhanVien));
        return mapToResponse(nhanVien);
    }

    @Override
    public NhanVienResponse createNhanVien(NhanVienRequest request) {
        checkTrung(request);

        NhanVien newNhanVien = NhanVien.builder()
                .maNhanVien(request.getMaNhanVien())
                .matKhau(request.getMatKhau())
                .hoTen(request.getHoTen())
                .ngaySinh(request.getNgaySinh())
                .gioiTinh(request.getGioiTinh())
                .sdt(request.getSdt())
                .email(request.getEmail())
                .diaChi(request.getDiaChi())
                .vaiTro(request.getVaiTro())
                .build();

        return mapToResponse(nhanVienRepository.save(newNhanVien));
    }

    private void checkTrung(NhanVienRequest request) {
        if (nhanVienRepository.findByMaNhanVien(request.getMaNhanVien()).isPresent()) {
            throw new DuplicateDataException("Mã nhân viên đã tồn tại: " + request.getMaNhanVien());
        }
        if (nhanVienRepository.findBySdt(request.getSdt()).isPresent()) {
            throw new DuplicateDataException("SĐT đã được sử dụng: " + request.getSdt());
        }
        if (nhanVienRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateDataException("Email đã được sử dụng: " + request.getEmail());
        }
    }

    private NhanVienResponse mapToResponse(NhanVien nhanVien) {
        return NhanVienResponse.builder()
                .maNhanVien(nhanVien.getMaNhanVien())
                .hoTen(nhanVien.getHoTen())
                .ngaySinh(nhanVien.getNgaySinh())
                .gioiTinh(nhanVien.getGioiTinh())
                .sdt(nhanVien.getSdt())
                .email(nhanVien.getEmail())
                .vaiTro(nhanVien.getVaiTro())
                .build();
    }
}
