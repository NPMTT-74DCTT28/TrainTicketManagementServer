# API — Hệ thống quản lý bán vé tàu (`TrainTicketManagementAPI`)

REST API phiên bản **v1** cho phần mềm máy trạm Swing và công cụ kiểm thử (Postman, v.v.).

## 1. Thông tin chung

| Hạng mục             | Giá trị                                                               |
|----------------------|-----------------------------------------------------------------------|
| Base URL             | `http://localhost:8080/api/v1` *(cấu hình cổng theo Spring Boot)*     |
| Định dạng            | JSON (`Content-Type: application/json`)                               |
| Gói lời đáp phổ biến | `ApiResponse<T>` — `{ "code": number, "message": string, "data": T }` |

### 1.1. Xác thực

- **`POST .../auth/login`** — **không** cần token.
- Mọi endpoint khác: gửi header  
  `Authorization: Bearer <token>`  
  (token nhận sau khi đăng nhập, trường `data.token` trong JSON phản hồi).

### 1.2. Phân quyền (Spring `@PreAuthorize`)

- Vai trò trong JWT được ánh xạ dạng `ROLE_<vaiTro>` (ví dụ `ROLE_Quản trị viên`).
- **`NhanVienController`**: toàn bộ lớp `hasRole('Quản trị viên')`, **ngoại lệ** `PUT /nhan-vien/personal-info` cho
  `Quản trị viên` **hoặc** `Nhân viên`.
- **`ThongKeController`**: mặc định lớp `hasRole('Quản trị viên')`, **ngoại lệ** `GET /thong-ke/doanh-thu-7d` cho cả
  `Nhân viên`.
- Các controller còn lại: chỉ cần **đã đăng nhập** (JWT hợp lệ), không gắn `@PreAuthorize` riêng.

### 1.3. Lưu ý định dạng đường dẫn

- Một số resource dùng gạch dưới trong path: **`/ga_tau`**, **`/tuyen_duong`** (khớp `@RequestMapping` trong code).
- **`GheController`** trả về trực tiếp `List<GheResponse>` / `GheResponse` / `String` — **không** bọc `ApiResponse` (
  khác với phần lớn API khác).

---

## 2. Bảng tổng hợp endpoint

*(Tất cả đường dẫn dưới đây nối sau base `.../api/v1` trừ khi ghi đầy đủ.)*

### 2.1. Xác thực

| Method | Path              | Token | Vai trò      |
|--------|-------------------|-------|--------------|
| POST   | `/auth/login`     | Không | Công khai    |
| POST   | `/auth/change-pw` | Có    | Đã đăng nhập |

### 2.2. Nhân viên

| Method | Path                       | Token | Vai trò                  |
|--------|----------------------------|-------|--------------------------|
| GET    | `/nhan-vien`               | Có    | Quản trị viên            |
| GET    | `/nhan-vien/{id}`          | Có    | Quản trị viên            |
| POST   | `/nhan-vien`               | Có    | Quản trị viên            |
| PUT    | `/nhan-vien`               | Có    | Quản trị viên            |
| PUT    | `/nhan-vien/personal-info` | Có    | Quản trị viên, Nhân viên |
| DELETE | `/nhan-vien/{id}`          | Có    | Quản trị viên            |
| GET    | `/nhan-vien/search`        | Có    | Quản trị viên            |

Query `GET /nhan-vien/search`: `keyword`, `gioiTinh`, `vaiTro` (tùy chọn).

### 2.3. Thống kê

| Method | Path                              | Token | Vai trò                      |
|--------|-----------------------------------|-------|------------------------------|
| GET    | `/thong-ke/doanh-thu-7d`          | Có    | Quản trị viên, **Nhân viên** |
| GET    | `/thong-ke/doanh-thu-ngay`        | Có    | Quản trị viên                |
| GET    | `/thong-ke/doanh-thu-tuyen`       | Có    | Quản trị viên                |
| GET    | `/thong-ke/ty-le-lap-day`         | Có    | Quản trị viên                |
| GET    | `/thong-ke/khach-hang-than-thiet` | Có    | Quản trị viên                |
| GET    | `/thong-ke/doanh-so`              | Có    | Quản trị viên                |

Query:

