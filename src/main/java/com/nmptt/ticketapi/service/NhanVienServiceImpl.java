package com.nmptt.ticketapi.service;

import com.nmptt.ticketapi.dto.request.ChangePasswordRequest;
import com.nmptt.ticketapi.dto.request.LoginRequest;
import com.nmptt.ticketapi.dto.request.NhanVienRequest;
import com.nmptt.ticketapi.dto.response.NhanVienResponse;
import com.nmptt.ticketapi.entity.NhanVien;
import com.nmptt.ticketapi.exception.BadCredentialsException;
import com.nmptt.ticketapi.exception.DuplicateDataException;
import com.nmptt.ticketapi.exception.ResourceNotFoundException;
import com.nmptt.ticketapi.repository.NhanVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NhanVienServiceImpl implements NhanVienService {
    private final NhanVienRepository nhanVienRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<NhanVienResponse> getAllNhanVien() {
        List<NhanVien> staffs = nhanVienRepository.findAll();
        return staffs.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public NhanVienResponse getNhanVienById(int id) {
        NhanVien nhanVien = nhanVienRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin nhân viên."));
        return mapToResponse(nhanVien);
    }

    @Override
    public NhanVienResponse createNhanVien(NhanVienRequest request) {
        checkTrung(request);

        String encodedPassword = passwordEncoder.encode(request.getMatKhau());
        NhanVien newNhanVien = NhanVien.builder()
                .maNhanVien(request.getMaNhanVien())
                .matKhau(encodedPassword)
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

    @Override
    public NhanVienResponse updateNhanVien(NhanVienRequest request) {
        checkTrung(request);

        NhanVien existing = nhanVienRepository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin nhân viên."));

        existing.setHoTen(request.getHoTen());
        existing.setNgaySinh(request.getNgaySinh());
        existing.setGioiTinh(request.getGioiTinh());
        existing.setSdt(request.getSdt());
        existing.setEmail(request.getEmail());
        existing.setDiaChi(request.getDiaChi());
        existing.setVaiTro(request.getVaiTro());

        return mapToResponse(nhanVienRepository.save(existing));
    }

    @Override
    public void deleteNhanVien(int id) {
        NhanVien nhanVien = nhanVienRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin nhân viên."));
        nhanVienRepository.delete(nhanVien);
    }

    @Override
    public NhanVienResponse login(LoginRequest request) {
        String pwIncorrectMsg = "Tài khoản hoặc mật khẩu không chính xác.";
        NhanVien nhanVien = nhanVienRepository.findByMaNhanVien(request.getMaNhanVien())
                .orElseThrow(() -> new ResourceNotFoundException(pwIncorrectMsg));
        boolean isPwMatch = passwordEncoder.matches(request.getMatKhau(), nhanVien.getMatKhau());
        if (!isPwMatch) {
            throw new BadCredentialsException(pwIncorrectMsg);
        }
        return mapToResponse(nhanVien);
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        NhanVien nhanVien = nhanVienRepository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin nhân viên."));

        if (!passwordEncoder.matches(request.getOldPassword(), nhanVien.getMatKhau())) {
            throw new BadCredentialsException("Mật khẩu cũ không chính xác.");
        }
        String encodedPassword = passwordEncoder.encode(request.getNewPassword());
        nhanVien.setMatKhau(encodedPassword);
        nhanVienRepository.save(nhanVien);
    }

    private void checkTrung(NhanVienRequest request) {
        if (nhanVienRepository.existsByMaNhanVienAndIdNot(request.getMaNhanVien(), request.getId())) {
            throw new DuplicateDataException("Mã nhân viên đã tồn tại.");
        }
        if (nhanVienRepository.existsBySdtAndIdNot(request.getSdt(), request.getId())) {
            throw new DuplicateDataException("SĐT đã được sử dụng.");
        }
        if (request.getEmail() != null && nhanVienRepository.existsByEmailAndIdNot(request.getEmail(), request.getId())) {
            throw new DuplicateDataException("Email đã được sử dụng.");
        }
    }

    private NhanVienResponse mapToResponse(NhanVien nhanVien) {
        return NhanVienResponse.builder()
                .id(nhanVien.getId())
                .maNhanVien(nhanVien.getMaNhanVien())
                .hoTen(nhanVien.getHoTen())
                .ngaySinh(nhanVien.getNgaySinh())
                .gioiTinh(nhanVien.getGioiTinh())
                .sdt(nhanVien.getSdt())
                .email(nhanVien.getEmail())
                .diaChi(nhanVien.getDiaChi())
                .vaiTro(nhanVien.getVaiTro())
                .build();
    }
}
