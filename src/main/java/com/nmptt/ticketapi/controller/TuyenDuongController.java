package com.nmptt.ticketapi.controller;

import com.nmptt.ticketapi.dto.request.TuyenDuongRequest;
import com.nmptt.ticketapi.dto.response.ApiResponse;
import com.nmptt.ticketapi.dto.response.TuyenDuongResponse;
import com.nmptt.ticketapi.service.TuyenDuongService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tuyen_duong")
@AllArgsConstructor
public class TuyenDuongController {
    private TuyenDuongService tuyenDuongService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TuyenDuongResponse>>> getAllTuyenDuong() {
        List<TuyenDuongResponse> data = tuyenDuongService.getAllTuyenDuong();
        ApiResponse<List<TuyenDuongResponse>> response = ApiResponse.<List<TuyenDuongResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy danh sách tuyến đường thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TuyenDuongResponse>> getTuyenDuongByMa(@PathVariable Integer id) {
        TuyenDuongResponse data = tuyenDuongService.getTuyenDuongById(id);
        ApiResponse<TuyenDuongResponse> response = ApiResponse.<TuyenDuongResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy thông tin tuyến đường thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TuyenDuongResponse>> createTuyenDuong(@RequestBody TuyenDuongRequest tuyenDuong) {
        TuyenDuongResponse data = tuyenDuongService.createTuyenDuong(tuyenDuong);
        ApiResponse<TuyenDuongResponse> response = ApiResponse.<TuyenDuongResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Thêm tuyến đường thành công!")
                .data(data)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<TuyenDuongResponse>> updateTuyenDuong(@RequestBody TuyenDuongRequest tuyenDuong) {
        TuyenDuongResponse data = tuyenDuongService.updateTuyenDuong(tuyenDuong);
        ApiResponse<TuyenDuongResponse> response = ApiResponse.<TuyenDuongResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Sửa tuyến đường thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTuyenDuong(@PathVariable Integer id) {
        tuyenDuongService.deleteTuyenDuong(id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .code(HttpStatus.CREATED.value())
                .message("Xóa tuyến đường thành công!")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<TuyenDuongResponse>>> searchTuyenDuong(
            @RequestParam(required = false, defaultValue = "") String key) {
        List<TuyenDuongResponse> data = tuyenDuongService.searchTuyenDuong(key);
        ApiResponse<List<TuyenDuongResponse>> response = ApiResponse.<List<TuyenDuongResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Tìm kiếm tuyến đường thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

}
