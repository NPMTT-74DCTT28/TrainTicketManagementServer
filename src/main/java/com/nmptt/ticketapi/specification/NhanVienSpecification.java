package com.nmptt.ticketapi.specification;

import com.nmptt.ticketapi.entity.NhanVien;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class NhanVienSpecification {

    public static Specification<NhanVien> filter(String keyword, String gioiTinh, String vaiTro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (keyword != null && !keyword.trim().isEmpty()) {
                String likeKeyword = "%" + keyword + "%";
                Predicate keywordPredicate = cb.or(
                        cb.like(root.get("maNhanVien"), likeKeyword),
                        cb.like(root.get("hoTen"), likeKeyword),
                        cb.like(root.get("sdt"), likeKeyword),
                        cb.like(root.get("email"), likeKeyword),
                        cb.like(root.get("diaChi"), likeKeyword)
                );
                predicates.add(keywordPredicate);
            }

            if (gioiTinh != null && !gioiTinh.trim().isEmpty()) {
                predicates.add(cb.equal(root.get("gioiTinh"), gioiTinh));
            }

            if (vaiTro != null && !vaiTro.trim().isEmpty()) {
                predicates.add(cb.equal(root.get("vaiTro"), vaiTro));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
