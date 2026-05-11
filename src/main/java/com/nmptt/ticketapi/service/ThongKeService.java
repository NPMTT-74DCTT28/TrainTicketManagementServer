package com.nmptt.ticketapi.service;

import com.nmptt.ticketapi.dto.thongke.*;
import com.nmptt.ticketapi.repository.ThongKeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface ThongKeService {
    List<DoanhThuBayNgayDTO> getDoanhThuBayNgay();

    List<DoanhThuTheoNgayDTO> getDoanhThuTheoNgay(LocalDate tuNgay, LocalDate denNgay);

    List<DoanhThuTheoTuyenDTO> getDoanhThuTheoTuyen(LocalDate tuNgay, LocalDate denNgay);

    List<TyLeLapDayDTO> getTyLeLapDay(LocalDate tuNgay, LocalDate denNgay);

    List<KhachHangVipDTO> getKhachHangVip(int soLuong);

    List<DoanhSoDTO> getDoanhSo(int thang, int nam);

    @Service
    @RequiredArgsConstructor
    class ThongKeServiceImpl implements ThongKeService {
        private final ThongKeRepository repository;

        @Override
        public List<DoanhThuBayNgayDTO> getDoanhThuBayNgay() {
            return repository.getDoanhThuBayNgay();
        }

        @Override
        public List<DoanhThuTheoNgayDTO> getDoanhThuTheoNgay(LocalDate tuNgay, LocalDate denNgay) {
            if (tuNgay.isAfter(denNgay)) {
                throw new IllegalArgumentException("Ngày bắt đầu không được lớn hơn ngày kết thúc.");
            }

            return repository.getDoanhThuTheoNgay(tuNgay, denNgay);
        }

        @Override
        public List<DoanhThuTheoTuyenDTO> getDoanhThuTheoTuyen(LocalDate tuNgay, LocalDate denNgay) {
            if (tuNgay.isAfter(denNgay)) {
                throw new IllegalArgumentException("Ngày bắt đầu không được lớn hơn ngày kết thúc.");
            }

            return repository.getDoanhThuTheoTuyen(tuNgay, denNgay);
        }

        @Override
        public List<TyLeLapDayDTO> getTyLeLapDay(LocalDate tuNgay, LocalDate denNgay) {
            if (tuNgay.isAfter(denNgay)) {
                throw new IllegalArgumentException("Ngày bắt đầu không được lớn hơn ngày kết thúc.");
            }

            return repository.getTyLeLapDay(tuNgay, denNgay);
        }

        @Override
        public List<KhachHangVipDTO> getKhachHangVip(int soLuong) {
            if (soLuong < 1) {
                throw new IllegalArgumentException("Số lượng phải từ 1 trở lên.");
            }

            return repository.getKhachHangVip(soLuong);
        }

        @Override
        public List<DoanhSoDTO> getDoanhSo(int thang, int nam) {
            int currentYear = LocalDate.now().getYear();
            int currentMonth = LocalDate.now().getMonthValue();

            if (thang < 1 || thang > 12)
                throw new IllegalArgumentException("Tháng phải từ 1-12.");

            if (nam > currentYear || (nam == currentYear && thang > currentMonth))
                throw new IllegalArgumentException("Không thể thống kê dữ liệu trong tương lai.");

            return repository.getDoanhSo(thang, nam);
        }
    }
}
