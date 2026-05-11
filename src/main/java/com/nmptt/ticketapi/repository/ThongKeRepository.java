package com.nmptt.ticketapi.repository;

import com.nmptt.ticketapi.dto.thongke.*;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ThongKeRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<DoanhThuBayNgayDTO> getDoanhThuBayNgay() {
        String sql = "CALL sp_DoanhThuBayNgay";

        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(DoanhThuBayNgayDTO.class));
    }

    public List<DoanhThuTheoNgayDTO> getDoanhThuTheoNgay(LocalDate tuNgay, LocalDate denNgay) {
        String sql = "CALL sp_DoanhThuTheoNgay(?, ?)";

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(DoanhThuTheoNgayDTO.class),
                tuNgay,
                denNgay
        );
    }

    public List<DoanhThuTheoTuyenDTO> getDoanhThuTheoTuyen(LocalDate tuNgay, LocalDate denNgay) {
        String sql = "CALL sp_DoanhThuTheoTuyen(?, ?)";

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(DoanhThuTheoTuyenDTO.class),
                tuNgay,
                denNgay
        );
    }

    public List<TyLeLapDayDTO> getTyLeLapDay(LocalDate tuNgay, LocalDate denNgay) {
        String sql = "CALL sp_TyLeLapDay(?, ?)";

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(TyLeLapDayDTO.class),
                tuNgay,
                denNgay
        );
    }

    public List<KhachHangVipDTO> getKhachHangVip(int soLuong) {
        String sql = "CALL sp_KhachHangVIP(?)";

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(KhachHangVipDTO.class),
                soLuong
        );
    }

    public List<DoanhSoDTO> getDoanhSo(int thang, int nam) {
        String sql = "CALL sp_DoanhSo(?, ?)";

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(DoanhSoDTO.class),
                thang,
                nam
        );
    }
}