- `doanh-thu-ngay`, `doanh-thu-tuyen`, `ty-le-lap-day`: `ngayBatDau`, `ngayKetThuc` (`LocalDate`, ví dụ `2026-05-01`).
- `khach-hang-than-thiet`: `soLuong` (int).
- `doanh-so`: `thang`, `nam` (int).

### 2.4. Vé tàu

| Method | Path                  | Token |
|--------|-----------------------|-------|
| GET    | `/ve-tau`             | Có    |
| GET    | `/ve-tau/{id}`        | Có    |
| POST   | `/ve-tau`             | Có    |
| PUT    | `/ve-tau`             | Có    |
| DELETE | `/ve-tau/{id}`        | Có    |
| GET    | `/ve-tau/search`      | Có    |
| GET    | `/ve-tau/gia-ve`      | Có    |
| GET    | `/ve-tau/gia-ve/{id}` | Có    |

Query `GET /ve-tau/search`: `maVe` (optional, default `""`).  
Query `GET /ve-tau/gia-ve`: `idLichTrinh`, `idGhe` (int).

### 2.5. Các module CRUD chuẩn

Chung pattern (đều cần token): `GET` danh sách, `GET /{id}`, `POST`, `PUT`, `DELETE /{id}`, và `GET /search` với tham số
như bảng dưới.

| Module      | Base path      | Tham số search                   |
|-------------|----------------|----------------------------------|
| Ga tàu      | `/ga_tau`      | `key`                            |
| Tuyến đường | `/tuyen_duong` | `key`                            |
| Tàu         | `/tau`         | `key`                            |
| Loại toa    | `/loai-toa`    | `key`, `heSo` (optional)         |
| Toa tàu     | `/toa-tau`     | `key`                            |
| Ghế         | `/ghe`         | `keyword`                        |
| Lịch trình  | `/lich-trinh`  | `key`                            |
| Khách hàng  | `/khach-hang`  | `keyword`, `gioiTinh` (optional) |

Body `POST`/`PUT`:

- Ga tàu, Khách hàng, Tàu (một số): entity tương ứng trong `com.nmptt.ticketapi.entity`.
- Ghế: `GheRequest`.
- Các module còn lại: xem DTO trong `com.nmptt.ticketapi.dto`.

---

## 3. Chi tiết: xác thực

### 3.1. Đăng nhập

- **POST** `/api/v1/auth/login`
- **Token:** không
- **Body** (`LoginRequest`):

```json
{
  "maNhanVien": "NV001",
  "matKhau": "your-password"
}
```

- **Response 200** — `ApiResponse<NhanVienResponse>`:

```json
{
  "code": 200,
  "message": "Đăng nhập thành công!",
  "data": {
    "id": 1,
    "maNhanVien": "NV001",
    "hoTen": "Nguyễn Văn A",
    "ngaySinh": "1980-01-01",
    "gioiTinh": "Nam",
    "sdt": "0123456789",
    "email": "example@domain.com",
    "diaChi": "Địa chỉ",
    "vaiTro": "Quản trị viên",
    "token": "<JWT>"
  }
}
```

Dùng giá trị `data.token` làm `Bearer` cho các request sau.

### 3.2. Đổi mật khẩu

- **POST** `/api/v1/auth/change-pw`
- **Token:** có
- **Body** (`ChangePasswordRequest`):

```json
{
  "id": 1,
  "oldPassword": "mật khẩu cũ",
  "newPassword": "mật khẩu mới"
}
```

- **Response 200:**

```json
{
  "code": 200,
  "message": "Đổi mật khẩu thành công!",
  "data": null
}
```

---

## 4. Chi tiết: nhân viên

### 4.1. Danh sách / theo ID / xóa / tìm kiếm

- **GET** `/api/v1/nhan-vien` — 200, message: *Lấy danh sách nhân viên thành công!*
- **GET** `/api/v1/nhan-vien/{id}` — 200, message: *Lấy thông tin nhân viên thành công!*
- **DELETE** `/api/v1/nhan-vien/{id}` — 200, message: *Xoá nhân viên thành công!*, `data`: null
- **GET** `/api/v1/nhan-vien/search?keyword=&gioiTinh=&vaiTro=` — 200

Tất cả: **Quản trị viên**.

### 4.2. Thêm nhân viên

