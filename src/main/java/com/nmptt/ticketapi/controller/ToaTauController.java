package com.nmptt.ticketapi.controller;

import com.nmptt.ticketapi.dto.request.ToaTauRequest;
import com.nmptt.ticketapi.dto.response.ApiResponse;
import com.nmptt.ticketapi.dto.response.ToaTauResponse;
import com.nmptt.ticketapi.service.ToaTauService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/toa-tau")
@AllArgsConstructor
public class ToaTauController {

    private final ToaTauService toaTauService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ToaTauResponse>>> getAllToaTau() {
        List<ToaTauResponse> data = toaTauService.getAllToaTau();
        ApiResponse<List<ToaTauResponse>> response = ApiResponse.<List<ToaTauResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy danh sách toa tàu thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ToaTauResponse>> getToaTauByMa(@PathVariable Integer id) {
        ToaTauResponse data = toaTauService.getToaTauById(id);
        ApiResponse<ToaTauResponse> response = ApiResponse.<ToaTauResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy thông tin toa tàu thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ToaTauResponse>> createToaTau(@RequestBody ToaTauRequest toaTau) {
        ToaTauResponse data = toaTauService.createToaTau(toaTau);
        ApiResponse<ToaTauResponse> response = ApiResponse.<ToaTauResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Thêm toa tàu thành công!")
                .data(data)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<ToaTauResponse>> updateToaTau(@RequestBody ToaTauRequest toaTau) {
        ToaTauResponse data = toaTauService.updateToaTau(toaTau);
        ApiResponse<ToaTauResponse> response = ApiResponse.<ToaTauResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Cập nhật toa tàu thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteToaTau(@PathVariable Integer id) {
        toaTauService.deleteToaTau(id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Xóa toa tàu thành công!")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ToaTauResponse>>> searchToaTau(
            @RequestParam(required = false, defaultValue = "") String key) {
        List<ToaTauResponse> data = toaTauService.searchToaTau(key);
        ApiResponse<List<ToaTauResponse>> response = ApiResponse.<List<ToaTauResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Tìm kiếm toa tàu thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }
}