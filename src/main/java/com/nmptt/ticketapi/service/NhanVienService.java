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
import com.nmptt.ticketapi.security.JwtUtil;
import com.nmptt.ticketapi.specification.NhanVienSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface NhanVienService {
    List<NhanVienResponse> getAllNhanVien();

    NhanVienResponse getNhanVienById(int id);

    NhanVienResponse createNhanVien(NhanVienRequest nhanVienRequest);

    NhanVienResponse updateNhanVien(NhanVienRequest nhanVienRequest);

    NhanVienResponse updateInfo(NhanVienRequest nhanVienRequest);

    void deleteNhanVien(int id);

    NhanVienResponse login(LoginRequest request);

    void changePassword(ChangePasswordRequest request);

    List<NhanVienResponse> searchNhanVien(String keyword, String gioiTinh, String vaiTro);

    @Service
    @RequiredArgsConstructor
    class NhanVienServiceImpl implements NhanVienService {
        private final NhanVienRepository repository;
        private final PasswordEncoder passwordEncoder;

        private final JwtUtil jwtUtil;

        @Override
        public List<NhanVienResponse> getAllNhanVien() {
            List<NhanVien> staffs = repository.findAll();
            return staffs.stream()
                    .map(this::mapToResponse)
                    .collect(Collectors.toList());
        }

        public NhanVienResponse getNhanVienById(int id) {
            NhanVien nhanVien = repository.findById(id)
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

            return mapToResponse(repository.save(newNhanVien));
        }

        @Override
        public NhanVienResponse updateNhanVien(NhanVienRequest request) {
            checkTrung(request);

            NhanVien existing = repository.findById(request.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin nhân viên."));

            existing.setHoTen(request.getHoTen());
            existing.setNgaySinh(request.getNgaySinh());
            existing.setGioiTinh(request.getGioiTinh());
            existing.setSdt(request.getSdt());
            existing.setEmail(request.getEmail());
            existing.setDiaChi(request.getDiaChi());
            existing.setVaiTro(request.getVaiTro());

            return mapToResponse(repository.save(existing));
        }

        @Override
        public NhanVienResponse updateInfo(NhanVienRequest request) {
            checkTrung(request);

            NhanVien existing = repository.findById(request.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin nhân viên."));

            existing.setHoTen(request.getHoTen());
            existing.setNgaySinh(request.getNgaySinh());
            existing.setGioiTinh(request.getGioiTinh());
            existing.setSdt(request.getSdt());
            existing.setEmail(request.getEmail());
            existing.setDiaChi(request.getDiaChi());

            return mapToResponse(repository.save(existing));
        }

        @Override
        public void deleteNhanVien(int id) {
            NhanVien nhanVien = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin nhân viên."));
            repository.delete(nhanVien);
        }

        @Override
        public NhanVienResponse login(LoginRequest request) {
            String pwIncorrectMsg = "Tài khoản hoặc mật khẩu không chính xác.";
            NhanVien nhanVien = repository.findByMaNhanVien(request.getMaNhanVien())
                    .orElseThrow(() -> new ResourceNotFoundException(pwIncorrectMsg));
            boolean isPwMatch = passwordEncoder.matches(request.getMatKhau(), nhanVien.getMatKhau());
            if (!isPwMatch) {
                throw new BadCredentialsException(pwIncorrectMsg);
            }
            NhanVienResponse response = mapToResponse(nhanVien);
            response.setToken(jwtUtil.generateToken(nhanVien.getMaNhanVien(), nhanVien.getVaiTro()));
            return response;
        }

        @Override
        public void changePassword(ChangePasswordRequest request) {
            NhanVien nhanVien = repository.findById(request.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin nhân viên."));

            if (!passwordEncoder.matches(request.getOldPassword(), nhanVien.getMatKhau())) {
                throw new BadCredentialsException("Mật khẩu cũ không chính xác.");
            }
            String encodedPassword = passwordEncoder.encode(request.getNewPassword());
            nhanVien.setMatKhau(encodedPassword);
            repository.save(nhanVien);
        }

        @Override
        public List<NhanVienResponse> searchNhanVien(String keyword, String gioiTinh, String vaiTro) {
            List<NhanVien> staffs = repository.findAll(NhanVienSpecification.filter(keyword, gioiTinh, vaiTro));

            return staffs.stream()
                    .map(this::mapToResponse)
                    .collect(Collectors.toList());
        }

        private void checkTrung(NhanVienRequest request) {
            if (repository.existsByMaNhanVienAndIdNot(request.getMaNhanVien(), request.getId())) {
                throw new DuplicateDataException("Mã nhân viên đã tồn tại.");
            }
            if (repository.existsBySdtAndIdNot(request.getSdt(), request.getId())) {
                throw new DuplicateDataException("SĐT đã được sử dụng.");
            }
            if (request.getEmail() != null && repository.existsByEmailAndIdNot(request.getEmail(), request.getId())) {
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
}
