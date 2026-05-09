package com.nmptt.ticketapi.controller;

import com.nmptt.ticketapi.dto.request.LichTrinhRequest;
import com.nmptt.ticketapi.dto.response.ApiResponse;
import com.nmptt.ticketapi.dto.response.LichTrinhResponse;
import com.nmptt.ticketapi.service.LichTrinhService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lich-trinh")
@PreAuthorize("hasAnyRole('Quản trị viên', 'Nhân viên')")
public class LichTrinhController {
    private final LichTrinhService lichTrinhService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<LichTrinhResponse>>> getAllLichTrinh() {
        List<LichTrinhResponse> data = lichTrinhService.getAllLichTrinh();
        ApiResponse<List<LichTrinhResponse>> response = ApiResponse.<List<LichTrinhResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy danh sách lịch trình thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LichTrinhResponse>> getLichTrinhByMa(@PathVariable Integer id) {
        LichTrinhResponse data = lichTrinhService.getLichTrinhById(id);
        ApiResponse<LichTrinhResponse> response = ApiResponse.<LichTrinhResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy thông tin lịch trình thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<LichTrinhResponse>> createLichTrinh(@RequestBody LichTrinhRequest lichTrinh) {
        LichTrinhResponse data = lichTrinhService.createLichTrinh(lichTrinh);
        ApiResponse<LichTrinhResponse> response = ApiResponse.<LichTrinhResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Thêm lịch trình thành công!")
                .data(data)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<LichTrinhResponse>> updateLichTrinh(@RequestBody LichTrinhRequest lichTrinh) {
        LichTrinhResponse data = lichTrinhService.updateLichTrinh(lichTrinh);
        ApiResponse<LichTrinhResponse> response = ApiResponse.<LichTrinhResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Sửa lịch trình thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteLichTrinh(@PathVariable Integer id) {
        lichTrinhService.deleteLichTrinh(id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .code(HttpStatus.CREATED.value())
                .message("Xóa lịch trình thành công!")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<LichTrinhResponse>>> searchLichTrinh(
            @RequestParam(required = false, defaultValue = "") String key) {
        List<LichTrinhResponse> data = lichTrinhService.searchLichTrinh(key);
        ApiResponse<List<LichTrinhResponse>> response = ApiResponse.<List<LichTrinhResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Tìm kiếm lịch trình thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }
}
