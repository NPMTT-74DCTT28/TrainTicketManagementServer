package com.nmptt.ticketapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "toa_tau")
@NoArgsConstructor
@AllArgsConstructor
public class ToaTau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Column(name = "ma_toa", nullable = false, length = 20)
    private String maToa;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tau", nullable = false)
    private Tau idTau;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_loai_toa", nullable = false)
    private LoaiToa idLoaiToa;
}