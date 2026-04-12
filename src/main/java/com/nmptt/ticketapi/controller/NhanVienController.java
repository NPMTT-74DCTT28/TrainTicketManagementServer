package com.nmptt.ticketapi.controller;

import com.nmptt.ticketapi.dto.request.NhanVienRequest;
import com.nmptt.ticketapi.dto.response.ApiResponse;
import com.nmptt.ticketapi.dto.response.NhanVienResponse;
import com.nmptt.ticketapi.service.NhanVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/nhan-vien")
@PreAuthorize("hasRole('Quản trị viên')")
public class NhanVienController {
    private final NhanVienService nhanVienService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<NhanVienResponse>>> getAllNhanVien() {
        List<NhanVienResponse> data = nhanVienService.getAllNhanVien();

        ApiResponse<List<NhanVienResponse>> response = ApiResponse.<List<NhanVienResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy danh sách nhân viên thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NhanVienResponse>> getNhanVienById(@PathVariable int id) {
        NhanVienResponse data = nhanVienService.getNhanVienById(id);

        ApiResponse<NhanVienResponse> response = ApiResponse.<NhanVienResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy thông tin nhân viên thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<NhanVienResponse>> createNhanVien(@RequestBody NhanVienRequest request) {
        NhanVienResponse data = nhanVienService.createNhanVien(request);

        ApiResponse<NhanVienResponse> response = ApiResponse.<NhanVienResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Thêm thông tin nhân viên thành công!")
                .data(data)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<NhanVienResponse>> updateNhanVien(@RequestBody NhanVienRequest request) {
        NhanVienResponse data = nhanVienService.updateNhanVien(request);

        ApiResponse<NhanVienResponse> response = ApiResponse.<NhanVienResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Cập nhật thông tin nhân viên thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteNhanVien(@PathVariable int id) {
        nhanVienService.deleteNhanVien(id);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Xoá nhân viên thành công!")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<NhanVienResponse>>> searchNhanVien(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String gioiTinh,
            @RequestParam(required = false) String vaiTro) {
        List<NhanVienResponse> data = nhanVienService.searchNhanVien(keyword, gioiTinh, vaiTro);

        ApiResponse<List<NhanVienResponse>> response = ApiResponse.<List<NhanVienResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Tìm kiếm nhân viên thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }
}
