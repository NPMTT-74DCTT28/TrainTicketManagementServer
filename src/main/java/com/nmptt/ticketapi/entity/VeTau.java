package com.nmptt.ticketapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ve_tau")
public class VeTau {
    @Size(max = 20)
    @NotNull
    @Column(name = "trang_thai", nullable = false, length = 20)
    private String trangThai;
    @NotNull
    @Column(name = "gia_ve", nullable = false)
    private double giaVe;
    @ColumnDefault("current_timestamp()")
    @Column(name = "ngay_dat")
    private LocalDateTime ngayDat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhan_vien")
    private NhanVien nhanVien;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_ghe", nullable = false)
    private Ghe ghe;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_lich_trinh", nullable = false)
    private LichTrinh lichTrinh;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_khach_hang", nullable = false)
    private KhachHang khachHang;
    @Size(max = 20)
    @NotNull
    @Column(name = "ma_ve", nullable = false, length = 20)
    private String maVe;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
}
