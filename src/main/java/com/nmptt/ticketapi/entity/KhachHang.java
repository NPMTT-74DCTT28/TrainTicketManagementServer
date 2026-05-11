package com.nmptt.ticketapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "khach_hang")
public class KhachHang {
    @Size(max = 255)
    @NotNull
    @Column(name = "dia_chi", nullable = false)
    private String diaChi;
    @Size(max = 20)
    @NotNull
    @Column(name = "sdt", nullable = false, length = 20)
    private String sdt;
    @Size(max = 20)
    @NotNull
    @Column(name = "gioi_tinh", nullable = false, length = 20)
    private String gioiTinh;
    @ColumnDefault("curdate()")
    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;
    @Size(max = 100)
    @NotNull
    @Column(name = "ho_ten", nullable = false, length = 100)
    private String hoTen;
    @Size(max = 20)
    @Column(name = "cccd", length = 20)
    private String cccd;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

}
