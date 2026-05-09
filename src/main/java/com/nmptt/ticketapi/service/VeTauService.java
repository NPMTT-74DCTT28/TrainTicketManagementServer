package com.nmptt.ticketapi.service;

import com.nmptt.ticketapi.dto.request.VeTauRequest;
import com.nmptt.ticketapi.dto.response.VeTauResponse;
import com.nmptt.ticketapi.entity.*;
import com.nmptt.ticketapi.exception.DuplicateDataException;
import com.nmptt.ticketapi.exception.ResourceNotFoundException;
import com.nmptt.ticketapi.repository.GheRepository;
import com.nmptt.ticketapi.repository.LichTrinhRepository;
import com.nmptt.ticketapi.repository.VeTauRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public interface VeTauService {
    List<VeTauResponse> getAllVeTau();

    VeTauResponse getVeTauById(int id);

    VeTauResponse createVeTau(VeTauRequest veTauRequest);

    VeTauResponse updateVeTau(VeTauRequest veTauRequest);

    void deleteVeTau(int id);

    List<VeTauResponse> searchVeTau(String maVe);

    double tinhGiaVe(int idLichTrinh, int idGhe);

    double tinhGiaVeByMaVe(int id);

    @Service
    @RequiredArgsConstructor

    class VeTauServiceImpl implements VeTauService {
        private final VeTauRepository repository;
        private final LichTrinhRepository lichTrinhRepository;
        private final GheRepository gheRepository;

        @Override
        public List<VeTauResponse> getAllVeTau() {

            List<VeTau> tickets = repository.findAll();
             return tickets.stream()
                     .map(this::mapToResponse)
                     .collect(Collectors.toList());
        }

        public VeTauResponse getVeTauById(int id) {
            VeTau veTau = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin vé"));
            return mapToResponse(veTau);
        }

        @Override
        public VeTauResponse createVeTau(VeTauRequest request) {
            checkTrung(request);
            KhachHang khachHang = KhachHang.builder().id(request.getIdKhachHang()).build();
            LichTrinh lichTrinh = LichTrinh.builder().id(request.getIdLichTrinh()).build();
            Ghe ghe = Ghe.builder().id(request.getIdGhe()).build();
            NhanVien nhanVien = NhanVien.builder().id(request.getIdNhanVien()).build();

            VeTau newVeTau = VeTau.builder()
                    .maVe(request.getMaVe())
                    .khachHang(khachHang)
                    .lichTrinh(lichTrinh)
                    .ghe(ghe)
                    .nhanVien(nhanVien)
                    .giaVe(request.getGiaVe())
                    .trangThai(request.getTrangThai())
                    .build();

            return mapToResponse(repository.save(newVeTau));
        }

        @Override
        public VeTauResponse updateVeTau(VeTauRequest request) {
            checkTrung(request);
            KhachHang khachHang = KhachHang.builder().id(request.getIdKhachHang()).build();
            LichTrinh lichTrinh = LichTrinh.builder().id(request.getIdLichTrinh()).build();
            Ghe ghe = Ghe.builder().id(request.getIdGhe()).build();
            NhanVien nhanVien = NhanVien.builder().id(request.getIdNhanVien()).build();

            VeTau existing = repository.findById(request.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin vé"));

            existing.setKhachHang(khachHang);
            existing.setLichTrinh(lichTrinh);
            existing.setGhe(ghe);
            existing.setNhanVien(nhanVien);
            existing.setGiaVe(request.getGiaVe());
            existing.setTrangThai(request.getTrangThai());

            return mapToResponse(repository.save(existing));
        }

        @Override
        public void deleteVeTau(int id) {
            VeTau veTau = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin vé"));
            repository.delete(veTau);
        }

        @Override
        public List<VeTauResponse> searchVeTau(String maVe) {
            List<VeTau> veTau = repository.findAllByMaVeLike(maVe);
            return veTau.stream()
                    .map(this::mapToResponse)
                    .collect(Collectors.toList());
        }

        @Override
        @Transactional(readOnly = true)
        public double tinhGiaVe(int idLichTrinh, int idGhe) {
            LichTrinh lichTrinh = lichTrinhRepository.findById(idLichTrinh)
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy lịch trình"));
            Ghe ghe = gheRepository.findById(idGhe)
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy ghế"));

            TuyenDuong tuyenDuong = lichTrinh.getIdTuyenDuong();
            ToaTau toaTau = ghe.getIdToaTau();
            LoaiToa loaiToa = toaTau.getIdLoaiToa();

            return tuyenDuong.getGiaCoBan()
                    .multiply(loaiToa.getHeSoGia())
                    .doubleValue();
        }

        @Override
        @Transactional(readOnly = true)
        public double tinhGiaVeByMaVe(int id) {
            VeTau veTau = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy vé với mã: " + id));
            return tinhGiaVe(veTau.getLichTrinh().getId(), veTau.getGhe().getId());
        }

        private void checkTrung(VeTauRequest request) {
            if (repository.existsByMaVeAndIdNot(request.getMaVe(), request.getId())) {
                throw new DuplicateDataException("Mã vé đã tồn tại");
            }
        }

        private VeTauResponse mapToResponse(VeTau veTau) {
            return VeTauResponse.builder()
                    .id(veTau.getId())
                    .maVe(veTau.getMaVe())
                    .idKhachHang(veTau.getKhachHang().getId())
                    .idLichTrinh(veTau.getLichTrinh().getId())
                    .idGhe(veTau.getGhe().getId())
                    .idNhanVien(veTau.getNhanVien().getId())
                    .ngayDatVe(veTau.getNgayDat().toString())
                    .giaVe(veTau.getGiaVe())
                    .trangThai(veTau.getTrangThai())
                    .build();
        }
    }


}