- **POST** `/api/v1/nhan-vien`
- **Quyền:** Quản trị viên
- **HTTP:** **201 Created** (body `code` cũng là 201)
- **Body** (`NhanVienRequest`): gồm `maNhanVien`, `matKhau`, `hoTen`, `ngaySinh`, `gioiTinh`, `sdt`, `email`, `diaChi`,
  `vaiTro`, …

### 4.3. Cập nhật (admin)

- **PUT** `/api/v1/nhan-vien`
- **Quyền:** Quản trị viên
- **Body:** `NhanVienRequest` (có `id`, …) — cập nhật hồ sơ theo quyền quản trị.

### 4.4. Cập nhật thông tin cá nhân

- **PUT** `/api/v1/nhan-vien/personal-info`
- **Quyền:** Quản trị viên **hoặc** Nhân viên
- **Body:** `NhanVienRequest` — logic nghiệp vụ trong service (không đổi mật khẩu qua endpoint này nếu không được
  service hỗ trợ; đổi mật khẩu dùng `/auth/change-pw`).

---

## 5. Chi tiết: vé tàu

- **POST** `/api/v1/ve-tau` — **201 Created**
- **Body** (`VeTauRequest`): `id`, `maVe`, `idKhachHang`, `idLichTrinh`, `idGhe`, `idNhanVien`, `giaVe`, `trangThai`
- **Response** (`VeTauResponse`): `id`, `maVe`, các id tham chiếu, `ngayDatVe`, `giaVe`, `trangThai`

**Tính giá vé:**

- **GET** `/api/v1/ve-tau/gia-ve?idLichTrinh=1&idGhe=1` → `ApiResponse<Double>`
- **GET** `/api/v1/ve-tau/gia-ve/{id}` — `id` là id vé; tính lại giá theo nghiệp vụ service

---

## 6. Chi tiết: thống kê (DTO)

Phản hồi đều bọc `ApiResponse<List<...>>` (trừ khi lỗi). Một số DTO:

| Endpoint                | Kiểu phần tử `data`                                        |
|-------------------------|------------------------------------------------------------|
| `doanh-thu-7d`          | `DoanhThuBayNgayDTO` — `ngay`, `doanhThu`                  |
| `doanh-thu-ngay`        | `DoanhThuTheoNgayDTO`                                      |
| `doanh-thu-tuyen`       | `DoanhThuTheoTuyenDTO`                                     |
| `ty-le-lap-day`         | `TyLeLapDayDTO` — `maLichTrinh`, `tenTau`, `tyLeLapDay`    |
| `khach-hang-than-thiet` | `KhachHangVipDTO`                                          |
| `doanh-so`              | `DoanhSoDTO` — `maNhanVien`, `hoTen`, `soVeBan`, `doanhSo` |

Dữ liệu thống kê doanh thu trong CSDL thường chỉ tính vé **Đã thanh toán** (theo stored procedure trong
`sql/quan_ly_ve_tau.sql`).

---

## 7. Module Ghế (đặc biệt)

Base: `/api/v1/ghe`

| Method | Path                   | Response body             |
|--------|------------------------|---------------------------|
| GET    | `/ghe`                 | `List<GheResponse>`       |
| GET    | `/ghe/{id}`            | `GheResponse`             |
| POST   | `/ghe`                 | `GheResponse`             |
| PUT    | `/ghe`                 | `GheResponse`             |
| DELETE | `/ghe/{id}`            | `String` — thông báo text |
| GET    | `/ghe/search?keyword=` | `List<GheResponse>`       |

Khi tích hợp, client cần parse JSON trực tiếp, không dùng cùng parser với `ApiResponse` như các API khác.

---

## 8. Cấu hình & CSDL

- JDBC & JPA: `src/main/resources/application.properties`
- Script mẫu: `sql/quan_ly_ve_tau.sql`
- JWT bí mật: `jwt.secret-key` — **không** nên commit giá trị production; dùng biến môi trường hoặc vault khi triển khai
  thật.

---

## 9. Gợi ý kiểm thử nhanh (Postman)

1. `POST /api/v1/auth/login` → copy `data.token`
2. Thêm header: `Authorization: Bearer <token>`
3. Gọi `GET /api/v1/tau` hoặc `GET /api/v1/thong-ke/doanh-thu-7d`
4. Với user **Nhân viên**: `doanh-thu-7d` thành công; các endpoint thống kê khác (nếu không được mở quyền) trả **403
   Forbidden**
