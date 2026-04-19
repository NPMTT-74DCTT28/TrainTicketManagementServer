package com.nmptt.ticketapi.service;

import com.nmptt.ticketapi.entity.KhachHang;
import com.nmptt.ticketapi.exception.DuplicateDataException;
import com.nmptt.ticketapi.exception.ResourceNotFoundException;
import com.nmptt.ticketapi.repository.KhachHangRepository;
import com.nmptt.ticketapi.specification.KhachHangSpecification;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

public interface KhachHangService {
    List<KhachHang> getAllKhachHang();

    KhachHang getKhachHangById(int id);

    KhachHang createKhachHang(KhachHang khachHangRequest);

    KhachHang updateKhachHang(KhachHang khachHangRequest);

    void deleteKhachHang(int id);

    List<KhachHang> searchKhachHang(String keyword, String gioiTinh);

    @Service
    @RequiredArgsConstructor
    class KhachHangServiceImpl implements KhachHangService {
        private final KhachHangRepository repository;

        @Override
        public List<KhachHang> getAllKhachHang() {

            List<KhachHang> customers = repository.findAll();
            return customers.stream()
                    .map(this::mapToResponse)
                    .collect(Collectors.toList());
        }

        public KhachHang getKhachHangById(int id) {
            KhachHang khachHang = repository.findById(id)
                    .orElseThrow(()-> new ResourceNotFoundException("Không tìm thấy thông tin khách hàng"));
            return mapToResponse(khachHang);
        }

        @Override
        public KhachHang createKhachHang(KhachHang request) {
            checkTrung(request);

            KhachHang newKhachHang = KhachHang.builder()
                    .cccd(request.getCccd())
                    .hoTen(request.getHoTen())
                    .ngaySinh(request.getNgaySinh())
                    .gioiTinh(request.getGioiTinh())
                    .sdt(request.getSdt())
                    .diaChi(request.getDiaChi())
                    .build();

            return mapToResponse(repository.save(newKhachHang));
        }

        @Override
        public KhachHang updateKhachHang(KhachHang request) {
            checkTrung(request);

            KhachHang existing = repository.findById(request.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin khách hàng"));

            existing.setHoTen(request.getHoTen());
            existing.setNgaySinh(request.getNgaySinh());
            existing.setGioiTinh(request.getGioiTinh());
            existing.setSdt(request.getSdt());
            existing.setDiaChi(request.getDiaChi());

            return mapToResponse(repository.save(existing));
        }

        @Override
        public void deleteKhachHang(int id) {
            KhachHang khachHang = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin khách hàng"));
            repository.delete(khachHang);
        }

        @Override
        public List<KhachHang> searchKhachHang(String keyword, String gioiTinh) {
            List<KhachHang> KhachHang = repository.findAll(KhachHangSpecification.filter(keyword, gioiTinh));
            return KhachHang.stream()
                    .map(this::mapToResponse)
                    .collect(Collectors.toList());
        }


        private void checkTrung(KhachHang request) {
            if (repository.existsByCccdAndIdNot(request.getCccd(), request.getId())) {
                throw new DuplicateDataException("Cccd đã tồn tại");
            }
            if (repository.existsBySdtAndIdNot(request.getSdt(), request.getId())) {
                throw new DuplicateDataException("SĐT đã được sử dụng.");
            }
        }

        private KhachHang mapToResponse(KhachHang khachHang) {
            return KhachHang.builder()
                    .id(khachHang.getId())
                    .cccd(khachHang.getCccd())
                    .hoTen(khachHang.getHoTen())
                    .ngaySinh(khachHang.getNgaySinh())
                    .gioiTinh(khachHang.getGioiTinh())
                    .sdt(khachHang.getSdt())
                    .diaChi(khachHang.getDiaChi())
                    .build();
        }
    }
}
