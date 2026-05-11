package com.nmptt.ticketapi.controller;

import com.nmptt.ticketapi.dto.response.ApiResponse;
import com.nmptt.ticketapi.entity.KhachHang;
import com.nmptt.ticketapi.service.KhachHangService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/khach-hang")
@PreAuthorize("hasAnyRole('Quản trị viên', 'Nhân viên')")
public class KhachHangController {
    private final KhachHangService khachHangService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<KhachHang>>> getAllKhachHang() {
        List<KhachHang> data = khachHangService.getAllKhachHang();

        ApiResponse<List<KhachHang>> response = ApiResponse.<List<KhachHang>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy danh sách khách hàng thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<KhachHang>> getKhachHangByMa(@PathVariable int id) {
        KhachHang data = khachHangService.getKhachHangById(id);

        ApiResponse<KhachHang> response = ApiResponse.<KhachHang>builder()
                .code((HttpStatus.OK.value()))
                .message("Lấy thông tin khách hàng thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<KhachHang>> createKhachHang(@RequestBody KhachHang request) {
        KhachHang data = khachHangService.createKhachHang(request);

        ApiResponse<KhachHang> response = ApiResponse.<KhachHang>builder()
                .code(HttpStatus.CREATED.value())
                .message("Thêm thông tin khách hàng thành công!")
                .data(data)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<KhachHang>> updateKhachHang(@RequestBody KhachHang request) {
        KhachHang data = khachHangService.updateKhachHang(request);

        ApiResponse<KhachHang> response = ApiResponse.<KhachHang>builder()
                .code(HttpStatus.OK.value())
                .message("Cập nhật thông tin khách hàng thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteKhachHang(@PathVariable int id) {
        khachHangService.deleteKhachHang(id);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Xóa khách hàng thành công!")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<KhachHang>>> searchKhachHang(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String gioiTinh) {
        List<KhachHang> data = khachHangService.searchKhachHang(keyword, gioiTinh);

        ApiResponse<List<KhachHang>> response = ApiResponse.<List<KhachHang>>builder()
                .code(HttpStatus.OK.value())
                .message("Tìm kiếm khách hàng thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }
}
