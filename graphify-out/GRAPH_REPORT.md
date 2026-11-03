# Graph Report - TrainTicketManagementServer  (2026-11-03)

## Corpus Check
- cluster-only mode — file stats not available

## Summary
- 688 nodes · 1966 edges · 19 communities (13 shown, 6 thin omitted)
- Extraction: 93% EXTRACTED · 7% INFERRED · 0% AMBIGUOUS · INFERRED: 131 edges (avg confidence: 0.85)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `4884e293`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- lombok.RequiredArgsConstructor
- ResourceNotFoundException
- GheResponse
- list
- LichTrinhResponse
- ToaTauResponse
- org.springframework.security.access.prepost.PreAuthorize
- KhachHang
- org.springframework.http.ResponseEntity
- VeTauResponse
- Tau
- LoaiToa
- JwtAuthenticationFilter.java
- TuyenDuong
- SecurityConfig.java
- TrainTicketManagementApiApplication.java
- com.nmptt:ticket-api

## God Nodes (most connected - your core abstractions)
1. `ApiResponse` - 87 edges
2. `ResourceNotFoundException` - 47 edges
3. `Tau` - 35 edges
4. `GaTau` - 35 edges
5. `KhachHang` - 34 edges
6. `LoaiToa` - 31 edges
7. `NhanVienResponse` - 28 edges
8. `DuplicateDataException` - 23 edges
9. `GheResponse` - 23 edges
10. `LichTrinhResponse` - 23 edges

## Surprising Connections (you probably didn't know these)
- `NhanVienServiceImpl` --references--> `NhanVienRepository`  [EXTRACTED]
  src/main/java/com/nmptt/ticketapi/service/NhanVienService.java → src/main/java/com/nmptt/ticketapi/repository/NhanVienRepository.java
- `NhanVienServiceImpl` --references--> `JwtUtil`  [EXTRACTED]
  src/main/java/com/nmptt/ticketapi/service/NhanVienService.java → src/main/java/com/nmptt/ticketapi/security/JwtUtil.java
- `LichTrinh` --references--> `Tau`  [EXTRACTED]
  src/main/java/com/nmptt/ticketapi/entity/LichTrinh.java → src/main/java/com/nmptt/ticketapi/entity/Tau.java
- `ToaTau` --references--> `Tau`  [EXTRACTED]
  src/main/java/com/nmptt/ticketapi/entity/ToaTau.java → src/main/java/com/nmptt/ticketapi/entity/Tau.java
- `ToaTau` --references--> `LoaiToa`  [EXTRACTED]
  src/main/java/com/nmptt/ticketapi/entity/ToaTau.java → src/main/java/com/nmptt/ticketapi/entity/LoaiToa.java

## Import Cycles
- None detected.

## Communities (19 total, 6 thin omitted)

### Community 0 - "lombok.RequiredArgsConstructor"
Cohesion: 0.07
Nodes (11): ThongKeController, DoanhSoDTO, DoanhThuBayNgayDTO, DoanhThuTheoNgayDTO, DoanhThuTheoTuyenDTO, KhachHangVipDTO, TyLeLapDayDTO, DuplicateDataException (+3 more)

### Community 1 - "ResourceNotFoundException"
Cohesion: 0.09
Nodes (10): AuthController, NhanVienController, ChangePasswordRequest, LoginRequest, NhanVienRequest, NhanVienResponse, BadCredentialsException, ResourceNotFoundException (+2 more)

### Community 2 - "GheResponse"
Cohesion: 0.09
Nodes (7): GheController, GheRequest, GheResponse, Ghe, GheRepository, GheService, GheServiceImpl

### Community 3 - "list"
Cohesion: 0.08
Nodes (6): NhanVien, VeTau, NhanVienRepository, VeTauRepository, KhachHangSpecification, NhanVienSpecification

