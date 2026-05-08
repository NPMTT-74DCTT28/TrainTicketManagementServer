package com.nmptt.ticketapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@Table(name = "lich_trinh")
@NoArgsConstructor
@AllArgsConstructor
public class LichTrinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 20)
    @Column(name = "ma_lich_trinh", length = 20)
    private String maLichTrinh;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tau", nullable = false)
    private Tau idTau;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tuyen_duong", nullable = false)
    private TuyenDuong idTuyenDuong;

    @NotNull
    @Column(name = "ngay_di", nullable = false)
    private LocalDateTime ngayDi;

    @NotNull
    @Column(name = "ngay_den", nullable = false)
    private LocalDateTime ngayDen;

    @Size(max = 20)
    @NotNull
    @Column(name = "trang_thai", nullable = false, length = 20)
    private String trangThai;


}