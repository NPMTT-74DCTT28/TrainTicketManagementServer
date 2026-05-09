package com.nmptt.ticketapi.controller;

import com.nmptt.ticketapi.dto.request.VeTauRequest;
import com.nmptt.ticketapi.dto.response.ApiResponse;
import com.nmptt.ticketapi.dto.response.VeTauResponse;
import com.nmptt.ticketapi.service.VeTauService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ve-tau")
public class VeTauController {
    private final VeTauService veTauService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<VeTauResponse>>> getAllVeTau() {
        List<VeTauResponse> data = veTauService.getAllVeTau();

        ApiResponse<List<VeTauResponse>> response = ApiResponse.<List<VeTauResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy danh sách vé tàu thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VeTauResponse>> getVeTauById(@PathVariable int id) {
        VeTauResponse data = veTauService.getVeTauById(id);

        ApiResponse<VeTauResponse> response = ApiResponse.<VeTauResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy thông tin vé tàu thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VeTauResponse>> createVeTau(@RequestBody VeTauRequest request) {
        VeTauResponse data = veTauService.createVeTau(request);

        ApiResponse<VeTauResponse> response = ApiResponse.<VeTauResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Thêm thông tin vé tàu thành công!")
                .data(data)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<VeTauResponse>> updateVeTau(@RequestBody VeTauRequest request) {
        VeTauResponse data = veTauService.updateVeTau(request);

        ApiResponse<VeTauResponse> response = ApiResponse.<VeTauResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Cập nhật thông tin vé tàu thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteVeTau(@PathVariable int id) {
        veTauService.deleteVeTau(id);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Xóa vé tàu thành công!")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<VeTauResponse>>> searchVeTau(
            @RequestParam(required = false, defaultValue = "") String maVe) {
        String keyword = "%" + maVe + "%";
        List<VeTauResponse> data = veTauService.searchVeTau(keyword);

        ApiResponse<List<VeTauResponse>> response = ApiResponse.<List<VeTauResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Tìm kiếm vé tàu thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/gia-ve")
    public ResponseEntity<ApiResponse<Double>> tinhGiaVe(
            @RequestParam int idLichTrinh,
            @RequestParam int idGhe) {
        double gia = veTauService.tinhGiaVe(idLichTrinh, idGhe);
        ApiResponse<Double> response = ApiResponse.<Double>builder()
                .code(HttpStatus.OK.value())
                .message("Tính giá vé thành công")
                .data(gia)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/gia-ve/{id}")
    public ResponseEntity<ApiResponse<Double>> tinhGiaTheoMaVe(@PathVariable int id) {
        double gia = veTauService.tinhGiaVeByMaVe(id);
        ApiResponse<Double> response = ApiResponse.<Double>builder()
                .code(HttpStatus.OK.value())
                .message("Tính giá vé từ mã vé thành công")
                .data(gia)
                .build();
        return ResponseEntity.ok(response);
    }
}
