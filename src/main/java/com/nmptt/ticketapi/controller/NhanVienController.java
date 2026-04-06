package com.nmptt.ticketapi.controller;

import com.nmptt.ticketapi.dto.request.NhanVienRequest;
import com.nmptt.ticketapi.dto.response.ApiResponse;
import com.nmptt.ticketapi.dto.response.NhanVienResponse;
import com.nmptt.ticketapi.service.NhanVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/nhan-vien")
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

    @GetMapping("/{maNhanVien}")
    public ResponseEntity<ApiResponse<NhanVienResponse>> getNhanVienByMa(@PathVariable String maNhanVien) {
        NhanVienResponse data = nhanVienService.getNhanVienByMa(maNhanVien);

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
                .code(HttpStatus.OK.value())
                .message("Thêm thông tin nhân viên thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }
}
