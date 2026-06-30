# TrainTicketManagementAPI

Backend **Spring Boot** cho hệ thống quản lý bán vé tàu: REST API, JPA, MariaDB, JWT.

## Yêu cầu

- JDK **17**
- Maven 3.9+
- MariaDB (hoặc MySQL tương thích) — tạo DB và import script

## Cài đặt và khởi chạy

### Chạy trực tiếp trên IDE

1. Khởi chạy MariaDB.
2. Import file: `sql/quan_ly_ve_tau.sql`
3. Chỉnh `src/main/resources/application.properties`:
    - `spring.datasource.url`, `username`, `password`
    - `jwt.secret-key` (dùng chuỗi đủ dài; tránh lộ trên môi trường thật)
4. Khởi chạy ứng dụng

```bash
cd TrainTicketManagementAPI
mvn spring-boot:run
```

### Chạy trên Docker

1. Tạo bản sao của file [docker-compose.yml.example](./docker-compose.yml.example) và xoá đuôi `.example`.
2. Mở terminal tại thư mục project.
3. Xoá sạch container cũ (nếu cần).

```bash
docker compose down -v
```

4. Chạy lệnh build.

```bash
docker compose up -d --build
```

Mặc định API lắng nghe **cổng 8080** (trừ khi bạn cấu hình khác).

## Tài liệu API

Xem **[API.md](./API.md)** — danh sách endpoint, phân quyền, ví dụ đăng nhập và lưu ý module **Ghế** (response không bọc
`ApiResponse`).

## Công nghệ chính

- Spring Boot 4.x, Web MVC, Data JPA, Validation, Security
- MariaDB JDBC
- JWT (JJWT), BCrypt

## Module client

Ứng dụng máy trạm nằm trong repo chị
em: [TrainTicketManagementClient](https://github.com/NPMTT-74DCTT28/TrainTicketManagementClient)(Swing, gọi
`http://localhost:8080/api/v1`).