# Hướng dẫn sử dụng API - Hệ thống quản lý vé tàu

## Thông tin

* **Tên ứng dụng:** `TrainTicketManagementAPI`
* **Phiên bản API:** `v1`
* **Phương thức xác thực:** `Bearer Token`
* **URL gốc:**

```
http://localhost:8080/api/v1
```

***Lưu ý bảo mật***: Ngoại trừ API đăng nhập, tất cả các API khác đều yêu cầu truyền token vào header của request với
định dạng:
`Authorization: Bearer <token_của_bạn>`

---

## Danh sách API

### Xác thực/Tài khoản

***Đăng nhập***

* **URL:** `/auth/login`
* **Phương thức:** `POST`
* **Yêu cầu token:** `Có`
* **Quyền hạn:** `Mọi vai trò`
* **Request body:**

```json
{
  "maNhanVien": "Mã nhân viên",
  "matKhau": "Mật khẩu"
}
```

* **Success response (200 - OK)**

```json
{
  "code": 200,
  "message": "Đăng nhập thành công!",
  "data": {
    "diaChi": "Địa chỉ",
    "email": "example@domain.com",
    "gioiTinh": "Nam",
    "hoTen": "Nguyễn Văn A",
    "id": 1,
    "maNhanVien": "NV001",
    "ngaySinh": "1980-01-01",
    "sdt": "0123456789",
    "token": "Bearer.authentication_TOKEN",
    "vaiTro": "Quản trị viên"
  }
}
```

***Đổi mật khẩu***

* **URL:** `/auth/change-pw`
* **Phương thức:** `POST`
* **Yêu cầu token:** `Có`
* **Quyền hạn:** `Bất kỳ ai đã đăng nhập`
* **Request body:**

```json
{
  "id": 1,
  "oldPassword": "Mật khẩu cũ",
  "newPassword": "Mật khẩu mới"
}
```

* **Success response (200 - OK)**

```json
{
  "code": 200,
  "message": "Đổi mật khẩu thành công!",
  "data": null
}
```

### Quản lý thông tin nhân viên

***Lấy danh sách nhân viên***

* **URL:** `/nhan-vien`
* **Phương thức:** `GET`
* **Yêu cầu token:** `Có`
* **Quyền hạn:** Chỉ `Quản trị viên` (Yêu cầu header mang token của admin)
* **Success response (200 - OK)**

```json
{
  "code": 200,
  "message": "Lấy danh sách nhân viên thành công!",
  "data": [
    {
      "diaChi": "Địa chỉ",
      "email": "example@domain.com",
      "gioiTinh": "Nam",
      "hoTen": "Nguyễn Văn A",
      "id": 1,
      "maNhanVien": "NV001",
      "ngaySinh": "1980-01-01",
      "sdt": "0123456789",
      "token": null,
      "vaiTro": "Quản trị viên"
    },
    {
      "diaChi": "Địa chỉ",
      "email": "example1@domain.com",
      "gioiTinh": "Nữ",
      "hoTen": "Nguyễn Thị B",
      "id": 2,
      "maNhanVien": "NV002",
      "ngaySinh": "1990-01-01",
      "sdt": "0321654987",
      "token": null,
      "vaiTro": "Nhân viên"
    }
  ]
}
```

***Lấy thông tin nhân viên bằng ID***

* **URL:** `/nhan-vien/{id}`
* **Phương thức:** `GET`
* **Yêu cầu token:** `Có`
* **Quyền hạn:** Chỉ `Quản trị viên`
* **Path variables**

```
| Biến | Kiểu dữ liệu |  Mô tả |

| id | int | Số ID nhân viên (VD: 123, 999) |
```

* **Success response (200 - OK)**

```json
{
  "code": 200,
  "message": "Đăng nhập thành công!",
  "data": {
    "diaChi": "Địa chỉ",
    "email": "example@domain.com",
    "gioiTinh": "Nam",
    "hoTen": "Nguyễn Văn A",
    "id": 1,
    "maNhanVien": "NV001",
    "ngaySinh": "1900-01-01",
    "sdt": "0123456789",
    "token": null,
    "vaiTro": "Quản trị viên"
  }
}
```

***Thêm thông tin nhân viên mới***

* **URL:** `/nhan-vien`
* **Phương thức:** `POST`
* **Yêu cầu token:** `Có`
* **Quyền hạn:** Chỉ `Quản trị viên`
* **Request body:**