### Community 4 - "LichTrinhResponse"
Cohesion: 0.09
Nodes (7): LichTrinhController, LichTrinhRequest, LichTrinhResponse, LichTrinh, LichTrinhRepository, LichTrinhService, LichTrinhServiceImpl

### Community 5 - "ToaTauResponse"
Cohesion: 0.09
Nodes (7): ToaTauController, ToaTauRequest, ToaTauResponse, ToaTau, ToaTauRepository, ToaTauService, ToaTauServiceImpl

### Community 6 - "org.springframework.security.access.prepost.PreAuthorize"
Cohesion: 0.09
Nodes (5): GaTauController, GaTau, GaTauRepository, GaTauService, GaTauServiceImpl

### Community 7 - "KhachHang"
Cohesion: 0.09
Nodes (5): KhachHangController, KhachHang, KhachHangRepository, KhachHangService, KhachHangServiceImpl

### Community 8 - "org.springframework.http.ResponseEntity"
Cohesion: 0.12
Nodes (6): TuyenDuongController, TuyenDuongRequest, ApiResponse, TuyenDuongResponse, GlobalExceptionHandler, TuyenDuongService

### Community 9 - "VeTauResponse"
Cohesion: 0.10
Nodes (5): VeTauController, VeTauRequest, VeTauResponse, VeTauService, VeTauServiceImpl

### Community 10 - "Tau"
Cohesion: 0.10
Nodes (5): TauController, Tau, TauRepository, TauService, TauServiceImpl

### Community 11 - "LoaiToa"
Cohesion: 0.10
Nodes (5): LoaiToaController, LoaiToa, LoaiToaRepository, LoaiToaService, LoaiToaServiceImpl

### Community 14 - "TuyenDuong"
Cohesion: 0.16
Nodes (3): TuyenDuong, TuyenDuongRepository, TuyenDuongServiceImpl

## Knowledge Gaps
- **1 isolated node(s):** `com.nmptt:ticket-api`
  These have ≤1 connection - possible missing edges. (Counts symbols only; 148 node(s) total have ≤1 connection when file, concept and rationale nodes are included.)
- **6 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `ApiResponse` connect `org.springframework.http.ResponseEntity` to `lombok.RequiredArgsConstructor`, `ResourceNotFoundException`, `GheResponse`, `LichTrinhResponse`, `ToaTauResponse`, `org.springframework.security.access.prepost.PreAuthorize`, `KhachHang`, `VeTauResponse`, `Tau`, `LoaiToa`, `lombok.Getter`?**
  _High betweenness centrality (0.140) - this node is a cross-community bridge._
- **What connects `com.nmptt:ticket-api` to the rest of the system?**
  _1 weakly-connected nodes found - possible missing edges._
- **Should `lombok.RequiredArgsConstructor` be split into smaller, more focused modules?**
  _Cohesion score 0.06944444444444445 - nodes in this community are weakly interconnected._
- **Why does `ResourceNotFoundException` connect `ResourceNotFoundException` to `lombok.RequiredArgsConstructor`, `GheResponse`, `LichTrinhResponse`, `ToaTauResponse`, `org.springframework.security.access.prepost.PreAuthorize`, `KhachHang`, `org.springframework.http.ResponseEntity`, `VeTauResponse`, `Tau`, `LoaiToa`, `TuyenDuong`?**
  _High betweenness centrality (0.086) - this node is a cross-community bridge._
- **Should `ResourceNotFoundException` be split into smaller, more focused modules?**
  _Cohesion score 0.08832425892316999 - nodes in this community are weakly interconnected._
- **Why does `KhachHang` connect `KhachHang` to `lombok.RequiredArgsConstructor`, `list`, `lombok.Getter`?**
  _High betweenness centrality (0.054) - this node is a cross-community bridge._
- **Should `GheResponse` be split into smaller, more focused modules?**
  _Cohesion score 0.08928571428571429 - nodes in this community are weakly interconnected._