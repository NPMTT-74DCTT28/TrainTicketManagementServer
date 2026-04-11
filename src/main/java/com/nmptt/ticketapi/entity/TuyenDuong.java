package com.nmptt.ticketapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tuyen_duong")
public class TuyenDuong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Column(name = "ma_tuyen", nullable = false, length = 20)
    private String maTuyen;

    @Size(max = 100)
    @NotNull
    @Column(name = "ten_tuyen", nullable = false, length = 100)
    private String tenTuyen;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_ga_di", nullable = false)
    private GaTau idGaDi;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_ga_den", nullable = false)
    private GaTau idGaDen;

    @Column(name = "khoang_cach_km")
    private Integer khoangCachKm;

    @NotNull
    @Column(name = "gia_co_ban", nullable = false, precision = 10, scale = 2)
    private BigDecimal giaCoBan;


}