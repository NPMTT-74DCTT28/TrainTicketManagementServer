package com.nmptt.ticketapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "loai_toa")
public class LoaiToa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "ten_loai", nullable = false, length = 50)
    private String tenLoai;

    @ColumnDefault("1.00")
    @Column(name = "he_so_gia", precision = 3, scale = 2)
    private BigDecimal heSoGia;


}