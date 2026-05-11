package com.nmptt.ticketapi.specification;

import com.nmptt.ticketapi.entity.KhachHang;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class KhachHangSpecification {
    public static Specification<KhachHang> filter(String keyword, String gioiTinh) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (keyword != null && !keyword.trim().isEmpty()) {
                String likeKeyword = "%" + keyword + "%";
                Predicate keywordPredicate = cb.or(
                        cb.like(root.get("cccd"), likeKeyword),
                        cb.like(root.get("hoTen"), likeKeyword),
                        cb.like(root.get("sdt"), likeKeyword),
                        cb.like(root.get("diaChi"), likeKeyword)
                );
                predicates.add(keywordPredicate);
            }

            if (gioiTinh != null && !gioiTinh.trim().isEmpty()) {
                predicates.add(cb.equal(root.get("gioiTinh"), gioiTinh));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
