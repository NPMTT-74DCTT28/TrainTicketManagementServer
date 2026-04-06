package com.nmptt.ticketapi.controller;

import com.nmptt.ticketapi.dto.request.ChangePasswordRequest;
import com.nmptt.ticketapi.dto.request.LoginRequest;
import com.nmptt.ticketapi.dto.response.ApiResponse;
import com.nmptt.ticketapi.dto.response.NhanVienResponse;
import com.nmptt.ticketapi.service.NhanVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final NhanVienService nhanVienService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<NhanVienResponse>> login(@RequestBody LoginRequest loginRequest) {
        NhanVienResponse data = nhanVienService.login(loginRequest);

        ApiResponse<NhanVienResponse> apiResponse = ApiResponse.<NhanVienResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Đăng nhập thành công!")
                .data(data)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/change-pw")
    public ResponseEntity<ApiResponse<Void>> changePw(@RequestBody ChangePasswordRequest request) {
        nhanVienService.changePassword(request);

        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Đổi mật khẩu thành công!")
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}
