# TrainTicketManagementAPI

Backend **Spring Boot** cho hệ thống quản lý bán vé tàu: REST API, JPA, MariaDB, JWT.

## Yêu cầu

- JDK **17**
- Maven 3.9+
- MariaDB (hoặc MySQL tương thích) — tạo DB và import script

## Cài đặt cơ sở dữ liệu

1. Khởi chạy MariaDB.
2. Import file: `sql/quan_ly_ban_ve_tau.sql`
3. Chỉnh `src/main/resources/application.properties`:
    - `spring.datasource.url`, `username`, `password`
    - `jwt.secret-key` (dùng chuỗi đủ dài; tránh lộ trên môi trường thật)

## Chạy ứng dụng

```bash
cd TrainTicketManagementAPI
mvn spring-boot:run
```

Mặc định API lắng nghe **cổng 8080** (trừ khi bạn cấu hình khác).

Entry point: `com.nmptt.ticketapi.TrainTicketManagementApiApplication`.

## Tài liệu API

Xem **[API.md](./API.md)** — danh sách endpoint, phân quyền, ví dụ đăng nhập và lưu ý module **Ghế** (response không bọc
`ApiResponse`).

## Công nghệ chính

- Spring Boot 4.x, Web MVC, Data JPA, Validation, Security
- MariaDB JDBC
- JWT (JJWT), BCrypt

## Module client

Ứng dụng máy trạm nằm trong repo chị em: **TrainTicketManagementClient** (Swing, gọi `http://localhost:8080/api/v1`).