```json
{
  "diaChi": "Địa chỉ",
  "email": "example2@domain.com",
  "gioiTinh": "Nam",
  "hoTen": "Đinh Văn C",
  "maNhanVien": "NV003",
  "matKhau": "Mật khẩu",
  "ngaySinh": "1991-01-01",
  "sdt": "0123789654",
  "vaiTro": "Nhân viên"
}
```

* **Success response (201 - CREATED)**

```json
{
  "code": 201,
  "message": "Thêm thông tin nhân viên thành công!",
  "data": {
    "diaChi": "Địa chỉ",
    "email": "example2@domain.com",
    "gioiTinh": "Nam",
    "hoTen": "Đinh Văn C",
    "id": 3,
    "maNhanVien": "NV003",
    "ngaySinh": "1991-01-01",
    "sdt": "0123789654",
    "token": null,
    "vaiTro": "Nhân viên"
  }
}
```

***Cập nhật thông tin nhân viên/thông tin cá nhân***

* **URL:** `/nhan-vien`
* **Phương thức:** `PUT`
* **Yêu cầu token:** `Có`
* **Quyền hạn:** `Quản trị viên`(sửa tất cả), `Nhân viên`(sửa thông tin cá nhân)
* **Request body:**

```json
{
  "diaChi": "Địa chỉ",
  "email": "example2@domain.com",
  "gioiTinh": "Nữ",
  "hoTen": "Đinh Thị D",
  "id": 3,
  "maNhanVien": "NV003",
  "matKhau": "(Không thay đổi bất kể có truyền dữ liệu hay không)",
  "ngaySinh": "1991-03-31",
  "sdt": "0123789654",
  "vaiTro": "(Không nên thay đổi nếu chọn cập nhật thông tin cá nhân)"
}
```

* **Success response 200 OK**

```json
{
  "code": 200,
  "message": "Cập nhật thông tin nhân viên thành công!",
  "data": {
    "diaChi": "Địa chỉ",
    "email": "example2@domain.com",
    "gioiTinh": "Nữ",
    "hoTen": "Đinh Thị D",
    "id": 3,
    "maNhanVien": "NV003",
    "ngaySinh": "1991-03-31",
    "sdt": "0123789654",
    "token": null,
    "vaiTro": "Quản trị viên/Nhân viên"
  }
}
```

***Xoá thông tin nhân viên***

* **URL:** `/nhan-vien/{id}`
* **Phương thức:** `DELETE`
* **Yêu cầu token:** `Có`
* **Quyền hạn:** Chỉ `Quản trị viên`
* **Path variables**

```
| Biến | Kiểu dữ liệu | Mô tả |

| id | int | Số ID nhân viên (VD: 1, 2) |
```

* **Success response (200 - OK)**

```json
{
  "code": 200,
  "message": "Xoá nhân viên thành công!",
  "data": null
}
```

***Tìm kiếm nhân viên***

* **URL:** `/search`
* **Phương thức:** `GET`
* **Yêu cầu token:** `Có`
* **Quyền hạn:** Chỉ `Quản trị viên`
* **Query params**

```
| Tham số | Kiểu dữ liệu | Bắt buộc | Mô tả |

| keyword | String | No | Từ khoá tìm kiếm (mã nhân viên, họ tên, số điện thoại, email, địa chỉ) |
| gioiTinh | String | No | Giới tính |
| vaiTro | String | No | Vai trò |
```

* **Success response (200 - OK)**

```json
{
  "code": 200,
  "message": "Tìm kiếm nhân viên thành công!",
  "data": [
    {
      "diaChi": "Địa chỉ",
      "email": "example@domain.com",
      "gioiTinh": "Nam",
      "hoTen": "Nguyễn Văn A",
      "id": 1,
      "maNhanVien": "NV001",
      "ngaySinh": "1980-01-01",
      "sdt": "0123456789",
      "token": null,
      "vaiTro": "Quản trị viên"
    },
    {
      "diaChi": "Địa chỉ",
      "email": "example1@domain.com",
      "gioiTinh": "Nữ",
      "hoTen": "Nguyễn Thị B",
      "id": 2,
      "maNhanVien": "NV002",
      "ngaySinh": "1990-01-01",
      "sdt": "0321654987",
      "token": null,
      "vaiTro": "Nhân viên"
    }
  ]
}
```
