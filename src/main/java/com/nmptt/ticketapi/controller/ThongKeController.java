package com.nmptt.ticketapi.controller;

import com.nmptt.ticketapi.dto.response.ApiResponse;
import com.nmptt.ticketapi.dto.thongke.*;
import com.nmptt.ticketapi.service.ThongKeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/thong-ke")
@PreAuthorize("hasRole('Quản trị viên')")
public class ThongKeController {
    private final ThongKeService thongKeService;

    @PreAuthorize("hasAnyRole('Quản trị viên', 'Nhân viên')")
    @GetMapping("/doanh-thu-7d")
    public ResponseEntity<ApiResponse<List<DoanhThuBayNgayDTO>>> getDoanhThuBayNgay() {
        List<DoanhThuBayNgayDTO> data = thongKeService.getDoanhThuBayNgay();

        ApiResponse<List<DoanhThuBayNgayDTO>> response = ApiResponse.<List<DoanhThuBayNgayDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy dữ liệu thống kê thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/doanh-thu-ngay")
    public ResponseEntity<ApiResponse<List<DoanhThuTheoNgayDTO>>> getDoanhThuTheoNgay(
            @RequestParam LocalDate ngayBatDau,
            @RequestParam LocalDate ngayKetThuc) {
        List<DoanhThuTheoNgayDTO> data = thongKeService.getDoanhThuTheoNgay(ngayBatDau, ngayKetThuc);

        ApiResponse<List<DoanhThuTheoNgayDTO>> response = ApiResponse.<List<DoanhThuTheoNgayDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy dữ liệu thống kê thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/doanh-thu-tuyen")
    public ResponseEntity<ApiResponse<List<DoanhThuTheoTuyenDTO>>> getDoanhThuTheoTuyen(
            @RequestParam LocalDate ngayBatDau,
            @RequestParam LocalDate ngayKetThuc) {
        List<DoanhThuTheoTuyenDTO> data = thongKeService.getDoanhThuTheoTuyen(ngayBatDau, ngayKetThuc);

        ApiResponse<List<DoanhThuTheoTuyenDTO>> response = ApiResponse.<List<DoanhThuTheoTuyenDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy dữ liệu thống kê thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ty-le-lap-day")
    public ResponseEntity<ApiResponse<List<TyLeLapDayDTO>>> getTyLeLapDay(
            @RequestParam LocalDate ngayBatDau,
            @RequestParam LocalDate ngayKetThuc) {
        List<TyLeLapDayDTO> data = thongKeService.getTyLeLapDay(ngayBatDau, ngayKetThuc);

        ApiResponse<List<TyLeLapDayDTO>> response = ApiResponse.<List<TyLeLapDayDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy dữ liệu thống kê thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/khach-hang-than-thiet")
    public ResponseEntity<ApiResponse<List<KhachHangVipDTO>>> getKhachHangVip(@RequestParam int soLuong) {
        List<KhachHangVipDTO> data = thongKeService.getKhachHangVip(soLuong);

        ApiResponse<List<KhachHangVipDTO>> response = ApiResponse.<List<KhachHangVipDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy dữ liệu thống kê thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/doanh-so")
    public ResponseEntity<ApiResponse<List<DoanhSoDTO>>> getDoanhSo(
            @RequestParam int thang,
            @RequestParam int nam) {
        List<DoanhSoDTO> data = thongKeService.getDoanhSo(thang, nam);

        ApiResponse<List<DoanhSoDTO>> response = ApiResponse.<List<DoanhSoDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy dữ liệu thống kê thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }
}
