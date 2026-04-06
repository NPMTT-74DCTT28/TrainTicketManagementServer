package com.nmptt.ticketapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "nhan_vien")
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ma_nhan_vien", unique = true, nullable = false, length = 20)
    private String maNhanVien;

    @Column(name = "mat_khau", nullable = false)
    private String matKhau;

    @Column(name = "ho_ten", nullable = false, length = 100)
    private String hoTen;

    @Column(name = "ngay_sinh", nullable = false)
    private LocalDate ngaySinh;

    @Column(name = "gioi_tinh", nullable = false, length = 20)
    private String gioiTinh;

    @Column(name = "sdt", unique = true, nullable = false)
    private String sdt;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "dia_chi", nullable = false)
    private String diaChi;

    @Column(name = "vai_tro", nullable = false, length = 20)
    private String vaiTro;
}
