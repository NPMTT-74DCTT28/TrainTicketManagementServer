package com.nmptt.ticketapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ga_tau")
public class GaTau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ma_ga", unique = true, nullable = false, length = 20)
    private String maGa;
    @Column(name = "ten_ga", unique = true, nullable = false, length = 100)
    private String tenGa;
    @Column(name = "dia_chi", nullable = false)
    private String diaChi;
    @Column(name = "thanh_pho", nullable = false)
    private String thanhPho;
}
