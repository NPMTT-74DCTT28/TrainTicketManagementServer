package com.nmptt.ticketapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nhan_vien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "ma_nhan_vien")
    private String maNhanVien;

    @Size(max = 255)
    @NotNull
    @Column(name = "mat_khau", nullable = false)
    private String matKhau;

    @Size(max = 255)
    @Column(name = "ho_ten")
    private String hoTen;

    @NotNull
    @ColumnDefault("curdate()")
    @Column(name = "ngay_sinh", nullable = false)
    private LocalDate ngaySinh;

    @Size(max = 255)
    @Column(name = "gioi_tinh")
    private String gioiTinh;

    @Size(max = 255)
    @Column(name = "sdt")
    private String sdt;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "dia_chi", nullable = false)
    private String diaChi;

    @Size(max = 20)
    @NotNull
    @Column(name = "vai_tro", nullable = false, length = 20)
    private String vaiTro;


}