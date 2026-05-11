package com.nmptt.ticketapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tau")
public class Tau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Column(name = "ma_tau", nullable = false, length = 20)
    private String maTau;

    @Size(max = 100)
    @NotNull
    @Column(name = "ten_tau", nullable = false, length = 100)
    private String tenTau;


}