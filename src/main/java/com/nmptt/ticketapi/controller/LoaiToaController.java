package com.nmptt.ticketapi.controller;

import com.nmptt.ticketapi.dto.response.ApiResponse;
import com.nmptt.ticketapi.entity.LoaiToa;
import com.nmptt.ticketapi.service.LoaiToaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/loai-toa")
@PreAuthorize("hasRole('Quản trị viên')")
public class LoaiToaController {
    private final LoaiToaService loaiToaService;

    @GetMapping
    @PreAuthorize("hasAnyRole('Quản trị viên', 'Nhân viên')")
    public ResponseEntity<ApiResponse<List<LoaiToa>>> getAll() {
        List<LoaiToa> data = loaiToaService.getAllLoaiToa();
        ApiResponse<List<LoaiToa>> response = ApiResponse.<List<LoaiToa>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy danh sách loại toa thành công")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LoaiToa>> getById(@PathVariable Integer id) {
        LoaiToa data = loaiToaService.getLoaiToaById(id);
        ApiResponse<LoaiToa> response = ApiResponse.<LoaiToa>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy thông tin loại toa thành công")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<LoaiToa>> create(@RequestBody LoaiToa loaiToa) {
        LoaiToa data = loaiToaService.insertLoaiToa(loaiToa);
        ApiResponse<LoaiToa> response = ApiResponse.<LoaiToa>builder()
                .code(HttpStatus.CREATED.value())
                .message("Thêm mới loại toa thành công")
                .data(data)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<LoaiToa>> update(@RequestBody LoaiToa loaiToa) {
        LoaiToa data = loaiToaService.updateLoaiToa(loaiToa);
        ApiResponse<LoaiToa> response = ApiResponse.<LoaiToa>builder()
                .code(HttpStatus.OK.value())
                .message("Cập nhật loại toa thành công")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        loaiToaService.delete(id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Xóa loại toa thành công")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<LoaiToa>>> search(
            @RequestParam(required = false, defaultValue = "") String key,
            @RequestParam(required = false) BigDecimal heSo) {

        String searchKey = "%" + key + "%";
        List<LoaiToa> data = loaiToaService.searchLoaiToa(searchKey, heSo);

        ApiResponse<List<LoaiToa>> response = ApiResponse.<List<LoaiToa>>builder()
                .code(HttpStatus.OK.value())
                .message("Tìm kiếm loại toa thành công")
                .data(data)
                .build();

        return ResponseEntity.ok(response);
    }
}