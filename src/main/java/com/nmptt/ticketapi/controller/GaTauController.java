package com.nmptt.ticketapi.controller;

import com.nmptt.ticketapi.dto.response.ApiResponse;
import com.nmptt.ticketapi.entity.GaTau;
import com.nmptt.ticketapi.service.GaTauService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ga_tau")
@PreAuthorize("hasRole('Quản trị viên')")
public class GaTauController {
    private final GaTauService gaTauService;

    @GetMapping
    @PreAuthorize("hasAnyRole('Quản trị viên', 'Nhân viên')")
    public ResponseEntity<ApiResponse<List<GaTau>>> getAllGaTau() {
        List<GaTau> data = gaTauService.getAllGaTau();
        ApiResponse<List<GaTau>> response = ApiResponse.<List<GaTau>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy danh sách ga tàu thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GaTau>> getGaTauByMa(@PathVariable Integer id) {
        GaTau data = gaTauService.getGaTauById(id);
        ApiResponse<GaTau> response = ApiResponse.<GaTau>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy thông tin ga tàu thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<GaTau>> createGaTau(@RequestBody GaTau gaTau) {
        GaTau data = gaTauService.createGaTau(gaTau);
        ApiResponse<GaTau> response = ApiResponse.<GaTau>builder()
                .code(HttpStatus.CREATED.value())
                .message("Thêm mới ga tàu thành công!")
                .data(data)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<GaTau>> updateGaTau(@RequestBody GaTau gaTau) {
        GaTau data = gaTauService.updateGaTau(gaTau);
        ApiResponse<GaTau> response = ApiResponse.<GaTau>builder()
                .code(HttpStatus.OK.value())
                .message("Cập nhật ga tàu thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteGaTau(@PathVariable Integer id) {
        gaTauService.deleteGaTau(id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Xóa ga tàu thành công!")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('Quản trị viên', 'Nhân viên')")
    public ResponseEntity<ApiResponse<List<GaTau>>> searchGaTau(
            @RequestParam(required = false, defaultValue = "") String key) {
        List<GaTau> data = gaTauService.searchGaTau(key);
        ApiResponse<List<GaTau>> response = ApiResponse.<List<GaTau>>builder()
                .code(HttpStatus.OK.value())
                .message("Tìm kiếm ga tàu thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

}
